package com.unclecole.dominionmilestones.utilities;

import com.unclecole.dominionmilestones.database.CachedPlayersData;
import com.unclecole.dominionmilestones.objects.IdlePlayer;
import org.bukkit.entity.Player;

public class IdleAPI {

    public boolean doesExist(Player player) {
        for (IdlePlayer idlePlayer : CachedPlayersData.data) {
            if (idlePlayer.getUUID().equals(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }


}
