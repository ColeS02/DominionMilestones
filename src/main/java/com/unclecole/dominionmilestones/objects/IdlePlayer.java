package com.unclecole.dominionmilestones.objects;

import com.unclecole.dominionmilestones.utilities.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IdlePlayer {

    private float oldPitch, newPitch, oldYaw, newYaw;
    private String newLocation, oldLocation;
    private UUID uuid;
    private boolean afk;


    public IdlePlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.newLocation = LocationUtility.parseToString(player.getLocation());
        this.newPitch = player.getLocation().getPitch();
        this.oldYaw = player.getLocation().getYaw();
        this.afk = false;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }

    public boolean isAfk() {
        return afk;
    }

    public boolean sameLocation() {
        return oldLocation.equalsIgnoreCase(newLocation);
    }

    public boolean sameView() {
        return oldYaw == newYaw && oldPitch == newPitch;
    }

    public void updatePlayer() {

        Player player = Bukkit.getPlayer(uuid);

        if (player == null) return;

        this.oldPitch = newPitch;
        this.oldYaw = newYaw;
        this.oldLocation = newLocation;
        this.newLocation = LocationUtility.parseToString(player.getLocation());
        this.newPitch = player.getLocation().getPitch();
        this.newYaw = player.getLocation().getYaw();
    }

    public UUID getUUID() {
        return uuid;
    }
}
