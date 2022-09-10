package it.ivirus.playerswap.commands.subcommands;

import it.ivirus.playerswap.commands.SubCommand;
import it.ivirus.playerswap.utils.PSwapUtil;
import it.ivirus.playerswap.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSubcmd extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        if (!sender.hasPermission("playerswap.get")) {
            adventure.sender(sender).sendMessage(Strings.ERROR_NOPERMISSION.getFormattedString());
            return;
        }
        if (!(sender instanceof Player)) {
            adventure.sender(sender).sendMessage(Strings.ERROR_ONLY_PLAYER.getFormattedString());
            return;
        }
        Player player = (Player) sender;

        player.getInventory().addItem(PSwapUtil.getSwapperItem(1));
        adventure.player(player).sendMessage(Strings.INFO_SWAPPER_GET.getFormattedString());

    }
}
