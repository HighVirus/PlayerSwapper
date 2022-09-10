package it.ivirus.playerswap.commands;

import it.ivirus.playerswap.PlayerSwap;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CommandTabCompleter implements TabCompleter {
    private final PlayerSwap plugin;

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        CommandExecutor commandExecutor = plugin.getCommand(command.getName()).getExecutor();
        PSwapCommandHandler commandHandler = (PSwapCommandHandler) commandExecutor;
        List<String> subcommands = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], commandHandler.getCommands().keySet(), subcommands);
            return subcommands;
        }

        return null;
    }
}
