package net.guizhanss.infinityexpansion2.core.commands.subcommands

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.guizhanlib.minecraft.commands.AbstractCommand
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.InfinityExpansion2API
import net.guizhanss.infinityexpansion2.core.commands.AbstractSubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GuideCommand(parent: AbstractCommand) : AbstractSubCommand(
    parent, "guide", ""
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

        // check if the player is holding an IE2 item in main hand
        val sfItem = SlimefunItem.getByItem(p.inventory.itemInMainHand)

        if (sfItem == null) {
            InfinityExpansion2.integrationService.sendMessage(p, "commands.invalid-item")
            return
        }

        InfinityExpansion2API.openGuide(p, sfItem)
    }
}
