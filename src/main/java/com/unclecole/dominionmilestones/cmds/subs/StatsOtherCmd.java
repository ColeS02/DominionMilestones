package com.unclecole.dominionmilestones.cmds.subs;

import com.unclecole.dominionmilestones.DominionMilestones;
import com.unclecole.dominionmilestones.cmds.AbstractCommand;
import com.unclecole.dominionmilestones.database.*;
import com.unclecole.dominionmilestones.utilities.C;
import com.unclecole.dominionmilestones.utilities.HeadUtility;
import com.unclecole.dominionmilestones.utilities.MenuUtility;
import com.unclecole.dominionmilestones.utilities.TL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Arrays;
import java.util.UUID;

public class StatsOtherCmd extends AbstractCommand {

    public StatsOtherCmd() { super("stats", true); }

    @Override
    public boolean execute(CommandSender s, String[] args) {

        if(DominionMilestones.getInstance().getMariaDB().fetchStringUsername(args[0], "uuid") == null) {
            TL.ENTER_VALID_USER.send(s);
            return false;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(DominionMilestones.getInstance().getMariaDB().fetchStringUsername(args[0], "uuid")));
        Player menuPlayer = (Player) s;

        ItemBuilder placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7))
                .setName(C.color("&a"));

        ItemStack mob = HeadUtility.getCustomSkull("https://textures.minecraft.net/texture/e4a716081c97fdb330fa4645a3fcf06b1d051e3bbd1efc35a3fed6f5abaa26fe", "&9Mob Stats",
                Arrays.asList(" ",
                        " &7&l* &bZombie Kills &7» &f" + ZombieKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSkeleton Kills &7» &f" + SkeletonKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bBlaze kills &7» &f" + BlazeKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bCreeper Kills &7» &f" + CreeperKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSpider Kills &7» &f" + SpiderKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bSilverfish Kills &7» &f" + SilverFishKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bIron Golem Kills &7» &f" + IronGolemKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &bZombie Pigman Kills &7» &f" + PigZombieKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " "));

        ItemStack general = HeadUtility.getCustomPlayerSkull(player.getName(),"",
                Arrays.asList(" ",
                        " &7&l* &eTotal Playtime &7» &f" + C.getFormattedTime(PlayTimeData.data.getOrDefault(player.getUniqueId(),0L)),
                        " &7&l* &eDungeon Playtime &7» &f" + C.getFormattedTime(DungeonTimeData.data.getOrDefault(player.getUniqueId(),0L)),
                        " &7&l* &ePlayer Kills &7» &f" + PlayerKillsData.data.getOrDefault(player.getUniqueId(),0L),
                        " &7&l* &eTotal Deaths &7» &f" + DeathsData.data.getOrDefault(player.getUniqueId(),0L),
                        " "));
        ItemBuilder generalBuilt = new ItemBuilder(general).setName(C.color("&6General Stats"));

        MenuUtility menu = new MenuUtility("&7" + player.getName() + " Stats", 27, menuPlayer.getUniqueId(), placeholder);
        menu.setItem(mob, 11, event -> {
            event.setCancelled(true);
        });
        menu.setItem(generalBuilt, 15, event -> {
            event.setCancelled(true);
        });

        menu.openInventory(menuPlayer);

        return false;
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
