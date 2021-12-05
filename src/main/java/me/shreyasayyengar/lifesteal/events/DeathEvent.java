package me.shreyasayyengar.lifesteal.events;

import me.shreyasayyengar.lifesteal.LifeSteal;
import me.shreyasayyengar.lifesteal.config.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {

    @EventHandler
    private void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        if ((player.getHealthScale() - 2) <= 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Config.getExecuteCommand().forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("%player_name%", player.getName())));
                }
            }.runTaskLater(LifeSteal.getInstance(), 5);
        } // out-of-lives actions

        if (!((player.getHealthScale() - 2) <= 0)) {
            player.setHealthScale(player.getHealthScale() - 2);
            if (Config.shouldDisplayActionBar()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You lost a heart! Kill other players to gain a full heart!"));
            }
        } // reduce heart actions (always enabled)

        if (Config.reduceInternalHealth()) {
            //noinspection deprecation
            player.setMaxHealth(player.getHealthScale());
        }
    }
}