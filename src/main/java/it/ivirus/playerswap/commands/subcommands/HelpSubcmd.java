package it.ivirus.playerswap.commands.subcommands;

import it.ivirus.playerswap.commands.SubCommand;
import it.ivirus.playerswap.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpSubcmd extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        if (!sender.hasPermission("playerswap.help")) {
            adventure.sender(sender).sendMessage(Strings.ERROR_NOPERMISSION.getFormattedString());
            return;
        }
        adventure.sender(sender).sendMessage(Strings.INFO_HELP.getFormattedString());
    }
}
