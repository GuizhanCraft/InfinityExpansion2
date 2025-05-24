package net.guizhanss.infinityexpansion2.core.commands.subcommands

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.InfinityExpansion2API
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Get the ID of handheld item.
 */
class IdCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "id", ""
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

        val handItem = p.inventory.itemInMainHand
        val sfItem = SlimefunItem.getByItem(handItem) ?: run {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.invalid-item")
            return
        }
        InfinityExpansion2.integrationService.sendMessage(
            p,
            "commands.id.message",
            InfinityExpansion2.integrationService.getTranslatedItemName(p, sfItem.item),
        )

    }

    override fun onTab(sender: CommandSender, args: Array<String>): List<String> {
        return emptyList()
    }
}




