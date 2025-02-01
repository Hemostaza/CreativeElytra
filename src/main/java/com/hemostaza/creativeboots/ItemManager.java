package com.hemostaza.creativeboots;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.List;

public class ItemManager {
    //public static ItemStack cElytra;
    public static ItemStack cBoots;
    public static ItemStack cSBoots;

    CreativeBoots plugin;
    FileConfiguration config;

    public ItemManager(CreativeBoots plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
        createWeakBoots();
        createStrongBoots();
    }

    private void createStrongBoots() {
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP,ItemFlag.HIDE_ARMOR_TRIM,ItemFlag.HIDE_ATTRIBUTES);

        ((Damageable) meta).setMaxDamage(config.getInt("premiumboots.durability"));
        ((Damageable) meta).setDamage(0);
        ((ArmorMeta)meta).setTrim(new ArmorTrim(TrimMaterial.REDSTONE, TrimPattern.WILD));
        meta.setDisplayName(config.getString("premiumboots.name"));
        List<String> lore = config.getStringList("premiumboots.lore");
        lore.addFirst(config.getString("firstline"));
        lore.addLast(plugin.getMatesial2Charge().name().replace("_"," "));
        meta.setLore(lore);
        ((Repairable)meta).setRepairCost(50);
        item.setItemMeta(meta);
        cSBoots = item;
    }

    private void createWeakBoots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP,ItemFlag.HIDE_ARMOR_TRIM,ItemFlag.HIDE_ATTRIBUTES);

        ((Damageable) meta).setMaxDamage(config.getInt("boots.durability"));
        ((Damageable) meta).setDamage(0);
        ((ArmorMeta)meta).setTrim(new ArmorTrim(TrimMaterial.REDSTONE, TrimPattern.SNOUT));
        meta.setDisplayName(config.getString("boots.name"));
        List<String> lore = config.getStringList("boots.lore");
        lore.addFirst(config.getString("firstline"));
        meta.setLore(lore);
        ((Repairable)meta).setRepairCost(50);
        item.setItemMeta(meta);
        cBoots = item;
    }
}
