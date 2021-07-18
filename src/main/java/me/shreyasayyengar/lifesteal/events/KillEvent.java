package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.config.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillEvent implements Listener {

    @EventHandler
    private void onDeath(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player damager && e.getEntity() instanceof Player victim) {

            if (damager.getGameMode() == GameMode.SURVIVAL && victim.getGameMode() == GameMode.SURVIVAL) {

                if (damager.getHealthScale() < 20.0) {

                    if (victim.getHealth() - e.getFinalDamage() <= 0) {

                        damager.setHealthScaled(true);
                        damager.setHealthScale(damager.getHealthScale() + 2);
                        if (Config.reduceInternalHealth()) {
                            //noinspection deprecation
                            damager.setMaxHealth(damager.getHealthScale());
                        }
                        if (Config.shouldDisplayActionBar()) {
                            //noinspection deprecation
                            damager.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN +
                                    "You gained a full heart by killing " + victim.getName()));
                        }
                    }
                }
            }
        }
    }
}