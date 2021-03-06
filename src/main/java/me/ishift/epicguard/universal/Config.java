package me.ishift.epicguard.universal;

import java.util.List;

public class Config {
    public static String firewallBlacklistCommand;
    public static String firewallWhitelistCommand;
    public static boolean firewallEnabled;
    public static int connectSpeed;
    public static int pingSpeed;
    public static int joinSpeed;
    public static boolean autoWhitelist;
    public static int autoWhitelistTime;
    public static String apiKey;
    public static List<String> countryList;
    public static List<String> blockedNames;
    public static String countryMode;
    public static boolean antibot;
    public static boolean updater;
    public static long attackResetTimer;
    public static boolean tabCompleteBlock;
    public static boolean bandwidthOptimizer;

    public static List<String> blockedCommands;
    public static List<String> allowedCommands;
    public static List<String> opProtectionList;
    public static String opProtectionAlert;
    public static String opProtectionCommand;
    public static String allowedCommandsBypass;
    public static boolean blockedCommandsEnable;
    public static boolean allowedCommandsEnable;
    public static boolean opProtectionEnable;
    public static boolean ipHistoryEnable;
    public static boolean forceRejoin;
    public static boolean pexProtection;

    public static boolean cloudEnabled;
    public static boolean cloudBlacklist;
    public static long cloudTime;
    public static boolean heuristicsEnabled;
    public static int heuristicsDiff;

    public static boolean filterEnabled;
    public static List<String> filterValues;

    public static boolean channelVerification;
    public static long channelDelay;
    public static String channelPunish;
    public static boolean blockedBrands;
    public static String blockedBrandsPunish;
    public static List<String> blockedBrandsValues;

    public static boolean customTabComplete;
    public static List<String> customTabCompleteList;
}
