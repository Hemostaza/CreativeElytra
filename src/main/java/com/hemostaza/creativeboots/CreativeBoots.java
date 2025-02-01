package com.hemostaza.creativeboots;

import com.hemostaza.creativeboots.listeners.OnArmorEquip;
import com.hemostaza.creativeboots.listeners.OnInventoryClick;
import com.hemostaza.creativeboots.listeners.OnPlayerJoin;
import com.hemostaza.creativeboots.listeners.PlayerStartFlight;
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

public final class CreativeBoots extends JavaPlugin {

    Logger l = Bukkit.getLogger();

    private Material matesial2Charge;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new UpdateChecker(this,122316);

        try{
            matesial2Charge = Material.valueOf(getConfig().getString("chargeitem").toUpperCase());
        }catch (IllegalArgumentException e){
            l.info("Wrong charge material in config, will be set to default WIND_CHARGE");
            matesial2Charge = Material.WIND_CHARGE;
        }catch (NullPointerException e){
            l.info("There is no config for chargeitem, will be set to default WIND_CHARGE");
            matesial2Charge = Material.WIND_CHARGE;
        }

        new ItemManager(this);

        MainCommands mc = new MainCommands();
        PluginCommand command = getCommand("giveboots");
        PluginCommand command2 = getCommand("premiumhermes");
        if(command!=null){
            command.setExecutor(mc);
        }
        if(command2!=null){
            command2.setExecutor(mc);
        }

        ArmorEquipEvent.registerListener(this);
        getServer().getPluginManager().registerEvents(new OnArmorEquip(this), this);
        getServer().getPluginManager().registerEvents(new PlayerStartFlight(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(this), this);
        // Plugin startup logic

        if(getConfig().getBoolean("cancraft")){
            AddRecipes();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Material getMatesial2Charge() {
        return matesial2Charge;
    }

    public void AddRecipes(){
        NamespacedKey weakBoots = new NamespacedKey(this,"weakhermesboots");
        ShapedRecipe weakBootsRecipe = new ShapedRecipe(weakBoots,ItemManager.cBoots);
        weakBootsRecipe.shape("MEM","MRM","MBM");
        weakBootsRecipe.setIngredient('R',Material.REDSTONE_BLOCK);
        weakBootsRecipe.setIngredient('E',Material.ELYTRA);
        weakBootsRecipe.setIngredient('M',Material.PHANTOM_MEMBRANE);
        weakBootsRecipe.setIngredient('B',Material.LEATHER_BOOTS);
        NamespacedKey strongBoots = new NamespacedKey(this,"stronghermesboots");
        ShapedRecipe strongBootsRecipe = new ShapedRecipe(strongBoots,ItemManager.cSBoots);
        strongBootsRecipe.shape("WEW","SRS","WBW");
        strongBootsRecipe.setIngredient('W',Material.WIND_CHARGE);
        strongBootsRecipe.setIngredient('B',Material.NETHERITE_BOOTS);
        strongBootsRecipe.setIngredient('E',Material.ELYTRA);
        strongBootsRecipe.setIngredient('S',Material.BREEZE_ROD);
        strongBootsRecipe.setIngredient('R',Material.REDSTONE_BLOCK);
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
        //Bukkit.getLogger().info(newFlyingTimer+"");
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
