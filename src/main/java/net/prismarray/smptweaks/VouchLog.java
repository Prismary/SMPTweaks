package net.prismarray.smptweaks;

import java.util.UUID;

public class VouchLog extends Config {

    private static VouchLog instance = new VouchLog("vouches.yml");

    private VouchLog(String fileName) {
        super(fileName);
    }

    public static VouchLog getInstance() {
        return instance;
    }


    // Specific Methods
    public static boolean registerPlayer(UUID playerID, UUID vouchID) {
        if (isRegistered(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString() + ".vouchID", vouchID.toString());

        getInstance().save();
        return true;
    }

    public static boolean suspendPlayer(UUID playerID, UUID suspenderID) {
        if (!isRegistered(playerID) || isSuspended(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString() + ".suspenderID", suspenderID.toString());

        getInstance().save();
        return true;
    }

    public static boolean reinstatePlayer(UUID playerID) {
        if (!isRegistered(playerID) || !isSuspended(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString() + ".suspenderID", null);

        getInstance().save();
        return true;
    }

    public static boolean purgePlayer(UUID playerID) {
        if (!isRegistered(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString(), null);

        getInstance().save();
        return true;
    }

    public static boolean isRegistered(UUID playerID) {
        if (getInstance().getConfig().contains(playerID.toString())) {
            return true;
        }
        return false;
    }

    public static boolean isSuspended(UUID playerID) {
        if (getInstance().getConfig().contains(playerID.toString() + ".suspenderID")) {
            return true;
        }
        return false;
    }

    // Getters

    public static UUID getVouchID(UUID playerID) {
        return UUID.fromString(getInstance().getConfig().getString(playerID.toString() + ".vouchID"));
    }

    public static UUID getSuspenderID(UUID playerID) {
        return UUID.fromString(getInstance().getConfig().getString(playerID.toString() + ".suspenderID"));
    }
}
