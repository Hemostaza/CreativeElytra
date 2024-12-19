package com.hemostaza.creativeElytra;

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

        if(cmd.getName().equalsIgnoreCase("celytra")){

            ItemStack item = ItemManager.cElytra;
            if(args.length>1){
                int amount = Integer.parseInt(args[1]);
                item.setAmount(amount);
            }
            player.getInventory().addItem(item);
        }
        return false;
    }
}
