package me.shreyasayyengar.lifesteal.config;

import me.shreyasayyengar.lifesteal.LifeSteal;

import java.util.List;

public class Config {

    private static LifeSteal main;

    public static void init(LifeSteal main) {
        Config.main = main;
        main.getConfig().options().configuration();
        main.saveDefaultConfig();
    }

    public static List<String> getExecuteCommand() {
        return main.getConfig().getStringList("execute-on-0");
    }

    public static boolean reduceInternalHealth() {
        return main.getConfig().getBoolean("reduce-internal-health");
    }

    public static boolean shouldDisplayActionBar() {return main.getConfig().getBoolean("display-action-bars"); }

    public static boolean isCraftingEnabled() {
        return main.getConfig().getBoolean("enable-custom-crafting");
    }
}
