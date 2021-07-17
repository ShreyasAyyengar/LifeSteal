package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.config.Config;
import me.shreyasayyengar.lifesteal.LifeSteal;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class KillEvent implements Listener {

    @EventHandler
    private void onDeath(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player damager && e.getEntity() instanceof Player victim) {

            if (damager.getGameMode() == GameMode.SURVIVAL && victim.getGameMode() == GameMode.SURVIVAL) {

                System.out.println(victim.getHealth() + "<--- player health from hit event");

                if (damager.getHealthScale() < 20.0) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            System.out.println(victim.getHealth());
                            System.out.println(damager.getHealthScale());
                            if (victim.getHealth() <= 0) {

                                damager.setHealthScaled(true);
                                damager.setHealthScale(damager.getHealthScale() + 2);
                                if (Config.usingInternalHealth()) {
                                    damager.setMaxHealth(damager.getHealthScale());
                                }
                            }
                        }
                    }.runTaskLater(LifeSteal.getInstance(), 10);
                }
            }
        }
    }
}