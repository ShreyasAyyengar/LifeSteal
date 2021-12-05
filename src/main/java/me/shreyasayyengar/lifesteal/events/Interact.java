package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.LifeSteal;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener {

    @EventHandler
    private void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (player.getInventory().getItemInMainHand().equals(LifeSteal.HEART_ITEMSTACK) || player.getInventory().getItemInOffHand().equals(LifeSteal.HEART_ITEMSTACK)) {

                if ((player.getHealth() + 2) > 20) {
                    player.sendMessage(LifeSteal.PREFIX + ChatColor.RED + "Could not add a heart: You already have 10 hearts!");
                } else {
                    player.setHealth(player.getHealth() + 2);
                    player.sendMessage(LifeSteal.PREFIX + ChatColor.LIGHT_PURPLE + "Added a heart!");
                }
            }
        }
    }
}
