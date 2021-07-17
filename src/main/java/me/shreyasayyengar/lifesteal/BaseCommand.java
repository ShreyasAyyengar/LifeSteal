package me.shreyasayyengar.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BaseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.hasPermission("lifesteal.manage")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.RED + "-------------------");
                sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal help - brings up this help menu");
                sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal reset <player> - resets players back to the default 20.0 HP (10 hearts)");
                sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal resetall - resets all players back to the default 20.0 HP (10 hearts)");
                sender.sendMessage(ChatColor.RED + "-------------------");
            } else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {

                Player player = Bukkit.getPlayer(args[1]);

                if (player != null) {
                    if (player.isOnline()) {
                        player.setMaxHealth(20.0);
                        player.setHealthScale(20.0);
                        player.setHealth(20.0);
                        player.sendMessage(ChatColor.GREEN + "Your health has been restored to normal!");
                        sender.sendMessage(ChatColor.GREEN + "You have successfully reset " + player.getName() + "'s health!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "The player (" + args[1] + ") is not online!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "The player " + ChatColor.YELLOW + "(" + args[1] + ") " + ChatColor.RED + "does not exist!!");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("resetall")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setMaxHealth(20.0);
                    player.setHealthScale(20.0);
                    player.setHealth(20.0);
                    player.sendMessage(ChatColor.GREEN + "Your health has been restored to normal!");
                    sender.sendMessage(ChatColor.GREEN + "You have successfully reset " + player.getName() + "'s health!");
                }
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command!");
        }
        return false;
    }
}
