package com.unclecole.dominionmilestones.listeners;

import com.unclecole.dominionmilestones.database.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class MobKillListener implements Listener {

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        if (!event.getEntity().getLocation().getWorld().getName().equals("dungeons")) return;
        if(event.getEntity().getKiller() == null) return;

        UUID uuid = event.getEntity().getKiller().getUniqueId();

        addByEntity(event.getEntityType(),uuid);
    }

    public void addByEntity(EntityType type, UUID uuid) {
        switch (type) {
            case ZOMBIE:
                long ZombieKills = ZombieKillsData.data.getOrDefault(uuid, (long) 0);
                ZombieKillsData.data.put(uuid, ZombieKills + 1);
                return;
            case SKELETON:
                long SkeletonKills = SkeletonKillsData.data.getOrDefault(uuid, (long) 0);
                SkeletonKillsData.data.put(uuid, SkeletonKills + 1);
                return;
            case BLAZE:
                long BlazeKills = BlazeKillsData.data.getOrDefault(uuid, (long) 0);
                BlazeKillsData.data.put(uuid, BlazeKills + 1);
                return;
            case CREEPER:
                long CreeperKills = CreeperKillsData.data.getOrDefault(uuid, (long) 0);
                CreeperKillsData.data.put(uuid, CreeperKills + 1);
                return;
            case SPIDER:
                long SpiderKills = SpiderKillsData.data.getOrDefault(uuid, (long) 0);
                SpiderKillsData.data.put(uuid, SpiderKills + 1);
                return;
            case SILVERFISH:
                long SilverFishKills = SilverFishKillsData.data.getOrDefault(uuid, (long) 0);
                SilverFishKillsData.data.put(uuid, SilverFishKills + 1);
                return;
            case IRON_GOLEM:
                long IronGolemKills = IronGolemKillsData.data.getOrDefault(uuid, (long) 0);
                IronGolemKillsData.data.put(uuid, IronGolemKills + 1);
                return;
            case PIG_ZOMBIE:
                long PigZombieKills = PigZombieKillsData.data.getOrDefault(uuid, (long) 0);
                PigZombieKillsData.data.put(uuid, PigZombieKills + 1);
        }
    }

}
