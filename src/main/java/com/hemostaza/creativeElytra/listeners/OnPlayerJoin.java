package com.hemostaza.creativeElytra.listeners;

import com.hemostaza.creativeElytra.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerJoin implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ItemStack boots = player.getInventory().getBoots();
        if(boots==null)return;
        if(!boots.getType().equals(Material.LEATHER_BOOTS)) return;
        boolean haveBoots = false;
        try{
            haveBoots = boots.getItemMeta().getLore().getFirst().equals(ItemManager.cBoots.getItemMeta().getLore().getFirst());
        }catch (NullPointerException e){
            Bukkit.getLogger().info("don't have boots");
            return;
        }
        if(!haveBoots) {
            Bukkit.getLogger().info(haveBoots+" ma buty");
            return;
        }
        player.setAllowFlight(true);
        if(!((Entity)player).isOnGround()){

            Bukkit.getLogger().info("nie na ziemi");
            player.setFlying(true);
        }
    }
}
