package com.unclecole.dominionmilestones.database.serializer;


import com.unclecole.dominionmilestones.DominionMilestones;

public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        DominionMilestones.getInstance().getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     *
     */
    public <T> T load(T def, Class<T> clazz, String name) {
        return DominionMilestones.getInstance().getPersist().loadOrSaveDefault(def, clazz, name);
    }



}
