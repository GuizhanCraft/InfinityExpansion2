package net.guizhanss.infinityexpansion2.core.commands.subcommands

import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Same as `/paper dumpitem`.
 */
class PrintItemCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "printitem", ""
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

        val item = p.inventory.itemInMainHand

        if (item.isAir()) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.commands.printitem.no-item")
            return
        }

        p.sendMessage(item.toString())
    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String> = emptyList()
}
