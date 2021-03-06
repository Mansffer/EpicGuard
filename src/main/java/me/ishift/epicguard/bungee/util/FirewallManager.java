package me.ishift.epicguard.bungee.util;

import me.ishift.epicguard.universal.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirewallManager {
    private static List<String> blackList = new ArrayList<>();
    private static List<String> whiiteList = new ArrayList<>();

    public static List<String> getBlackList() {
        return blackList;
    }

    public static List<String> getWhiiteList() {
        return whiiteList;
    }

    public static void blacklist(String adress) {
        blackList.add(adress);
        if (Config.firewallEnabled) {
            try {
                Runtime.getRuntime().exec(Config.firewallBlacklistCommand.replace("{IP}", adress));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void whitelist(String adress) {
        whiiteList.add(adress);
        if (Config.firewallEnabled) {
            try {
                Runtime.getRuntime().exec(Config.firewallWhitelistCommand.replace("{IP}", adress));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
