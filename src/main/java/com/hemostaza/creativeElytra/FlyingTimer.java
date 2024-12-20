package com.hemostaza.creativeElytra;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyingTimer extends BukkitRunnable {

    CreativeElytra plugin;
    ItemStack boots;
    Damageable dmg;

    int maxDamage;
    int damage;
    int time;
    Player player;
    public FlyingTimer(CreativeElytra plugin, Player player){
        this.player = player;
        this.plugin = plugin;
        this.boots = player.getInventory().getBoots();
        assert boots!=null;
        dmg = (Damageable) boots.getItemMeta();
        if(dmg==null) return;
        if(!dmg.hasMaxDamage()) dmg.setMaxDamage(30);
        maxDamage = dmg.getMaxDamage();
        damage = dmg.getDamage();
        runTaskTimer(this.plugin, 1, 20);
    }

    @Override
    public void run() {
        dmg.setDamage(dmg.getDamage()+1);
        boots.setItemMeta(dmg);
        Bukkit.getLogger().info("damage on item "+dmg.getDamage());

        if(dmg.getDamage()>=maxDamage){
            this.cancel();
            player.setFlying(false);
            player.setAllowFlight(false);
        }
    }
}
