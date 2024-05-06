package com.unclecole.dominionmilestones;

import com.unclecole.dominionmilestones.cmds.BaseCommand;
import com.unclecole.dominionmilestones.database.*;
import com.unclecole.dominionmilestones.database.serializer.Persist;
import com.unclecole.dominionmilestones.listeners.MobKillListener;
import com.unclecole.dominionmilestones.listeners.PlayerDeathListener;
import com.unclecole.dominionmilestones.listeners.PlaytimeListener;
import com.unclecole.dominionmilestones.objects.IdlePlayer;
import com.unclecole.dominionmilestones.task.IdleCheck;
import com.unclecole.dominionmilestones.task.PlayTimeRecord;
import com.unclecole.dominionmilestones.utilities.IdleAPI;
import com.unclecole.dominionmilestones.utilities.MenuUtility;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class DominionMilestones extends JavaPlugin {

    @Getter
    private static DominionMilestones instance;
    @Getter
    private Persist persist;
    @Getter private IdleAPI idleAPI;
    @Getter public static Map<UUID, Long> currentTime = new HashMap<>();
    @Getter private MariaDB mariaDB;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        persist = new Persist();
        idleAPI = new IdleAPI();
        saveDefaultConfig();

        CachedPlayersData.load();
        DeathsData.load();
        PlayerKillsData.load();
        PlayTimeData.load();
        DungeonTimeData.load();
        ZombieKillsData.load();
        SkeletonKillsData.load();
        BlazeKillsData.load();
        CreeperKillsData.load();
        SpiderKillsData.load();
        SilverFishKillsData.load();
        IronGolemKillsData.load();
        PigZombieKillsData.load();

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new MobKillListener(), instance);
        pm.registerEvents(new PlayerDeathListener(), instance);
        pm.registerEvents(new PlaytimeListener(), instance);
        pm.registerEvents(MenuUtility.getListener(), this);

        Objects.requireNonNull(getCommand("stats")).setExecutor(new BaseCommand());

        Bukkit.getScheduler().runTaskTimer(this, new IdleCheck(), 20, 20 * 15);
        Bukkit.getScheduler().runTaskTimer(this, new PlayTimeRecord(), 20, 20);

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (getIdleAPI().doesExist(player)) continue;
            IdlePlayer idlePlayer = new IdlePlayer(player);
            idlePlayer.updatePlayer();
            CachedPlayersData.data.add(idlePlayer);
        }

        try {
            this.mariaDB = new MariaDB("jdbc:mariadb://","172.18.0.1:3306","s2_AuthBot" + "?autoReconnect=true","u2_J4SzmdiXcp","ZZk+0!NQ6z9w!hSsVn9SN0qi");
            this.mariaDB.connect();
            this.mariaDB.registerTable();
            getLogger().info("Successfully Connected!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        CachedPlayersData.save();
        DeathsData.save();
        PlayerKillsData.save();
        PlayTimeData.save();
        DungeonTimeData.save();
        ZombieKillsData.save();
        SkeletonKillsData.save();
        BlazeKillsData.save();
        CreeperKillsData.save();
        SpiderKillsData.save();
        SilverFishKillsData.save();
        IronGolemKillsData.save();
        PigZombieKillsData.save();
    }
}
