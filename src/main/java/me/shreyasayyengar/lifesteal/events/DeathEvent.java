package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathEvent implements Listener {

    @EventHandler
    private void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        if ((player.getHealthScale() - 2) <= 0) {
            String toExecute = Config.getExecuteCommand().replaceAll("%player_name%", player.getName());
//            System.out.println("To execute ---> " + toExecute);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    toExecute);
        }

        if (!((player.getHealthScale() - 2) <= 0)) {
            player.setHealthScale(player.getHealthScale() - 2);
        }

        if (Config.usingInternalHealth()) {
            player.setMaxHealth(player.getHealthScale());
        }
    }
}
