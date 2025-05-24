package net.guizhanss.infinityexpansion2.core.commands.subcommands

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.guizhanlib.minecraft.utils.InventoryUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.Locale

/**
 * Give the recipe ingredients of a specific item to a given player.
 */
class GiveRecipeCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "giverecipe", "<id> [player]"
) {

    override fun onExecute(p: CommandSender, args: Array<String>) {
        if (!p.hasPermission()) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.no-permission")
            return
        }

        // get the target player from the second arg or the sender
        val target = if (args.size == 2) {
            InfinityExpansion2.instance.server.getPlayer(args[1]) ?: run {
                InfinityExpansion2.integrationService.sendMessage(p, "commands.player-not-found")
                return
            }
        } else {
            if (p !is Player) {
                InfinityExpansion2.integrationService.sendMessage(p, "commands.player-only")
                return
            }
            p
        }

        // check if the target item is valid
        val sfItem = SlimefunItem.getById(args[0].uppercase(Locale.getDefault()))

        if (sfItem == null ||
            sfItem is MultiBlockMachine ||
            sfItem.recipeType == RecipeType.GEO_MINER ||
            sfItem.recipeType == IERecipeTypes.SINGULARITY_CONSTRUCTOR
        ) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.invalid-item")
            return
        }

        // give the recipe
        InfinityExpansion2.integrationService.sendMessage(
            p,
            "commands.giverecipe.to-sender",
            InfinityExpansion2.integrationService.getTranslatedItemName(target, sfItem.item),
            target.name
        )
        InfinityExpansion2.integrationService.sendMessage(
            target,
            "commands.giverecipe.to-target",
            InfinityExpansion2.integrationService.getTranslatedItemName(target, sfItem.item)
        )

        InventoryUtil.push(target, *sfItem.recipe.filterNotNull().toTypedArray())
    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String>? {
        return when (args.size) {
            1 -> {
                // filter slimefun item id
                Slimefun.getRegistry().enabledSlimefunItems
                    .map { it.id }
                    .filter { it.startsWith(args[0], true) }
                    .take(10)
            }

            2 -> null
            else -> emptyList()
        }
    }
}
