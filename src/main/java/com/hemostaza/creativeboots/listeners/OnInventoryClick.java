package com.hemostaza.creativeboots.listeners;

import com.hemostaza.creativeboots.CreativeBoots;
import com.hemostaza.creativeboots.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Logger;

public class OnInventoryClick implements Listener {

    CreativeBoots plugin;
    FileConfiguration config;
    Logger l = Bukkit.getLogger();

    public OnInventoryClick(CreativeBoots plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //if(!config.getBoolean("cancharge")) return;
        ItemStack held = event.getCursor();
        ItemStack slot = event.getCurrentItem();

        //Sprawdzenie slotowego itema
        if (slot == null) return;
        if (slot.getItemMeta() == null) return;
        if (slot.getItemMeta().getLore() == null) return;

        PersistentDataContainer container = slot.getItemMeta().getPersistentDataContainer();
        if (container.has(plugin.getKey(), PersistentDataType.STRING)) {

            plugin.UpdateBoots(slot);

            //jak nic nie wzial to jebac
            if (held == null) return;
            if (held.getType().equals(Material.AIR)) return;
            //jak nie item do ladowania to jebac
            if (!held.getType().equals(plugin.getMatesial2Charge())) return;
            //jak nie klikniecie PPM to jebac
            if (!event.getClick().equals(ClickType.RIGHT)) return;

            String type = container.get(plugin.getKey(), PersistentDataType.STRING);
            assert type != null;
            if (!type.equals("premiumboots")) {
                return;
            }
            Damageable bootsMeta = (Damageable) slot.getItemMeta();
            int currentDmg = bootsMeta.getDamage();
            //buty nie sa zepsute to jebac
            if (currentDmg == 0) return;
            event.setCancelled(true);
            int replenish = currentDmg - config.getInt("chargefficiency");
            if (replenish <= 0) {
                replenish = 0;
            }
            bootsMeta.setDamage(replenish);
            slot.setItemMeta(bootsMeta);

            held.setAmount(held.getAmount() - 1);
        }


    }
}
