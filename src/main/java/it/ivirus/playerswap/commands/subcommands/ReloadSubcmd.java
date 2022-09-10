package it.ivirus.playerswap.commands.subcommands;

import it.ivirus.playerswap.commands.SubCommand;
import it.ivirus.playerswap.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadSubcmd extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        if (!sender.hasPermission("playerswap.admin")) {
            adventure.sender(sender).sendMessage(Strings.ERROR_NOPERMISSION.getFormattedString());
            return;
        }
        plugin.reloadConfig();
        plugin.loadLangConfig();
        adventure.sender(sender).sendMessage(Strings.INFO_RELOAD.getFormattedString());
    }
}
