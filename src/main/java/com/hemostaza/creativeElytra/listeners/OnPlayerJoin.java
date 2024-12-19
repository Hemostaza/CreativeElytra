package com.hemostaza.creativeElytra.listeners;

import com.hemostaza.creativeElytra.ItemManager;
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
        ItemStack chest = player.getInventory().getChestplate();
        if(chest==null)return;
        if(!chest.getType().equals(Material.ELYTRA)) return;
        boolean haveElytra = false;
        try{
            haveElytra = chest.getItemMeta().getLore().getFirst().equals(ItemManager.cElytra.getItemMeta().getLore());
        }catch (NullPointerException e){

        }
        player.setAllowFlight(true);
        if(!((Entity)player).isOnGround()){
            player.setFlying(true);
        }
    }
}
