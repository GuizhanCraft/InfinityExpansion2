package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.schntgaispock.slimehud.util.HudBuilder
import io.github.schntgaispock.slimehud.waila.HudRequest
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.getBlockMenu
import net.guizhanss.infinityexpansion2.utils.getInt
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import net.guizhanss.infinityexpansion2.utils.setInt
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import java.util.logging.Level
import kotlin.math.floor
import kotlin.random.Random


class MobSimulationChamber(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT, energyPerTick),
    EnergyTickingConsumer, InformationalRecipeDisplayItem {

    override fun postRegister() {
        super.postRegister()

        // check if consumption is too large (about 2.1b / 100)
        if (getEnergyConsumptionPerTick() > 20_000_000) {
            InfinityExpansion2.log(Level.WARNING, "Invalid item settings for $id:")
            InfinityExpansion2.log(
                Level.WARNING,
                "The energy consumption is too large (cannot larger than 20_000_000)."
            )
            InfinityExpansion2.log(Level.WARNING, "Using default value now, please update the config.")
            energyPerTickSetting.update(energyPerTickSetting.defaultValue)
        }
    }

    override fun getCapacity() = getEnergyConsumptionPerTick() * 100

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        val l = b.location

        // xp button
        menu.replaceExistingItem(XP_SLOT, GuiItems.experience(0))
        menu.addMenuClickHandler(XP_SLOT) { p, _, _, _ ->
            val xp = l.getInt(XP_KEY)
            if (xp > 0) {
                p.giveExp(xp)
                p.playSound(l, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
                l.setInt(XP_KEY, 0)
                menu.replaceExistingItem(XP_SLOT, GuiItems.experience(0))
            }
            false
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val l = b.location
        val (props, cardAmount) = menu.getDataCard(layout) ?: run {
            menu.setStatus { GuiItems.INVALID_INPUT }
            menu.setEnergyConsumption(0)
            return false
        }

        // handle stackable
        val amount = if (InfinityExpansion2.configService.mobSimAllowStackedCard) cardAmount else 1
        val energy = getEnergyConsumptionPerTick() + props.energy

        if (getCharge(menu.location) < energy) {
            menu.setStatus { GuiItems.NO_POWER }
            menu.setEnergyConsumption(0)
            return false
        }

        val currentXp = l.getInt(XP_KEY)
        menu.setStatus { GuiItems.PRODUCING }
        menu.setEnergyConsumption(energy)
        menu.replaceExistingItem(XP_SLOT, GuiItems.experience(currentXp))

        if (InfinityExpansion2.sfTickCount() % (InfinityExpansion2.configService.mobSimInterval * getCustomTickRate()) == 0) {
            val xp = floor(props.experience * InfinityExpansion2.configService.mobSimExpMultiplier).toInt()
            l.setInt(XP_KEY, currentXp + xp)

            if (InfinityExpansion2.configService.mobSimLegacyOutput) {
                val output = props.getRandomDrop()
                if (!menu.fits(output, *outputSlots)) {
                    menu.setStatus { GuiItems.NO_ROOM }
                    return false
                }
                menu.pushItem(output.clone(), *outputSlots)
            } else {
                val drops = mutableListOf<ItemStack>()
                props.drops.forEach { (item, chance) ->
                    if (Random.nextDouble() <= chance) {
                        val totalAmt = item.amount * amount
                        val stacks = floor(totalAmt * 1.0 / item.maxStackSize).toInt()
                        val remaining = totalAmt % item.maxStackSize
                        repeat(stacks) {
                            drops.add(item.clone().apply { setAmount(maxStackSize) })
                        }
                        if (remaining > 0) {
                            drops.add(item.clone().apply { setAmount(remaining) })
                        }
                    }
                }

                if (!InvUtils.fitAll(menu.toInventory(), drops.toTypedArray(), *outputSlots)) {
                    menu.setStatus { GuiItems.NO_ROOM }
                    return false
                }
                drops.forEach { menu.pushItem(it, *outputSlots) }
            }
        }

        removeCharge(l, energy)
        // handle custom energy consumption, so always return false
        return false
    }

    private fun BlockMenu.setEnergyConsumption(value: Int = 0) {
        if (hasViewer()) {
            val item = if (value > 0) {
                GuiItems.energyConsumptionPerTick(value, getCustomTickRate())
            } else {
                ChestMenuUtils.getBackground()
            }
            replaceExistingItem(ENERGY_CONSUMPTION_SLOT, item)
        }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.outputInterval(InfinityExpansion2.configService.mobSimInterval),
    )

    companion object {

        private const val ENERGY_CONSUMPTION_SLOT = 5
        private const val XP_SLOT = 8
        private const val XP_KEY = "xp"

        /**
         * Get the data card from the menu input slot (the layout must be [MenuLayout.SINGLE_INPUT]).
         * If the input is invalid, return null.
         * Returns the [MobDataCardProps] and the amount of the card.
         */
        private fun BlockMenu.getDataCard(layout: MenuLayout): Pair<MobDataCardProps, Int>? {
            val input = getItemInSlot(layout.inputSlots[0])
            if (input.isAir || !input.isSlimefunItem<MobDataCard>()) {
                return null
            }

            // check if input is a registered card
            val id = MobDataCard.getMobDataId(input) ?: return null
            val props = IERegistry.mobDataCards[id] ?: return null

            return props to input.amount
        }

        fun getHudResponse(request: HudRequest): String {
            val menu = request.location.getBlockMenu()
            val chamber = request.slimefunItem as MobSimulationChamber
            val energyHud = " | " + HudBuilder.formatEnergyStored(chamber.getCharge(request.location), chamber.capacity)
            val (props, _) = menu.getDataCard(chamber.layout) ?: return "Empty$energyHud"
            return props.name + energyHud
        }
    }
}
