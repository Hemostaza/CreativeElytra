package com.hemostaza.creativeElytra;

import com.hemostaza.creativeElytra.listeners.OnArmorEquip;
import com.hemostaza.creativeElytra.listeners.OnPlayerJoin;
import com.hemostaza.creativeElytra.listeners.PlayerStartFlight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import com.jeff_media.armorequipevent.ArmorEquipEvent;

import java.util.HashMap;
import java.util.logging.Logger;

public final class CreativeElytra extends JavaPlugin {

    Logger l = Bukkit.getLogger();

    @Override
    public void onEnable() {
        ItemManager.init();

        PluginCommand command = getCommand("hermes");
        if(command!=null){
            MainCommands mc = new MainCommands();
            command.setExecutor(mc);
        }

        ArmorEquipEvent.registerListener(this);
        getServer().getPluginManager().registerEvents(new OnArmorEquip(this), this);
        getServer().getPluginManager().registerEvents(new PlayerStartFlight(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        // Plugin startup logic

        AddRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void AddRecipes(){
        NamespacedKey weakBoots = new NamespacedKey(this,"weakhermesboots");
        ShapedRecipe weakBootsRecipe = new ShapedRecipe(weakBoots,ItemManager.cBoots);
        weakBootsRecipe.shape("MMM","WBW","MMM");
        weakBootsRecipe.setIngredient('M',Material.PHANTOM_MEMBRANE);
        weakBootsRecipe.setIngredient('W',Material.WIND_CHARGE);
        weakBootsRecipe.setIngredient('B',Material.LEATHER_BOOTS);
        NamespacedKey strongBoots = new NamespacedKey(this,"stronghermesboots");
        ShapedRecipe strongBootsRecipe = new ShapedRecipe(strongBoots,ItemManager.cSBoots);
        strongBootsRecipe.shape("WBW");
        strongBootsRecipe.setIngredient('W',Material.WIND_CHARGE);
        strongBootsRecipe.setIngredient('B',Material.NETHERITE_BOOTS);
        Bukkit.addRecipe(weakBootsRecipe);
        Bukkit.addRecipe(strongBootsRecipe);
    }

    private static HashMap<String, FlyingTimer> activeTimer = new HashMap<String, FlyingTimer>();

    public void StartFlyingTimer(Player player){
        FlyingTimer aft = activeTimer.get(player.getName());
        //l.info("Start timer");
        if(aft!=null){
            aft.cancel();
            activeTimer.remove(player.getName());
        }
        FlyingTimer newFlyingTimer = new FlyingTimer(this, player);
        Bukkit.getLogger().info(newFlyingTimer+"");
        activeTimer.put(player.getName(),newFlyingTimer);
    }
    public void StopFlyingTimer(Player player){
        FlyingTimer aft = activeTimer.get(player.getName());
        //l.info("stop timer");
        if(aft!=null){
            //l.info("Był timer?");
            aft.cancel();
            activeTimer.remove(player.getName());
        }
    }
}
