package com.unclecole.dominionmilestones.database;

import com.unclecole.dominionmilestones.database.serializer.Serializer;

import java.util.HashMap;
import java.util.UUID;

public class DeathsData {

    private static final transient DeathsData instance = new DeathsData();

    public static HashMap<UUID, Long> data = new HashMap<>();

    public static void save() {

        new Serializer().save(instance);

    }

    public static void load() {

        new Serializer().load(instance, DeathsData.class, "deathsdata");

    }

}
