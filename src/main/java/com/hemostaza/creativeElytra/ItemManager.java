package com.hemostaza.creativeElytra;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    //public static ItemStack cElytra;
    public static ItemStack cBoots;
    public static ItemStack cSBoots;
    public static void init(){
        createWeakBoots();
        createStrongBoots();
    }

    private static void createStrongBoots() {
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;

        ((Damageable) meta).setMaxDamage(30);
        ((Damageable) meta).setDamage(0);
        ((ArmorMeta)meta).setTrim(new ArmorTrim(TrimMaterial.GOLD, TrimPattern.WILD));
        meta.setDisplayName("Premium Hermes Boots");
        List<String> lore = new ArrayList<>();
        lore.add("Allow you to fly like you always wanted");
        lore.add("You can charge it with");
        lore.add("Wind Charge");
        meta.setLore(lore);
        ((Repairable)meta).setRepairCost(50);
        item.setItemMeta(meta);
        cSBoots = item;
    }

    private static void createWeakBoots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;

        ((Damageable) meta).setMaxDamage(30);
        ((Damageable) meta).setDamage(0);
        meta.setDisplayName("Low quality Hermes Boots");
        ((ArmorMeta)meta).setTrim(new ArmorTrim(TrimMaterial.QUARTZ, TrimPattern.SNOUT));
        List<String> lore = new ArrayList<>();
        lore.add("Allow you to fly like you always wanted");
        lore.add("But it has limits");
        meta.setLore(lore);
        ((Repairable)meta).setRepairCost(50);
        item.setItemMeta(meta);
        cBoots = item;
    }
}
