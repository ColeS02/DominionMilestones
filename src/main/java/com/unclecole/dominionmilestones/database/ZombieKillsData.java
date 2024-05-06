package com.unclecole.dominionmilestones.database;

import com.unclecole.dominionmilestones.database.serializer.Serializer;

import java.util.HashMap;
import java.util.UUID;

public class ZombieKillsData {

    private static final transient ZombieKillsData instance = new ZombieKillsData();

    public static HashMap<UUID, Long> data = new HashMap<>();

    public static void save() {

        new Serializer().save(instance);

    }

    public static void load() {

        new Serializer().load(instance, ZombieKillsData.class, "zombiekillsdata");

    }

}
