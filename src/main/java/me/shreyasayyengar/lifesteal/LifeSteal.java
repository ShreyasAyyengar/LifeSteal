package me.shreyasayyengar.lifesteal;

import me.shreyasayyengar.lifesteal.config.Config;
import me.shreyasayyengar.lifesteal.events.DeathEvent;
import me.shreyasayyengar.lifesteal.events.KillEvent;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeSteal extends JavaPlugin {

    private static LifeSteal instance;

    @Override
    public void onEnable() {

        LifeSteal.instance = this;
        getLogger().info(ChatColor.GOLD + "Plugin has loaded with no errors");

        getServer().getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);

        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);

        getCommand("lifesteal").setExecutor(new BaseCommand());

        new Config(this);
        getLogger().info(ChatColor.AQUA + "Generating the configuration file...");
    }

    @Override
    public void onDisable() {
        getLogger().warning(ChatColor.RED + "Shutting down...");
    }

    public static LifeSteal getInstance() {
        return instance;
    }
}
