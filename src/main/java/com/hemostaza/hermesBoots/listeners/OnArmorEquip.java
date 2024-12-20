package com.hemostaza.hermesBoots.listeners;

import com.hemostaza.hermesBoots.HermesBoots;
import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Logger;

public class OnArmorEquip implements Listener {

    Logger l = Bukkit.getLogger();

    HermesBoots plugin;
    FileConfiguration config;

    public OnArmorEquip(HermesBoots plugin) {
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
            isEquipFLyingItem = eqpItem.getItemMeta().getLore().getFirst().equals(config.getString("firstline"));
        } catch (NullPointerException e) {
            //l.info("Gdzieś ktoś jest nulerm");
        }
        try {
            isUnequipFlyingItem = unqItem.getItemMeta().getLore().getFirst().equals(config.getString("firstline"));
        } catch (NullPointerException e) {
            //l.info("Gdzieś ktoś jest nulerm");
        }
        if (!isUnequipFlyingItem && !isEquipFLyingItem) {
            //l.info("To nie jest latajacy item");
            return;
        }

        if (isUnequipFlyingItem) {
            //Material unqMat = unqItem.getType();
            //if (unqMat.equals(Material.LEATHER_BOOTS) || unqMat.equals(Material.NETHERITE_BOOTS)) {
                //l.info("Zdejowanie buta");

                player.setFlying(false);
                player.setAllowFlight(false);
                plugin.StopFlyingTimer(player);
            //}
        }
        if (isEquipFLyingItem) {
            //Material eqpMat = eqpItem.getType();
            //if (eqpMat.equals(Material.LEATHER_BOOTS) || eqpMat.equals(Material.NETHERITE_BOOTS)) {
                //l.info("zakłądanie buty");
                player.setAllowFlight(true);
                if(!((Entity)player).isOnGround()){
                    player.setFlying(true);
                    //plugin.StartFlyingTimer(player);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.StartFlyingTimer(player), 1);
                }
            //}
        }
    }
}
