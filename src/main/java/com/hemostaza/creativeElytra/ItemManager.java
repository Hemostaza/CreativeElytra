package com.hemostaza.creativeElytra;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    //public static ItemStack cElytra;
    public static ItemStack cBoots;
    public static void init(){
        createCreativeBoots();
    }
    private static void createCreativeBoots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;

        ((Damageable) meta).setMaxDamage(10);

        meta.setDisplayName("Hermes Boots");
        List<String> lore = new ArrayList<>();
        lore.add("Allow you to fly like you always wanted");
        meta.setLore(lore);
        ((Repairable)meta).setRepairCost(50);
        item.setItemMeta(meta);
        cBoots = item;
    }
}
