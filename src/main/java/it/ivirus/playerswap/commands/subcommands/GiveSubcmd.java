package it.ivirus.playerswap.commands.subcommands;

import it.ivirus.playerswap.commands.SubCommand;
import it.ivirus.playerswap.utils.PSwapUtil;
import it.ivirus.playerswap.utils.Strings;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveSubcmd extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        if (!sender.hasPermission("playerswap.give")) {
            adventure.sender(sender).sendMessage(Strings.ERROR_NOPERMISSION.getFormattedString());
            return;
        }
        if (args.length != 2 && args.length != 3) {
            adventure.sender(sender).sendMessage(Strings.ERROR_USAGE_GIVE.getFormattedString());
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            adventure.sender(sender).sendMessage(Strings.ERROR_TARGET_OFFLINE.getFormattedString());
            return;
        }
        int amount = 1;
        if (args.length != 2) {
            if (!NumberUtils.isDigits(args[2])) {
                adventure.sender(sender).sendMessage(Strings.ERROR_INVALID_VALUE.getFormattedString());
                return;
            }
            amount = Integer.parseInt(args[2]);
            if (amount < 1) {
                adventure.sender(sender).sendMessage(Strings.ERROR_INVALID_VALUE.getFormattedString());
                return;
            }
        }
        target.getInventory().addItem(PSwapUtil.getSwapperItem(amount));
        adventure.player(target).sendMessage(Strings.INFO_SWAPPER_GET.getFormattedString());
        adventure.sender(sender).sendMessage(Strings.getFormattedString(Strings.INFO_SWAPPER_GIVE.getString()
                .replaceAll("%amount%", String.valueOf(amount))
                .replaceAll("%target_name%", target.getName())));

    }
}
