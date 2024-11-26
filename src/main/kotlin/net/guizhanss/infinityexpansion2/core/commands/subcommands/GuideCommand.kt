package net.guizhanss.infinityexpansion2.core.commands.subcommands

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.InfinityExpansion2API
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GuideCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "guide", "[ID]"
) {

    override fun onExecute(p: CommandSender, args: Array<String>) {
        if (!p.hasPermission()) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.no-permission")
            return
        }
        if (p !is Player) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.player-only")
            return
        }

        if (args.size == 1) { // with ID
            val sfItem = SlimefunItem.getById(args[0]) ?: run {
                InfinityExpansion2.integrationService.sendMessage(p, "commands.invalid-item")
                return
            }

            InfinityExpansion2API.openGuide(p, sfItem)
            return
        } else { // no ID provided, check handheld item
            val handItem = p.inventory.itemInMainHand
            val sfItem = SlimefunItem.getByItem(handItem) ?: run {
                InfinityExpansion2.integrationService.sendMessage(p, "commands.invalid-item")
                return
            }

            InfinityExpansion2API.openGuide(p, sfItem, handItem)
        }
    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String> {
        return if (args.size == 1) {
            // filter slimefun item id
            Slimefun.getRegistry().enabledSlimefunItems
                .map { it.id }
                .filter { it.startsWith(args[0], true) }
                .take(10)
        } else {
            emptyList()
        }
    }
}
