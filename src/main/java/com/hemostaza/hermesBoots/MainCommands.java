package com.hemostaza.hermesBoots;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("hermes")){
            ItemStack item = ItemManager.cBoots;
            player.getInventory().addItem(item);
        }
        if(cmd.getName().equalsIgnoreCase("premiumhermes")){
            ItemStack item = ItemManager.cSBoots;
            player.getInventory().addItem(item);
        }
        return false;
    }
}
