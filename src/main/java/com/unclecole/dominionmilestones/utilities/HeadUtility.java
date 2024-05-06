package com.unclecole.dominionmilestones.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum HeadUtility {

    ARROW_LEFT("MHF_ArrowLeft"),
    ARROW_RIGHT("MHF_ArrowRight"),
    ARROW_UP("MHF_ArrowUp"),
    ARROW_DOWN("MHF_ArrowDown"),
    QUESTION("MHF_Question"),
    EXCLAMATION("MHF_Exclamation"),
    CAMERA("FHG_Cam"),

    ZOMBIE_PIGMAN("MHF_PigZombie"),
    PIG("MHF_Pig"),
    SHEEP("MHF_Sheep"),
    BLAZE("MHF_Blaze"),
    CHICKEN("MHF_Chicken"),
    COW("MHF_Cow"),
    SLIME("MHF_Slime"),
    SPIDER("MHF_Spider"),
    SQUID("MHF_Squid"),
    VILLAGER("MHF_Villager"),
    OCELOT("MHF_Ocelot"),
    HEROBRINE("MHF_Herobrine"),
    LAVA_SLIME("MHF_LavaSlime"),
    MOOSHROOM("MHF_MushroomCow"),
    GOLEM("MHF_Golem"),
    GHAST("MHF_Ghast"),
    ENDERMAN("MHF_Enderman"),
    CAVE_SPIDER("MHF_CaveSpider"),

    CACTUS("MHF_Cactus"),
    CAKE("MHF_Cake"),
    CHEST("MHF_Chest"),
    MELON("MHF_Melon"),
    LOG("MHF_OakLog"),
    PUMPKIN("MHF_Pumpkin"),
    TNT("MHF_TNT"),
    DYNAMITE("MHF_TNT2");

    private static final Base64 base64 = new Base64();
    private String id;

    private HeadUtility(String id) {
        this.id = id;
    }

    /**
     * Return a skull that has a custom texture specified by url.
     *
     * @param url skin url
     * @return itemstack
     */
    public static ItemStack getCustomSkull(String url, String title, List<String> lore) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
        List<String> newlore = new ArrayList<>();

        for (String string : lore) {
            newlore.add(ChatColor.translateAlternateColorCodes('&', string));
        }

        headMeta.setLore(newlore);
        head.setItemMeta(headMeta);
        return head;
    }

    public static String decode(String base) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base);
        return new String(decodedBytes);
    }

    public static ItemStack getCustomPlayerSkull(String name, String title, List<String> lore) {
        String value = decode(getPlayerValue(name));
        JsonObject jsonData = new JsonParser().parse(value).getAsJsonObject().get("textures").getAsJsonObject();
        JsonObject skinData = jsonData.get("SKIN").getAsJsonObject();
        String valueData = skinData.get("url").getAsString();
        return getCustomSkull(valueData, title, lore);
    }

    public static String getPlayerValue(String playerName) {
        try {
            String uuid = getUUID(playerName);
            URL url_0 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            JsonArray jsonData = new JsonParser().parse(reader_0).getAsJsonObject().get("properties").getAsJsonArray();
            JsonObject value = jsonData.get(0).getAsJsonObject();
            return value.get("value").getAsString();

        } catch (Exception e) {
            return null;
        }
    }

    public static String getUUID(String playerName) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            return new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getName(String UUID) {
        try {
            URL url_0 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + UUID);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            return new JsonParser().parse(reader_0).getAsJsonObject().get("name").getAsString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Return a skull of a player.
     *
     * @param name player's name
     * @return itemstack
     */
    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Return the skull's id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Return the skull of the enum.
     *
     * @return itemstack
     */
    public ItemStack getSkull() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(id);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
