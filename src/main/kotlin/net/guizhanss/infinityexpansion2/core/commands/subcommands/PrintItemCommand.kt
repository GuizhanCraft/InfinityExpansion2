package net.guizhanss.infinityexpansion2.core.commands.subcommands

import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PrintItemCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "printitem", ""
) {

    override fun onExecute(p: CommandSender, args: Array<String>) {
        if (!p.hasPermission()) {
            InfinityExpansion2.integrationService.sendMessage(p, "no-permission")
            return
        }
        if (p !is Player) {
            InfinityExpansion2.integrationService.sendMessage(p, "player-only")
            return
        }

        val item = p.inventory.itemInMainHand

        if (item.isAir) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.printitem.no-item")
            return
        }

        p.sendMessage(item.toString())
    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String> = emptyList()
}
