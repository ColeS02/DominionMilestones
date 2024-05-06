package com.unclecole.dominionmilestones.cmds.subs;

import com.unclecole.dominionmilestones.cmds.AbstractCommand;
import com.unclecole.dominionmilestones.database.*;
import com.unclecole.dominionmilestones.utilities.C;
import com.unclecole.dominionmilestones.utilities.HeadUtility;
import com.unclecole.dominionmilestones.utilities.MenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.*;


public class StatsSelfCmd extends AbstractCommand {

    public StatsSelfCmd() { super("stats", true); }

    @Override
    public boolean execute(CommandSender s, String[] args) {

        Player player = (Player) s;

        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        MenuUtility menu = new MenuUtility("&7" + player.getName() + " Stats", 54, player.getUniqueId(), placeholder);

        ItemStack Zombie = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/64528b3229660f3dfab42414f59ee8fd01e80081dd3df30869536ba9b414e089", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Zombie, 11, event -> {
            MobTop(player,EntityType.ZOMBIE);
        });
        ItemStack Skeleton = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/301268e9c492da1f0d88271cb492a4b302395f515a7bbf77f4a20b95fc02eb2", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Skeleton, 12, event -> {
            MobTop(player,EntityType.SKELETON);
        });
        ItemStack Blaze = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/b20657e24b56e1b2f8fc219da1de788c0c24f36388b1a409d0cd2d8dba44aa3b", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Blaze, 13, event -> {
            MobTop(player,EntityType.BLAZE);
        });
        ItemStack Creeper = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/831ba39a16ca442cab935040ddc726edad9dd276ecfaf6fee9976768bb276bc0", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Creeper, 14, event -> {
            MobTop(player,EntityType.CREEPER);
        });
        ItemStack Spider = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/c87a96a8c23b83b32a73df051f6b84c2ef24d25ba4190dbe74f11138629b5aef", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Spider, 15, event -> {
            MobTop(player,EntityType.SPIDER);
        });
        ItemStack Silverfish = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(Silverfish, 21, event -> {
            MobTop(player,EntityType.SILVERFISH);
        });
        ItemStack IronGolem = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(IronGolem, 22, event -> {
            MobTop(player,EntityType.IRON_GOLEM);
        });
        ItemStack PigZombie = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/74e9c6e98582ffd8ff8feb3322cd1849c43fb16b158abb11ca7b42eda7743eb", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(PigZombie, 23, event -> {
            MobTop(player,EntityType.PIG_ZOMBIE);
        });
        ItemStack PlayerKills = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/6a5361b52daf4f1c5c5480a39faaa10897595fa5763f757bdda3956588fec678", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(PlayerKills, 38, event -> {
            KillTop(player);
        });
        ItemStack PlayerDeaths = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/9ddbd0bb9622ce124dabb6ba3baa9a9ea71430f870fec15b3ea81f1961a412b2", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(PlayerDeaths, 39, event -> {
            DeathsTop(player);
        });
        ItemStack TotalTime = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/2579f867a71399957be37a7c2fb941d468523fce9903e9df88d37e1835665", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(TotalTime, 41, event -> {
            event.setCancelled(true);
        });
        ItemStack DungeonTime = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/8477dafc8c9ea0799633381793866d146c9a39fad4c6684e7117d97e9b6c3", "&9Zombie Kill Stats",
                Arrays.asList(""));
        menu.setItem(DungeonTime, 42, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);

        return false;
    }

    public void MobTop(Player player, EntityType entity) {
        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        MenuUtility menu = new MenuUtility("&7" + entity.getEntityClass().getSimpleName() + " Kill(s) Top", 54, player.getUniqueId(), placeholder);
        int amount = 0;
        Map<UUID, Long> sorted = null;
        switch (entity) {
            case ZOMBIE:
                sorted = sortByValue(ZombieKillsData.data);
                break;
            case SKELETON:
                sorted = sortByValue(SkeletonKillsData.data);
                break;
            case BLAZE:
                sorted = sortByValue(BlazeKillsData.data);
                break;
            case CREEPER:
                sorted = sortByValue(CreeperKillsData.data);
                break;
            case SPIDER:
                sorted = sortByValue(SpiderKillsData.data);
                break;
            case SILVERFISH:
                sorted = sortByValue(SilverFishKillsData.data);
                break;
            case IRON_GOLEM:
                sorted = sortByValue(IronGolemKillsData.data);
                break;
            case PIG_ZOMBIE:
                sorted = sortByValue(PigZombieKillsData.data);
        }

        int page = Math.min(sorted.size(), 10);

        assert sorted != null;
        int placement = 10;
        for (Map.Entry<UUID, Long> data : sorted.entrySet()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(data.getKey());
            if (amount < page) {
                if(amount >= 7) placement = 14;
                ItemStack players = HeadUtility.getCustomPlayerSkull(offlinePlayer.getName(),
                        "&e&l" + offlinePlayer.getName().toUpperCase() + " &8( &e" + data.getValue() + " Kills &8)",
                        Arrays.asList(" "));
                menu.setItem(players, placement+amount, event -> {
                    event.setCancelled(true);
                });

            }
            amount++;
        }
        for(int x = amount; x < 10; x++) {
            if(x >= 7) placement = 14;
            ItemStack players = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/3d8e73e370ee2a3767484fcf56698fee6bc785bf71e5dfd6aba67bf59294b418",
                    "&e&lNO ONE",
                    Arrays.asList(""));
            menu.setItem(players, placement+x, event -> {
                event.setCancelled(true);
            });
        }

        ItemStack players = HeadUtility.getCustomPlayerSkull(player.getName(),player.getName(),
                Arrays.asList(" ",
                        "&7&l* Kills: " + sorted.get(player.getUniqueId())));
        menu.setItem(players, 40, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);
    }

    public void KillTop(Player player) {
        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        MenuUtility menu = new MenuUtility("&7Kill(s) Top", 54, player.getUniqueId(), placeholder);
        int amount = 0;
        Map<UUID, Long> sorted = sortByValue(PlayerKillsData.data);

        int page = Math.min(sorted.size(), 10);

        assert sorted != null;
        int placement = 10;
        for (Map.Entry<UUID, Long> data : sorted.entrySet()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(data.getKey());
            if (amount < page) {
                if(amount >= 7) placement = 14;
                ItemStack players = HeadUtility.getCustomPlayerSkull(offlinePlayer.getName(),
                        "&e&l" + offlinePlayer.getName().toUpperCase() + " &8( &e" + data.getValue() + " Kills &8)",
                        Arrays.asList(" "));
                menu.setItem(players, placement+amount, event -> {
                    event.setCancelled(true);
                });

            }
            amount++;
        }
        for(int x = amount; x < 10; x++) {
            if(x >= 7) placement = 14;
            ItemStack players = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/3d8e73e370ee2a3767484fcf56698fee6bc785bf71e5dfd6aba67bf59294b418",
                    "&e&lNO ONE",
                    Arrays.asList(""));
            menu.setItem(players, placement+x, event -> {
                event.setCancelled(true);
            });
        }

        ItemStack players = HeadUtility.getCustomPlayerSkull(player.getName(),player.getName(),
                Arrays.asList(" ",
                        "&7&l* Kills: " + sorted.get(player.getUniqueId())));
        menu.setItem(players, 40, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);
    }

    public void DeathsTop(Player player) {
        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        MenuUtility menu = new MenuUtility("&7Death(s) Top", 54, player.getUniqueId(), placeholder);
        int amount = 0;
        Map<UUID, Long> sorted = sortByValue(DeathsData.data);

        int page = Math.min(sorted.size(), 10);

        assert sorted != null;
        int placement = 10;
        for (Map.Entry<UUID, Long> data : sorted.entrySet()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(data.getKey());
            if (amount < page) {
                if(amount >= 7) placement = 14;
                ItemStack players = HeadUtility.getCustomPlayerSkull(offlinePlayer.getName(),
                        "&e&l" + offlinePlayer.getName().toUpperCase() + " &8( &e" + data.getValue() + " Deaths &8)",
                        Arrays.asList(" "));
                menu.setItem(players, placement+amount, event -> {
                    event.setCancelled(true);
                });

            }
            amount++;
        }
        for(int x = amount; x < 10; x++) {
            if(x >= 7) placement = 14;
            ItemStack players = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/3d8e73e370ee2a3767484fcf56698fee6bc785bf71e5dfd6aba67bf59294b418",
                    "&e&lNO ONE",
                    Arrays.asList(""));
            menu.setItem(players, placement+x, event -> {
                event.setCancelled(true);
            });
        }

        ItemStack players = HeadUtility.getCustomPlayerSkull(player.getName(),player.getName(),
                Arrays.asList(" ",
                        "&7&l* Deaths: " + sorted.get(player.getUniqueId())));
        menu.setItem(players, 40, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);
    }

    public void Time(Player player, String Type) {
        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        MenuUtility menu = new MenuUtility("&7Death(s) Top", 54, player.getUniqueId(), placeholder);
        int amount = 0;
        Map<UUID, Long> sorted = null;

        if(Type.equalsIgnoreCase("TOTAL")) {
            sorted = sortByValue(PlayTimeData.data);
        } else {
            sorted = sortByValue(DungeonTimeData.data);
        }

        int page = Math.min(sorted.size(), 10);

        assert sorted != null;
        int placement = 10;
        for (Map.Entry<UUID, Long> data : sorted.entrySet()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(data.getKey());
            if (amount < page) {
                if(amount >= 7) placement = 14;
                ItemStack players = HeadUtility.getCustomPlayerSkull(offlinePlayer.getName(),
                        "&e&l" + offlinePlayer.getName().toUpperCase() + " &8( &e" + data.getValue() + " Deaths &8)",
                        Arrays.asList(" "));
                menu.setItem(players, placement+amount, event -> {
                    event.setCancelled(true);
                });

            }
            amount++;
        }
        for(int x = amount; x < 10; x++) {
            if(x >= 7) placement = 14;
            ItemStack players = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/3d8e73e370ee2a3767484fcf56698fee6bc785bf71e5dfd6aba67bf59294b418",
                    "&e&lNO ONE",
                    Arrays.asList(""));
            menu.setItem(players, placement+x, event -> {
                event.setCancelled(true);
            });
        }

        ItemStack players = HeadUtility.getCustomPlayerSkull(player.getName(),player.getName(),
                Arrays.asList(" ",
                        "&7&l* Deaths: " + sorted.get(player.getUniqueId())));
        menu.setItem(players, 40, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(player);
    }

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
}
