package net.guizhanss.infinityexpansion2.core.commands

import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.guizhanlib.minecraft.commands.SubCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.command.CommandSender

abstract class AbstractSubCommand(
    parent: AbstractCommand?,
    name: String,
    usage: String,
    vararg subCommands: SubCommand
) : SubCommand(parent, name, { _, sender -> getDescription(name, sender) }, usage, *subCommands) {

    constructor(
        name: String,
        usage: String,
        vararg subCommands: SubCommand
    ) : this(null, name, usage, *subCommands)

    fun CommandSender.hasPermission() =
        this.hasPermission("infinityexpansion2.command.$name")

    companion object {

        private fun getDescription(cmd: String, sender: CommandSender) =
            InfinityExpansion2.integrationService.getMessage(sender, "commands.$cmd.description")
    }
}
