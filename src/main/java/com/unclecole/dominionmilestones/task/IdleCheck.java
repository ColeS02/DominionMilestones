package com.unclecole.dominionmilestones.task;

import com.unclecole.dominionmilestones.database.CachedPlayersData;
import com.unclecole.dominionmilestones.objects.IdlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IdleCheck implements Runnable {

    @Override
    public void run() {

        for (IdlePlayer idlePlayer : CachedPlayersData.data) {

            Player player = Bukkit.getPlayer(idlePlayer.getUUID());

            if (player == null) continue;

            if (player.isOnline()) {
                if (idlePlayer.sameView() && idlePlayer.sameLocation()) {
                    idlePlayer.setAfk(true);
                } else {
                    idlePlayer.setAfk(false);
                }
                idlePlayer.updatePlayer();
            }
        }
    }
}
