package it.ivirus.playerswap.commands;

import it.ivirus.playerswap.PlayerSwap;
import it.ivirus.playerswap.commands.subcommands.GetSubcmd;
import it.ivirus.playerswap.commands.subcommands.GiveSubcmd;
import it.ivirus.playerswap.commands.subcommands.HelpSubcmd;
import it.ivirus.playerswap.commands.subcommands.ReloadSubcmd;
import it.ivirus.playerswap.utils.Strings;
import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PSwapCommandHandler implements CommandExecutor {

    private final Map<String, SubCommand> commands = new HashMap<>();
    private final PlayerSwap plugin;
    private final BukkitAudiences adventure;

    public PSwapCommandHandler(PlayerSwap plugin) {
        this.plugin = plugin;
        this.adventure = plugin.getAdventure();
        registerCommand("reload", new ReloadSubcmd());
        registerCommand("help", new HelpSubcmd());
        registerCommand("get", new GetSubcmd());
        registerCommand("give", new GiveSubcmd());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        if (args.length == 0 || !commands.containsKey(args[0].toLowerCase())) {
            adventure.sender(sender).sendMessage(Strings.getFormattedString("&3-------------------------------"));
            adventure.sender(sender).sendMessage(Strings.getFormattedString("&3Plugin developer: &fiVirus_"));
            adventure.sender(sender).sendMessage(Strings.getFormattedString("&3Telegram: &fhttps://t.me/HoxijaChannel"));
            adventure.sender(sender).sendMessage(Strings.getFormattedString("&3Discord: &fhttps://discord.io/hoxija"));
            adventure.sender(sender).sendMessage(Strings.getFormattedString("&3-------------------------------"));
            return true;
        }

        commands.get(args[0].toLowerCase()).onCommand(sender, command, args);
        return true;
    }

    private void registerCommand(String cmd, SubCommand subCommand) {
        commands.put(cmd, subCommand);
    }
}
