package me.shreyasayyengar.lifesteal.config;

import me.shreyasayyengar.lifesteal.LifeSteal;

public class Config {

    private static LifeSteal main;

    public Config(LifeSteal main) {
        Config.main = main;
        main.getConfig().options().configuration();
        main.saveDefaultConfig();
    }

    public static String getExecuteCommand() {
        return main.getConfig().getString("execute-on-0");
    }

    public static boolean usingInternalHealth() {
        return main.getConfig().getBoolean("reduce-internal-health");
    }
}
