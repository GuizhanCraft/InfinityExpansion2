package net.guizhanss.infinityexpansion2.core.commands

import net.guizhanss.guizhanlib.minecraft.commands.BaseCommand
import net.guizhanss.infinityexpansion2.core.commands.subcommands.DebugCommand
import net.guizhanss.infinityexpansion2.core.commands.subcommands.GiveRecipeCommand
import net.guizhanss.infinityexpansion2.core.commands.subcommands.GuideCommand
import net.guizhanss.infinityexpansion2.core.commands.subcommands.IdCommand
import net.guizhanss.infinityexpansion2.core.commands.subcommands.PrintItemCommand
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand

class MainCommand(command: PluginCommand) : BaseCommand(
    command, { _, _ -> "" }, "<subcommand>"
) {

    init {
        addSubCommand(DebugCommand(this))
        addSubCommand(GiveRecipeCommand(this))
        addSubCommand(GuideCommand(this))
        addSubCommand(IdCommand(this))
        addSubCommand(PrintItemCommand(this))
    }

    override fun onExecute(sender: CommandSender, args: Array<String>) {
        // there are sub commands so the root command does nothing
    }
}
