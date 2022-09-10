package it.ivirus.playerswap.utils;

import de.tr7zw.changeme.nbtapi.NBTItem;
import it.ivirus.playerswap.PlayerSwap;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PSwapUtil {

    private static final PlayerSwap plugin = PlayerSwap.getInstance();

    public static void loadListeners(JavaPlugin plugin, Listener... listeners) {
        for (Listener l : listeners) {
            Bukkit.getPluginManager().registerEvents(l, plugin);
        }
    }

    public static ItemStack getSwapperItem(int amount) {
        ItemStack itemStack = new ItemStack(Material.SNOWBALL, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Strings.getOldFormatString(LegacyComponentSerializer.legacyAmpersand().serialize(Strings.getFormattedString(PlayerSwap.getInstance().getLangConfig().getString("item.name")))));
        itemMeta.setCustomModelData(plugin.getConfig().getInt("item.customModelData"));
        List<String> lore = new ArrayList<>();
        for (String s : plugin.getLangConfig().getStringList("item.lore")) {
            lore.add(Strings.getOldFormatString(LegacyComponentSerializer.legacyAmpersand().serialize(Strings.getFormattedString(s))));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setBoolean("PlayerSwapper", true);
        nbtItem.applyNBT(itemStack);

        return itemStack;
    }
}
