package me.shreyasayyengar.lifesteal;

import me.shreyasayyengar.lifesteal.commands.BaseCommand;
import me.shreyasayyengar.lifesteal.commands.BaseCommandTabCompleter;
import me.shreyasayyengar.lifesteal.config.Config;
import me.shreyasayyengar.lifesteal.events.DeathEvent;
import me.shreyasayyengar.lifesteal.events.Interact;
import me.shreyasayyengar.lifesteal.events.KillEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public final class LifeSteal extends JavaPlugin {

    public static ItemStack HEART_ITEMSTACK;
    public static String PREFIX = ChatColor.YELLOW + "[Life" + ChatColor.BOLD + "Steal] - ";
    private static LifeSteal INSTANCE;

    public static LifeSteal getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        getLogger().info(PREFIX + ChatColor.GOLD + "Plugin has loaded with no errors");

        Config.init(this);
        getLogger().info(PREFIX + ChatColor.AQUA + "Generating the configuration file...");

        registerEvents();
        registerCommands();
        if (Config.isCraftingEnabled()) {
            registerRecipe();
        }
    }

    private void registerRecipe() {
        getLogger().info(ChatColor.BLUE + "Creating custom recipe...");

        HEART_ITEMSTACK = new ItemStack(Material.RABBIT_FOOT);
        HEART_ITEMSTACK.setDurability((short) 1);
        ItemMeta itemMeta = HEART_ITEMSTACK.getItemMeta();
        itemMeta.setLocalizedName("heart.add");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Extra Heart");
        itemMeta.setLore(Collections.singletonList(ChatColor.AQUA + "Right click to gain an extra heart!"));
        HEART_ITEMSTACK.setItemMeta(itemMeta);

        /*
           D - diamond block
           O - obsidian
           G - gold block
         */

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(LifeSteal.getInstance(), "heart"), HEART_ITEMSTACK);
        recipe.shape("DOD", "OGO", "DOD");
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('G', Material.GOLD_BLOCK);

        getServer().addRecipe(recipe);
    }

    private void registerCommands() {
        getLogger().info(PREFIX+ ChatColor.BLUE + "Creating commands...");
        getCommand("lifesteal").setExecutor(new BaseCommand());
        getCommand("lifesteal").setTabCompleter(new BaseCommandTabCompleter());
    }

    private void registerEvents() {
        getLogger().info(PREFIX + ChatColor.BLUE + "Creating events...");
        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new Interact(), this);
    }

    @Override
    public void onDisable() {
        getLogger().warning(PREFIX + ChatColor.RED + "Shutting down...");
    }
}
