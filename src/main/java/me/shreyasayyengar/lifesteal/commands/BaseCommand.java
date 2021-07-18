package me.shreyasayyengar.lifesteal.commands;

import me.shreyasayyengar.lifesteal.LifeSteal;
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

        String prefix = ChatColor.YELLOW + "[Life" + ChatColor.BOLD + "Steal] - ";

        if (sender.hasPermission("lifesteal.manage")) {
            if (args.length == 0) {
                sendUsageMesssage(sender);
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {

                    case "restoreall", "resetall" -> {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            //noinspection deprecation
                            player.setMaxHealth(20.0);
                            player.setHealthScale(20.0);
                            player.setHealth(20.0);
                            player.sendMessage(prefix + ChatColor.GREEN + "Your health has been restored to normal!");
                            sender.sendMessage(prefix + ChatColor.GREEN + "You have successfully reset " + player.getName() + "'s health!");
                        }
                    }

                    case "config", "reloadconfig", "reload", "rc" -> {
                        LifeSteal.getInstance().reloadConfig();
                        sender.sendMessage(prefix + ChatColor.GREEN + "The configuration file has been reloaded!");
                    }
                    default -> sendUsageMesssage(sender);

                }
            } else if (args.length == 2) {
                switch (args[0].toLowerCase()) {
                    case "reset", "restore" -> {
                        Player player = Bukkit.getPlayer(args[1]);
                        if (player != null) {
                            if (player.isOnline()) {
                                //noinspection deprecation
                                player.setMaxHealth(20.0);
                                player.setHealthScale(20.0);
                                player.setHealth(20.0);
                                player.sendMessage(prefix + ChatColor.GREEN + "Your health has been restored to normal!");
                                sender.sendMessage(prefix + ChatColor.GREEN + "You have successfully reset " + player.getName() + "'s health!");
                            } else {
                                sender.sendMessage(prefix + ChatColor.RED + "The player (" + args[1] + ") is not online!");
                            }
                        } else {
                            sender.sendMessage(prefix + ChatColor.RED + "The player " + ChatColor.YELLOW + "(" + args[1] + ") " + ChatColor.RED + "does not exist!!");
                        }
                    }
                    case "resetall", "restoreall" -> {
                        double newHealth = 0;
                        try {
                            newHealth = Double.parseDouble(args[1]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(prefix + ChatColor.RED + args[1] + " is not a valid number!");
                        }

                        if (!(newHealth < 1 || newHealth > 20)) {

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                //noinspection deprecation
                                player.setMaxHealth(newHealth);
                                player.setHealthScale(newHealth);
                                player.setHealth(newHealth);
                                player.sendMessage(prefix + ChatColor.GREEN + "Your health has been set to " + args[1] + " HP!");
                                sender.sendMessage(prefix + ChatColor.GREEN + "You have successfully set " + player.getName() + "'s health to " + args[1] + " HP!");
                            }
                        } else {
                            sender.sendMessage(prefix + ChatColor.BLUE + "The HP must be in between 1 - 20");
                        }
                    }
                    default -> sendUsageMesssage(sender);
                }
            } else if (args.length == 3) {
                switch (args[0].toLowerCase()) {
                    case "reset", "restore" -> {
                        Player player = Bukkit.getPlayer(args[1]);
                        double newHealth = 0;
                        try {
                            newHealth = Double.parseDouble(args[2]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(prefix + ChatColor.RED + args[2] + " is not a valid number!");
                        }
                        if (!(newHealth < 1 || newHealth > 20)) {
                            if (player != null) {
                                if (player.isOnline()) {
                                    //noinspection deprecation
                                    player.setMaxHealth(newHealth);
                                    player.setHealthScale(newHealth);
                                    player.setHealth(newHealth);
                                    player.sendMessage(prefix + ChatColor.GREEN + "Your health has been set to " + newHealth + " HP!");
                                    sender.sendMessage(prefix + ChatColor.GREEN + "You have successfully set " + player.getName() + "'s health to " + newHealth + " HP!");
                                } else {
                                    sender.sendMessage(prefix + ChatColor.RED + "The player (" + args[1] + ") is not online!");
                                }
                            } else {
                                sender.sendMessage(prefix + ChatColor.RED + "The player " + ChatColor.YELLOW + "(" + args[1] + ") " + ChatColor.RED + "does not exist!!");
                            }
                        } else {
                            sender.sendMessage(prefix + ChatColor.BLUE + "The HP must be in between 1 - 20");
                        }
                    }
                    default -> sendUsageMesssage(sender);
                }
            } else {
                sendUsageMesssage(sender);
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "You do not have permission to execute this command!");
        }

        return false;
    }

    public void sendUsageMesssage(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "LifeSteal v" + LifeSteal.getInstance().getDescription().getVersion());
        sender.sendMessage(ChatColor.RED + "-------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal help - brings up this help menu");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal restore <player> - resets players back to the default 20.0 HP (10 hearts)");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal restore <player> <hp> - sets players to the HP specified.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal restoreall - resets all players back to the default 20.0 HP (10 hearts)");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal restoreall <hp> - sets all players to the HP specified.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/lifesteal reload - reloads the plugin's config.yml");
        sender.sendMessage(ChatColor.RED + "-------------------");
    }
}