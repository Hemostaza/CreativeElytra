package com.hemostaza.creativeElytra;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack cElytra;
    public static void init(){
        createCreativeElytra();
    }
    private static void createCreativeElytra(){
        ItemStack item = new ItemStack(Material.ELYTRA,1);
        ItemMeta meta = item.getItemMeta();
        assert meta!=null;

        ((Damageable) meta).setMaxDamage(10);

        meta.setDisplayName("Creative Elytra");
        List<String> lore = new ArrayList<>();
        lore.add("Allow you to fly like you always wanted");
        meta.setLore(lore);
        item.setItemMeta(meta);

        cElytra = item;
    }
}
