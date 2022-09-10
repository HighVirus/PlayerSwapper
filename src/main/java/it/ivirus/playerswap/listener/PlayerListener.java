package it.ivirus.playerswap.listener;

import de.tr7zw.changeme.nbtapi.NBTItem;
import it.ivirus.playerswap.PlayerSwap;
import it.ivirus.playerswap.data.PlayerSwapData;
import it.ivirus.playerswap.utils.Strings;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

@RequiredArgsConstructor
public class PlayerListener implements Listener {
    private final PlayerSwapData playerSwapData = PlayerSwapData.getInstance();
    private final PlayerSwap plugin;


    @EventHandler
    public void onPlayerThrowSwapper(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof Snowball)) return;
        ProjectileSource shooter = event.getEntity().getShooter();

        if (!(shooter instanceof Player)) return;
        Player pShooter = (Player) shooter;

        ItemStack item = pShooter.getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasNBTData() || !nbtItem.hasKey("PlayerSwapper")) return;

        if (playerSwapData.getCooldown().containsKey(pShooter.getUniqueId())) {
            int countdownTime = plugin.getConfig().getInt("cooldown");
            long time = playerSwapData.getCooldown().get(pShooter.getUniqueId()) / 1000L + countdownTime - System.currentTimeMillis() / 1000L;
            if (time > 0L) {
                plugin.getAdventure().player(pShooter).sendMessage(Strings.getFormattedString(Strings.ERROR_COOLDOWN.getString().replaceAll("%cooldown%", String.valueOf(time))));
                event.setCancelled(true);
                return;
            }
        }

        playerSwapData.getShooters().add(pShooter.getUniqueId());
        playerSwapData.getCooldown().put(pShooter.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity eDamager = event.getDamager();

        if (!(eDamager instanceof Snowball)) return;
        if (!(event.getEntity() instanceof Player)) return;

        Player pDamaged = (Player) event.getEntity();

        Snowball snowball = (Snowball) eDamager;
        ProjectileSource shooter = snowball.getShooter();

        if (!(shooter instanceof Player)) return;
        Player pShooter = (Player) shooter;

        if (!playerSwapData.getShooters().contains(pShooter.getUniqueId())) return;

        Location locP1 = pDamaged.getLocation();
        pDamaged.teleport(pShooter.getLocation());
        pDamaged.playSound(pDamaged.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0F, 1.0F);
        pShooter.teleport(locP1);
        pShooter.playSound(pShooter.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0F, 1.0F);
        plugin.getAdventure().player(pShooter).sendMessage(Strings.getFormattedString(Strings.INFO_SHOOTER_SWAPPER.getString().replaceAll("%target_name%", pDamaged.getName())));
        plugin.getAdventure().player(pDamaged).sendMessage(Strings.getFormattedString(Strings.INFO_TARGET_SWAPPER.getString().replaceAll("%shooter_name%", pShooter.getName())));
    }
}
