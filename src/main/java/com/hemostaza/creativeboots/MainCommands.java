package com.hemostaza.creativeboots;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainCommands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length < 2) return false;
        if(!sender.isOp()) {
            sender.sendMessage("Don't have permission to do that");
            return false;
        }
        String playerName = args[0];
        Player target = sender.getServer().getPlayerExact(playerName);
        if (target == null) { //check whether the player is online
            sender.sendMessage("Player " + playerName + " is not online.");
            return true;
        }
        ItemStack item = null;
        if(args[1].equalsIgnoreCase("normal")){
            item = ItemManager.cBoots;
        }else if(args[1].equalsIgnoreCase("premium")){
            item = ItemManager.cSBoots;
        }
        if(item==null){
            sender.sendMessage("Wrong item specified.");
        }
        target.getInventory().addItem(item);
        sender.sendMessage("Give " + playerName + " boots");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            players.add(p.getName());
        }
        if(args.length==1){
            return players;
        }
        if (args.length == 2) {
            if ("normal".startsWith(args[1].toLowerCase())) {
                completions.add("normal");
            }
            if ("premium".startsWith(args[1].toLowerCase())) {
                completions.add("premium");
            }

        }
        return completions;
    }

}
