package it.ivirus.playerswap;

import it.ivirus.playerswap.commands.CommandTabCompleter;
import it.ivirus.playerswap.commands.PSwapCommandHandler;
import it.ivirus.playerswap.listener.PlayerListener;
import it.ivirus.playerswap.utils.PSwapUtil;
import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class PlayerSwap extends JavaPlugin {

    @Getter
    private static PlayerSwap instance;
    private File langFile;
    private FileConfiguration langConfig;
    private BukkitAudiences adventure;

    @Override
    public void onEnable() {
        instance = this;
        this.adventure = BukkitAudiences.create(this);
        this.saveDefaultConfig();
        this.createLangFile("en_US", "it_IT");
        this.loadLangConfig();
        getCommand("playerswap").setExecutor(new PSwapCommandHandler(this));
        getCommand("playerswap").setTabCompleter(new CommandTabCompleter(this));
        PSwapUtil.loadListeners(this, new PlayerListener(this));

        int pluginId = 16398;
        new Metrics(this, pluginId);

        getLogger().info("---------------------------------------");
        getLogger().info("PlayerSwap by iVirus_");
        getLogger().info("Version: " + this.getDescription().getVersion());
        getLogger().info("Discord support: https://discord.io/hoxija");
        getLogger().info("Telegram channel: https://t.me/HoxijaChannel");
        getLogger().info("Plugin is ready!");
        getLogger().info("---------------------------------------");
    }

    @Override
    public void onDisable() {
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    private void createLangFile(String... names) {
        for (String name : names) {
            if (!new File(getDataFolder(), "lang" + File.separator + name + ".yml").exists()) {
                saveResource("lang" + File.separator + name + ".yml", false);
            }
        }
    }

    public void loadLangConfig() {
        langFile = new File(getDataFolder(), "lang" + File.separator + getConfig().getString("lang") + ".yml");
        if (!langFile.exists()) {
            langFile = new File(getDataFolder(), "lang" + File.separator + "en_US.yml");
        }
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

}
