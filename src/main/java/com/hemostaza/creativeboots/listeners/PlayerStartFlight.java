package com.hemostaza.creativeboots.listeners;

import com.hemostaza.creativeboots.CreativeBoots;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Logger;

public class PlayerStartFlight implements Listener {

    CreativeBoots plugin;
    FileConfiguration config;
    Logger l = Bukkit.getLogger();

    public PlayerStartFlight(CreativeBoots plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    void onToggelFlightEvent(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        boolean haveFlyingItem = false;
        try {
            PersistentDataContainer container = player.getInventory().getBoots().getItemMeta().getPersistentDataContainer();
            if(container.has(plugin.getKey(), PersistentDataType.STRING)) {
                String type = container.get(plugin.getKey(), PersistentDataType.STRING);
                haveFlyingItem = true;
            }
            //haveFlyingItem = player.getInventory().getBoots().getItemMeta().getLore().getFirst().equals(config.getString("firstline"));
        } catch (NullPointerException e) {
            return;
        }
        if (!haveFlyingItem) {
            //l.info("Jak "+player.getName()+" to zrobił?");
            plugin.StopFlyingTimer(player);
            player.setFlying(false);
            player.setAllowFlight(false);
            return;
        }
        //ma latajacy item
        ItemStack boots = player.getInventory().getBoots();
        Damageable dmg = (Damageable) boots.getItemMeta();
        if(dmg.getDamage()>=dmg.getMaxDamage()){
            //l.info("Zjebane buty");
            player.setFlying(false);
            player.setAllowFlight(false);
            return;
        }
        //l.info("Zalozone buty: "+boots.getType());
        if (event.isFlying()) {
            plugin.StartFlyingTimer(player);
        }else {
            plugin.StopFlyingTimer(player);
        }
    }
}
