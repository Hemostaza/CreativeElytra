package com.hemostaza.creativeElytra.listeners;

import com.hemostaza.creativeElytra.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class PlayerStartFlight implements Listener {

    @EventHandler
    void onToggelFlightEvent(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        boolean haveElytra = false;
        try {
            haveElytra = player.getInventory().getBoots().getItemMeta().getLore().equals(ItemManager.cBoots.getItemMeta().getLore());
        } catch (NullPointerException e) {
            return;
        }
        if (!haveElytra) {
            player.setAllowFlight(false);
            player.setFlying(false);
        }

        ItemStack boots = player.getInventory().getBoots();
        Damageable dmg = (Damageable) boots.getItemMeta();

        if (event.isFlying()) {
            dmg.setDamage(dmg.getDamage() + 1);
            if (dmg.getDamage() > 10) {
                player.getInventory().getBoots().setAmount(0);
                player.setAllowFlight(false);
                player.setFlying(false);
            }
            boots.setItemMeta(dmg);
        }else {
            if(dmg.getDamage()==10){
                player.getInventory().getBoots().setAmount(0);
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }
}
