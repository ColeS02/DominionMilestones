package com.unclecole.dominionmilestones.cmds;

import org.bukkit.command.CommandSender;

import java.io.IOException;

public interface Executable {

    boolean execute(CommandSender paramCommandSender, String[] paramArrayOfString) throws IOException;

    String getDescription();

    String getPermission();
}