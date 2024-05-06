package com.unclecole.dominionmilestones.utilities;

import org.bukkit.ChatColor;

public class ProgressBarUtility {

    static final ChatColor AVAILABLE_COLOR = ChatColor.GREEN;
    static final ChatColor NOT_AVAILABLE_COLOR = ChatColor.RED;
    static final String DEFAULT_DELIMITER = ":";

    public static String getProgressBar(int amountOfDelimeters, String delimeter, double current, double required) {

        if (delimeter == null || delimeter.isEmpty()) {
            delimeter = DEFAULT_DELIMITER;
        }

        double treshold = required / amountOfDelimeters;
        int numberOfGreens = (int) (current / treshold);

        StringBuilder result = new StringBuilder();

        result.append(AVAILABLE_COLOR);
        for (int i = 0; i < numberOfGreens; i++) {
            result.append(delimeter);
        }
        result.append(NOT_AVAILABLE_COLOR);
        for (int i = 0; i < amountOfDelimeters - numberOfGreens; i++) {
            result.append(delimeter);
        }
        return C.color(result.toString());
    }
}
