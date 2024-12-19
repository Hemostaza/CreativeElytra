package com.hemostaza.creativeElytra;

import com.hemostaza.creativeElytra.listeners.OnArmorEquip;
import com.hemostaza.creativeElytra.listeners.OnPlayerJoin;
import com.hemostaza.creativeElytra.listeners.PlayerStartFlight;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import com.jeff_media.armorequipevent.ArmorEquipEvent;

public final class CreativeElytra extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemManager.init();

        PluginCommand command = getCommand("celytra");
        if(command!=null){
            MainCommands mc = new MainCommands();
            command.setExecutor(mc);
        }

        ArmorEquipEvent.registerListener(this);
        getServer().getPluginManager().registerEvents(new OnArmorEquip(this), this);
        getServer().getPluginManager().registerEvents(new PlayerStartFlight(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
