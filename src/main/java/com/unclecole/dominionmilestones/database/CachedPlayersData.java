package com.unclecole.dominionmilestones.database;

import com.unclecole.dominionmilestones.database.serializer.Serializer;
import com.unclecole.dominionmilestones.objects.IdlePlayer;

import java.util.*;

public class CachedPlayersData {

    private static final transient CachedPlayersData instance = new CachedPlayersData();

    public static Set<IdlePlayer> data = new HashSet<>();

    public static void save() {

        new Serializer().save(instance);

    }

    public static void load() {

        new Serializer().load(instance, CachedPlayersData.class, "statsdata");

    }
}
