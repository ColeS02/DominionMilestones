package com.unclecole.dominionmilestones.listeners;

import com.unclecole.dominionmilestones.DominionMilestones;
import com.unclecole.dominionmilestones.database.CachedPlayersData;
import com.unclecole.dominionmilestones.objects.IdlePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlaytimeListener implements Listener {

    public static HashMap<UUID,Long> onlinePlayers = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DominionMilestones.currentTime.put(player.getUniqueId(), System.currentTimeMillis());

        if (!DominionMilestones.getInstance().getIdleAPI().doesExist(player)) {
            IdlePlayer idlePlayer = new IdlePlayer(player);
            idlePlayer.updatePlayer();
            CachedPlayersData.data.add(idlePlayer);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        DominionMilestones.currentTime.remove(player.getUniqueId());
    }

}
