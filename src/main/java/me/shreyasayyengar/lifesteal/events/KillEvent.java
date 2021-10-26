package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.config.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener {

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();

        if (player.getKiller() != null) {
            Player killer = player.getKiller();

            if (killer.getHealthScale() < 20.0) {

                killer.setHealthScaled(true);
                killer.setHealthScale(killer.getHealthScale() + 2);
                if (Config.reduceInternalHealth()) {
                    //noinspection deprecation
                    killer.setMaxHealth(killer.getHealthScale());
                }
                if (Config.shouldDisplayActionBar()) {
                    //noinspection deprecation
                    killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN +
                            "You gained a full heart by killing " + player.getName()));
                }
            }
        }
    }
}