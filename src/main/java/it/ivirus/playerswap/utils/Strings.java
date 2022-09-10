package it.ivirus.playerswap.utils;

import it.ivirus.playerswap.PlayerSwap;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

public enum Strings {

    PREFIX("prefix"),
    INFO_HELP("info.help"),
    INFO_SWAPPER_GET("info.swapper-get"),
    INFO_SWAPPER_GIVE("info.swapper-give"),
    INFO_RELOAD("info.reload"),
    INFO_SHOOTER_SWAPPER("info.shooter-swap"),
    INFO_TARGET_SWAPPER("info.target-swapped"),
    ERROR_COOLDOWN("errors.cooldown"),
    ERROR_USAGE_GIVE("errors.give-usage"),
    ERROR_TARGET_OFFLINE("errors.target-offline"),
    ERROR_ONLY_PLAYER("errors.only-player"),
    ERROR_INVALID_VALUE("errors.invalid-value"),
    ERROR_BLOCKED_COMMAND("errors.blocked-command"),
    ERROR_NOPERMISSION("errors.no-permission");


    private final PlayerSwap plugin = PlayerSwap.getInstance();
    @Getter
    private final String path;

    Strings(String path) {
        this.path = path;
    }

    private String getPrefix() {
        return plugin.getLangConfig().getString(PREFIX.getPath());
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (plugin.getLangConfig().isList(path)) {
            for (String s : plugin.getLangConfig().getStringList(path)) {
                stringBuilder.append(s + "\n");
            }
        } else {
            return plugin.getLangConfig().getString(path).replaceAll("%prefix%", getPrefix());
        }
        return stringBuilder.toString().replaceAll("%prefix%", getPrefix());
    }

    public Component getFormattedString() {
        if (getString().contains("&"))
            return LegacyComponentSerializer.legacyAmpersand().deserialize(getString());
        else
            return MiniMessage.miniMessage().deserialize(getString());
    }

    public static Component getFormattedString(String string) {
        if (string.contains("&"))
            return LegacyComponentSerializer.legacyAmpersand().deserialize(string);
        else
            return MiniMessage.miniMessage().deserialize(string);
    }

    public static String getOldFormatString(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
