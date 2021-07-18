package me.shreyasayyengar.lifesteal;

import me.shreyasayyengar.lifesteal.commands.BaseCommand;
import me.shreyasayyengar.lifesteal.commands.BaseCommandTabCompleter;
import me.shreyasayyengar.lifesteal.config.Config;
import me.shreyasayyengar.lifesteal.events.DeathEvent;
import me.shreyasayyengar.lifesteal.events.KillEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeSteal extends JavaPlugin {

    private static LifeSteal instance;

    public static LifeSteal getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        LifeSteal.instance = this;

        getLogger().info(ChatColor.GOLD + "Plugin has loaded with no errors");

        new Config(this);
        getLogger().info(ChatColor.AQUA + "Generating the configuration file...");

        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getCommand("lifesteal").setExecutor(new BaseCommand());
        getCommand("lifesteal").setTabCompleter(new BaseCommandTabCompleter());
    }

    @Override
    public void onDisable() {
        getLogger().warning(ChatColor.RED + "Shutting down...");
    }
}
