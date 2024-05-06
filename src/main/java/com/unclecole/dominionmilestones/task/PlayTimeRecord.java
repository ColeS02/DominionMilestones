package com.unclecole.dominionmilestones.task;

import com.unclecole.dominionmilestones.database.CachedPlayersData;
import com.unclecole.dominionmilestones.database.DungeonTimeData;
import com.unclecole.dominionmilestones.database.PlayTimeData;
import com.unclecole.dominionmilestones.objects.IdlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayTimeRecord implements Runnable {

    @Override
    public void run() {
        for (IdlePlayer idlePlayer : CachedPlayersData.data) {

            Player player = Bukkit.getPlayer(idlePlayer.getUUID());

            if (player == null) continue;

            if (player.isOnline() && !idlePlayer.isAfk()) {
                long currentTime = PlayTimeData.data.getOrDefault(player.getUniqueId(), 0L);
                PlayTimeData.data.put(player.getUniqueId(),currentTime + 1);
            }

            if(player.isOnline() && !idlePlayer.isAfk() && player.getLocation().getWorld().getName().equals("dungeons")) {
                long currentTime = DungeonTimeData.data.getOrDefault(player.getUniqueId(), 0L);
                DungeonTimeData.data.put(player.getUniqueId(),currentTime + 1);
            }
        }
    }
}
