package com.unclecole.dominionmilestones.listeners;

import com.unclecole.dominionmilestones.database.DeathsData;
import com.unclecole.dominionmilestones.database.PlayerKillsData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        long currentDeaths = DeathsData.data.getOrDefault(player.getUniqueId(), (long) 0);
        DeathsData.data.put(player.getUniqueId(), currentDeaths + 1);

        if (killer != null) {

            long currentKills = PlayerKillsData.data.getOrDefault(killer.getUniqueId(), (long) 0);
            PlayerKillsData.data.put(killer.getUniqueId(), currentKills + 1);

        }
    }

}
