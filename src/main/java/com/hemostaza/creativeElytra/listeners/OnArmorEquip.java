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
            isEquippingElytra = eqpItem.getItemMeta().getLore().getFirst().equals(ItemManager.cElytra.getItemMeta().getLore().getFirst());
        }catch (NullPointerException e){
            l.info("Gdzieś ktoś jest nulerm");
        }
        try{
            isUnequippingElytra = unqItem.getItemMeta().getLore().getFirst().equals(ItemManager.cElytra.getItemMeta().getLore().getFirst());
        }catch (NullPointerException e){
            l.info("Gdzieś ktoś jest nulerm");
        }
        l.info("a ja jestem po trycatchu");
        if(isEquippingElytra){
            l.info("zakłądanie elytry bez zdejmowania elytry");
            player.setAllowFlight(true);
            player.setFlying(true);
        }else if(isUnequippingElytra){
            l.info("Zdejowanie elytry bez zakladania elytry");
            player.setFlying(false);
            player.setAllowFlight(false);
        }
//
//        if(unqItem==null){
//            l.info("Poprzedni item to null");
//            return;
//        }
//        ItemMeta unqMeta = unqItem.getItemMeta();
//        if(unqMeta==null){
//            l.info("Meta poprzedniego itema to null");
//            return;
//        }
//
//        if (eqpItem == null) {
//            //sprawdza czy porpzedni był nullem - raczej nie - ale niech zostanie
//            if (unqItem == null) return;
//            ItemMeta unqMeta = unqItem.getItemMeta();
//            //jak poprzedni item ne miał mety to wyejbuje
//            if (unqMeta == null) return;
//            //jak poprzedni nie miał lora to wyjebuje
//            if (unqMeta.getLore() == null) return;
//            //jak poprzedni item nie był celytrą to wyjebuje
//            boolean isGoodMeta = false;
//            try {
//                isGoodMeta = unqMeta.getLore().getFirst().equals(ItemManager.cElytra.getItemMeta().getLore().getFirst());
//            } catch (NullPointerException e) {
//                l.info("error cos someting is null: " + e);
//                //return;
//            }
//            if (!isGoodMeta) return;
//
//            //zdejmowanie elytry
//            l.info("Zdemowanie elytry");
//            player.setFlying(false);
//            player.setAllowFlight(false);
//
//        }//else jest coś ekwipowanego
//        else {
//            //jak to nawet nie jest elytra to jebac
//            l.info(eqpItem + " zakladany item");
//            if (!eqpItem.getType().equals(Material.ELYTRA)) {
//                l.info("Not elytra");
//                return;
//            }
//            ItemMeta eqpMeta = eqpItem.getItemMeta();
//            //jak nie ma mety
//            if (eqpMeta == null) {
//                l.info("eqpMeta is null");
//                return;
//            }
////            if(eqpMeta.getLore()==null){
////                return;
////            }
//            //jak meta sie nie zgadza
//            boolean isGoodMetaEqup = false;
//            try {
//                isGoodMetaEqup = eqpMeta.getLore().getFirst().equals(ItemManager.cElytra.getItemMeta().getLore().getFirst());
//            } catch (NullPointerException e) {
//                l.info("error cos idk?: " + e);
//                return;
//            }
//            if (!isGoodMetaEqup) {
//                l.info("wrong meta");
//                return;
//            }
//
//            //zakladanie elytry
//            l.info("Zakładamy eletyr");
//            player.setAllowFlight(true);
//            player.setFlying(true);
//
//        }
    }
}
