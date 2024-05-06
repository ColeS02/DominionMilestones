/*package com.unclecole.dominionmilestones.cmds.subs;

import com.unclecole.dominionmilestones.cmds.AbstractCommand;
import com.unclecole.dominionmilestones.database.*;
import com.unclecole.dominionmilestones.utilities.C;
import com.unclecole.dominionmilestones.utilities.HeadUtility;
import com.unclecole.dominionmilestones.utilities.MenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.*;

public class StatsTopCmd extends AbstractCommand {

    public StatsTopCmd() { super("top", true); }

    @Override
    public boolean execute(CommandSender s, String[] args) {

        Map<UUID, Long> deathsSorted = sortByValue(DeathsData.data);
        Map<UUID, Long> playerKillsSorted = sortByValue(PlayerKillsData.data);
        Map<UUID, Long> totalPlayTimeSorted = sortByValue(PlayTimeData.data);
        Map<UUID, Long> dungeonTimeSorted = sortByValue(DungeonTimeData.data);
        Map<UUID, Long> zombieKillsSorted = sortByValue(ZombieKillsData.data);
        Map<UUID, Long> skeletonKillsSorted = sortByValue(SkeletonKillsData.data);
        Map<UUID, Long> blazeKillsSorted = sortByValue(BlazeKillsData.data);
        Map<UUID, Long> creeperKillsSorted = sortByValue(CreeperKillsData.data);
        Map<UUID, Long> spiderKillsSorted = sortByValue(SpiderKillsData.data);
        Map<UUID, Long> silverFishKillsSorted = sortByValue(SilverFishKillsData.data);
        Map<UUID, Long> ironGolemKillsSorted = sortByValue(IronGolemKillsData.data);
        Map<UUID, Long> pigZombieKillsSorted = sortByValue(PigZombieKillsData.data);

        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats", Arrays.asList(""));
        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats", Arrays.asList(""));
        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats", Arrays.asList(""));
        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats", Arrays.asList(""));

        int amount = sorted.size();
        int page = Math.min(amount, 10);

        for (Map.Entry<UUID, Long> data : sorted.entrySet()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(data.getKey());
            if (amount > page) {

            }
        }



        HashMap<UUID, Long> test = new HashMap<>();

        Player player = (Player) s;

        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        PlayerStats stats = CachedPlayersData.playerstats.get(player.getUniqueId());

        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats",
                Arrays.asList(" ",
                        " &7&l* &bZombie Kills &7» &f" + ZombieKillsData.zombiekills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSkeleton Kills &7» &f" + SkeletonKillsData.skeletonkills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bBlaze kills &7» &f" + BlazeKillsData.blazekills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bCreeper Kills &7» &f" + CreeperKillsData.creeperKills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSpider Kills &7» &f" + SpiderKillsData.spiderkills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSilverfish Kills &7» &f" + SilverFishKillsData.silverfishKills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bIron Golem Kills &7» &f" + IronGolemKillsData.irongolemkills.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bZombie Pigman Kills &7» &f" + PigZombieKillsData.pigzombiekills.getOrDefault(player.getUniqueId(),0L),
                        " "));

        ItemStack general = HeadUtility.getCustomPlayerSkull(player.getName(),
                Arrays.asList(" ",
                        " &7&l* &eTotal Playtime &7» &f" + stats.format("PLAYTIME"),
                        " &7&l* &eDungeon Playtime &7» &f" + stats.format("DUNGEONTIME"),
                        " &7&l* &ePlayer Kills &7» &f" + stats.getPlayerkills(),
                        " &7&l* &eTotal Deaths &7» &f" + stats.getDeaths(),
                        " "));
        ItemBuilder generalBuilt = new ItemBuilder(general).setName(C.color("&6General Stats"));

        MenuUtility menu = new MenuUtility("&7" + player.getName() + " Stats", 27, player.getUniqueId(), placeholder);
        menu.setItem(mob, 11, event -> {
            event.setCancelled(true);
        });
        menu.setItem(generalBuilt, 15, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);

        return false;
    }

    public void

    private HashMap<UUID, Long> sortByValue(HashMap<UUID, Long> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<UUID, Long> > list = new LinkedList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        Collections.reverse(list);

        // put data from sorted list to hashmap
        HashMap<UUID, Long> temp = new LinkedHashMap<>();
        for (Map.Entry<UUID, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    @Override
    public String getDescription() {
        return "View your stats!";
    }

    @Override
    public String getPermission() {
        return "milestones.stats";
    }
}*/
