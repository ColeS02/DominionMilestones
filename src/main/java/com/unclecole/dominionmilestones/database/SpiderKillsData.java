package com.unclecole.dominionmilestones.database;

import com.unclecole.dominionmilestones.database.serializer.Serializer;

import java.util.HashMap;
import java.util.UUID;

public class SpiderKillsData {

    private static final transient SpiderKillsData instance = new SpiderKillsData();

    public static HashMap<UUID, Long> data = new HashMap<>();

    public static void save() {

        new Serializer().save(instance);

    }

    public static void load() {

        new Serializer().load(instance, SpiderKillsData.class, "spiderkillsdata");

    }

}