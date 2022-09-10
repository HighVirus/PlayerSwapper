package it.ivirus.playerswap.commands;

import it.ivirus.playerswap.PlayerSwap;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class SubCommand {
    protected final PlayerSwap plugin = PlayerSwap.getInstance();
    protected final FileConfiguration config = plugin.getConfig();
    protected final FileConfiguration langConfig = plugin.getLangConfig();
    protected final BukkitAudiences adventure = plugin.getAdventure();

    public abstract void onCommand(CommandSender sender, Command command, String[] args);
}
