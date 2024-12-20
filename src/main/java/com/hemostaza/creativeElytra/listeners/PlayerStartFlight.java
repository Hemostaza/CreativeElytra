package com.hemostaza.creativeElytra.listeners;

import com.hemostaza.creativeElytra.CreativeElytra;
import com.hemostaza.creativeElytra.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.logging.Logger;

public class PlayerStartFlight implements Listener {

    CreativeElytra plugin;
    Logger l = Bukkit.getLogger();

    public PlayerStartFlight(CreativeElytra plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void onToggelFlightEvent(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        boolean haveFlyingItem = false;
        try {
            haveFlyingItem = player.getInventory().getBoots().getItemMeta().getLore().getFirst().equals(ItemManager.cBoots.getItemMeta().getLore().getFirst());
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
