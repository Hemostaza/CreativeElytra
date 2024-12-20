package com.hemostaza.hermesBoots.listeners;

import com.hemostaza.hermesBoots.HermesBoots;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.logging.Logger;

public class OnPlayerJoin implements Listener {

    HermesBoots plugin;
    FileConfiguration config;
    Logger l = Bukkit.getLogger();
    public OnPlayerJoin(HermesBoots plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ItemStack boots = player.getInventory().getBoots();
        if(boots==null)return;
        if(!boots.getType().equals(Material.LEATHER_BOOTS) && !boots.getType().equals(Material.NETHERITE_BOOTS)) return;
        boolean haveBoots = false;
        try{
            haveBoots = boots.getItemMeta().getLore().getFirst().equals(config.getString("firstline"));
        }catch (NullPointerException e){
            //Bukkit.getLogger().info("don't have boots");
            return;
        }
        int maxDamage = ((Damageable)boots.getItemMeta()).getMaxDamage();
        if(!haveBoots || ((Damageable)boots.getItemMeta()).getDamage()>=maxDamage) {
            //Bukkit.getLogger().info("nie ma butow albo sa zjebane");
            return;
        }
        player.setAllowFlight(true);
        if(!((Entity)player).isOnGround()){
            //Bukkit.getLogger().info("nie na ziemi");
            player.setFlying(true);
            plugin.StartFlyingTimer(player);
        }
    }
}
