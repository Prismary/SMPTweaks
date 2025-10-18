package net.prismarray.smptweaks;

import java.util.UUID;

public class InviteLog extends Config {

    private static InviteLog instance = new InviteLog("invites.yml");

    private InviteLog(String fileName) {
        super(fileName);
    }

    public static InviteLog getInstance() {
        return instance;
    }


    // Specific Methods
    public static boolean registerPlayer(String inviteeName, UUID inviteeID,  UUID inviterID) {
        if (isRegistered(inviteeID)) {
            return false;
        }
        getInstance().getConfig().set(inviteeID.toString() + ".name", inviteeName);
        getInstance().getConfig().set(inviteeID.toString() + ".invitedBy", inviterID == null ? "SERVER" : inviterID.toString());
        getInstance().getConfig().set(inviteeID.toString() + ".inviteTime", System.currentTimeMillis());


        getInstance().save();
        return true;
    }

    public static boolean suspendPlayer(UUID playerID, UUID suspenderID) {
        if (!isRegistered(playerID) || isSuspended(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString() + ".suspendedBy", suspenderID == null ? "SERVER" : suspenderID.toString());
        getInstance().getConfig().set(playerID.toString() + ".suspendTime", System.currentTimeMillis());

        getInstance().save();
        return true;
    }

    public static boolean reinstatePlayer(UUID playerID) {
        if (!isRegistered(playerID) || !isSuspended(playerID)) {
            return false;
        }

        getInstance().getConfig().set(playerID.toString() + ".suspendedBy", null);
        getInstance().getConfig().set(playerID.toString() + ".suspendTime", null);

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
        return getInstance().getConfig().contains(playerID.toString());
    }

    public static boolean isSuspended(UUID playerID) {
        return getInstance().getConfig().contains(playerID.toString() + ".suspendedBy");
    }

    // Getters

    public static UUID getInviteID(UUID playerID) {
        String entry = getInstance().getConfig().getString(playerID.toString() + ".invitedBy", "SERVER");
        if (entry.equals("SERVER")) {
            return null;
        }
        return UUID.fromString(entry);
    }

    public static String getInviteTime(UUID playerID) {
        return getInstance().getConfig().getString(playerID.toString() + ".inviteTime");
    }

    public static UUID getSuspenderID(UUID playerID) {
        return UUID.fromString(getInstance().getConfig().getString(playerID.toString() + ".suspendedBy"));
    }

    public static String getSuspendTime(UUID playerID) {
        return getInstance().getConfig().getString(playerID.toString() + ".suspendTime");
    }
}
