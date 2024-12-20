package com.hemostaza.creativeElytra.listeners;

import com.hemostaza.creativeElytra.CreativeElytra;
import com.hemostaza.creativeElytra.ItemManager;
import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import java.util.logging.Logger;

public class OnArmorEquip implements Listener {

    Logger l = Bukkit.getLogger();

    CreativeElytra plugin;

    public OnArmorEquip(CreativeElytra plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onElytraEquip(ArmorEquipEvent event) {
        ItemStack eqpItem = event.getNewArmorPiece();
        ItemStack unqItem = event.getOldArmorPiece();
        Player player = event.getPlayer();

        boolean isEquippingElytra = false;
        boolean isUnequippingElytra = false;
        try{
            isEquippingElytra = eqpItem.getItemMeta().getLore().getFirst().equals(ItemManager.cBoots.getItemMeta().getLore().getFirst());
        }catch (NullPointerException e){
            l.info("Gdzieś ktoś jest nulerm");
        }
        try{
            isUnequippingElytra = unqItem.getItemMeta().getLore().getFirst().equals(ItemManager.cBoots.getItemMeta().getLore().getFirst());
        }catch (NullPointerException e){
            l.info("Gdzieś ktoś jest nulerm");
        }

        if(isEquippingElytra && eqpItem.getType().equals(Material.LEATHER_BOOTS)){
            l.info("zakłądanie elytry bez zdejmowania elytry");
            player.setAllowFlight(true);
            //player.setFlying(true);
        }else if(isUnequippingElytra && unqItem.getType().equals(Material.LEATHER_BOOTS)){
            l.info("Zdejowanie elytry bez zakladania elytry");
            player.setFlying(false);
            player.setAllowFlight(false);
        }
    }
}
