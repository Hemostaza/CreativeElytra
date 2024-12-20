package com.hemostaza.hermesBoots.listeners;

import com.hemostaza.hermesBoots.HermesBoots;
import com.hemostaza.hermesBoots.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.logging.Logger;

public class OnInventoryClick implements Listener {

    HermesBoots plugin;
    FileConfiguration config;
    Logger l = Bukkit.getLogger();
    public OnInventoryClick(HermesBoots plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!config.getBoolean("cancharge")) return;
        ItemStack held = event.getCursor();
        ItemStack slot = event.getCurrentItem();
        //jak nic nie wzial to jebac
        if(held==null)return;
        //jak nie item do ladowania to jebac
        Material material;
        String configMat = config.getString("chargeitem");
        if(configMat==null || configMat.isEmpty()){
            material = Material.WIND_CHARGE;
        }else{
            try{
                material = Material.valueOf(configMat.toUpperCase());
            }catch (IllegalArgumentException e){
                Bukkit.getLogger().info("Wrong charge material in config");
                material=Material.WIND_CHARGE;
            }
        }
        if(!held.getType().equals(material)) return;
        //jak nie klikniecie PPM to jebac
        if(!event.getClick().equals(ClickType.RIGHT)) return;
        //jak w slocie nie ma netherowych lataczy
        if(slot==null)return;
        if(slot.getItemMeta()==null)return;
        if(slot.getItemMeta().getLore()==null) return;
        if(!slot.getItemMeta().getLore().equals(ItemManager.cSBoots.getItemMeta().getLore())) return;

        Damageable bootsMeta = (Damageable) slot.getItemMeta();
        int currentDmg = bootsMeta.getDamage();
        if(currentDmg==0) return;
        event.setCancelled(true);
        //int efficiency = config.getInt("chargefficiency");
//        if(efficiency==0){
//            l.info("config chargeefficiency is 0 set to default 200");
//            efficiency = 200;
//        }
        int replenish = currentDmg-config.getInt("chargefficiency");
        if(replenish<=0){
            replenish=0;
        }
        bootsMeta.setDamage(replenish);
        slot.setItemMeta(bootsMeta);

        held.setAmount(held.getAmount()-1);
    }
}
