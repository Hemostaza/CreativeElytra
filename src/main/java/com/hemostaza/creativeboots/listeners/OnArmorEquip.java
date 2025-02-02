package com.hemostaza.creativeboots.listeners;

import com.hemostaza.creativeboots.CreativeBoots;
import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OnArmorEquip implements Listener {

    Logger l = Bukkit.getLogger();

    CreativeBoots plugin;
    FileConfiguration config;

    public OnArmorEquip(CreativeBoots plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    void onElytraEquip(ArmorEquipEvent event) {
        ItemStack eqpItem = event.getNewArmorPiece();
        ItemStack unqItem = event.getOldArmorPiece();
        Player player = event.getPlayer();

        boolean isEquipFLyingItem = false;
        boolean isUnequipFlyingItem = false;
        try {
            PersistentDataContainer container = eqpItem.getItemMeta().getPersistentDataContainer();
            if (container.has(plugin.getKey(), PersistentDataType.STRING)) {
                //String type = container.get(plugin.getKey(), PersistentDataType.STRING);
                l.log(Level.WARNING,"zakladany - Jest type String: " );
                isEquipFLyingItem = true;
                plugin.UpdateBoots(eqpItem);

            } else {
                //l.info("Its not flying item by key value");
            }
        } catch (NullPointerException e) {
            //l.info("Gdzieś ktoś jest nulerm");
        }
        try {
            PersistentDataContainer container = unqItem.getItemMeta().getPersistentDataContainer();
            if (container.has(plugin.getKey(), PersistentDataType.STRING)) {
                String type = container.get(plugin.getKey(), PersistentDataType.STRING);
                //l.info("zdejmowany - Jest type String: " + type);
                isUnequipFlyingItem = true;
            } else {
                //l.info("Its not flying item by key value");
            }
        } catch (NullPointerException e) {
            //l.info("Gdzieś ktoś jest nulerm");
        }
        if (!isUnequipFlyingItem && !isEquipFLyingItem) {
            //l.info("To nie jest latajacy item");
            return;
        }

        if (isUnequipFlyingItem) {
            //l.info("Zdejowanie buta");

            player.setFlying(false);
            player.setAllowFlight(false);
            plugin.StopFlyingTimer(player);

        }
        if (isEquipFLyingItem) {
            //l.info("zakłądanie buty");
            player.setAllowFlight(true);
            if (!((Entity) player).isOnGround()) {
                player.setFlying(true);
                //plugin.StartFlyingTimer(player);
                Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.StartFlyingTimer(player), 1);
            }
        }
    }
}
