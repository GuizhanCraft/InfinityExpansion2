package net.guizhanss.infinityexpansion2.core.commands.subcommands

import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import org.bukkit.command.CommandSender

/**
 * Debug control command
 */
class DebugCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "debug", "<enable|disable|toggle|list> [debugcase]"
) {

    override fun onExecute(sender: CommandSender, args: Array<String>) {
        if (!sender.hasPermission()) {
            InfinityExpansion2.integrationService.sendMessage(sender, "commands.no-permission")
            return
        }

        if (args.isEmpty()) {
            InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.invalid-action", "")
            return
        }

        when (val action = args[0].lowercase()) {
            "list" -> handleListCommand(sender)
            "enable", "disable", "toggle" -> {
                if (args.size < 2) {
                    handleGeneralToggle(sender, action)
                } else {
                    val case = args[1]
                    handleCaseToggle(sender, action, case)
                }
            }

            else -> InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.invalid-action", action)
        }
    }

    private fun handleListCommand(sender: CommandSender) {
        val debugService = InfinityExpansion2.debugService
        val generalStatus = if (debugService.isDebugEnabled()) Strings.CHECK else Strings.CROSS
        InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.list.general", generalStatus)
        sender.sendMessage("")
        InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.list.header")
        for (debugCase in debugService.getAvailableCases()) {
            val caseStatus = if (debugService.isEnabled(debugCase)) Strings.CHECK else Strings.CROSS
            InfinityExpansion2.integrationService.sendMessage(
                sender,
                "commands.debug.list.item",
                debugCase.name.lowercase(),
                caseStatus
            )
        }
    }

    private fun handleGeneralToggle(sender: CommandSender, action: String) {
        val debugService = InfinityExpansion2.debugService
        when (action) {
            "enable" -> {
                debugService.setDebugEnabled(true)
                InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.general.enabled")
            }

            "disable" -> {
                debugService.setDebugEnabled(false)
                InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.general.disabled")
            }

            "toggle" -> {
                val newState = debugService.toggleDebug()
                val status = if (newState) "enabled" else "disabled"
                InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.general.${status}")
            }
        }
    }

    private fun handleCaseToggle(
        sender: CommandSender, action: String, case: String
    ) {
        val debugCase = DebugCase.fromString(case) ?: run {
            InfinityExpansion2.integrationService.sendMessage(sender, "commands.debug.case.invalid", case)
            return
        }

        val debugService = InfinityExpansion2.debugService

        when (action) {
            "enable" -> {
                debugService.setEnabled(debugCase, true)
                InfinityExpansion2.integrationService.sendMessage(
                    sender,
                    "commands.debug.case.enabled",
                    debugCase.name.lowercase()
                )
            }

            "disable" -> {
                debugService.setEnabled(debugCase, false)
                InfinityExpansion2.integrationService.sendMessage(
                    sender,
                    "commands.debug.case.disabled",
                    debugCase.name.lowercase()
                )
            }

            "toggle" -> {
                val newState = debugService.toggle(debugCase)
                val status = if (newState) "enabled" else "disabled"
                InfinityExpansion2.integrationService.sendMessage(
                    sender, "commands.debug.case.${status}", debugCase.name.lowercase()
                )
            }
        }
    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String> {
        return when (args.size) {
            1 -> listOf("enable", "disable", "toggle", "list").filter { it.startsWith(args[0].lowercase()) }
            2 -> {
                val action = args[0].lowercase()
                if (action in listOf("enable", "disable", "toggle")) {
                    DebugCase.entries.map { it.name }
                        .filter { it.lowercase().startsWith(args[1].lowercase()) }
                } else {
                    emptyList()
                }
            }

            else -> emptyList()
        }
    }
}
