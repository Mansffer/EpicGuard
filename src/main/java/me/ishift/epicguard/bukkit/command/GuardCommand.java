package me.ishift.epicguard.bukkit.command;

import me.ishift.epicguard.bukkit.GuardBukkit;
import me.ishift.epicguard.bukkit.gui.GuiMain;
import me.ishift.epicguard.bukkit.manager.BlacklistManager;
import me.ishift.epicguard.bukkit.manager.UserManager;
import me.ishift.epicguard.bukkit.manager.User;
import me.ishift.epicguard.bukkit.util.misc.MessagesBukkit;
import me.ishift.epicguard.universal.Config;
import me.ishift.epicguard.universal.util.ChatUtil;
import me.ishift.epicguard.universal.util.GeoAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class GuardCommand implements CommandExecutor {
    public static void send(CommandSender sender, String message) {
        sender.sendMessage(ChatUtil.fix(MessagesBukkit.PREFIX + message));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command c, String s, String[] args) {
        if (args.length == 0) {
            send(sender, "&7You are running &6EpicGuard v" + GuardBukkit.getInstance().getDescription().getVersion() + " &7by &ciShift & ruzekh&7.");
            if (sender instanceof Player && !sender.hasPermission(GuardBukkit.PERMISSION)) {
                send(sender, MessagesBukkit.NO_PERMISSION);
                return true;
            }
            send(sender, "&6/" + s + " menu &8- &7Open main plugin GUI.");
            send(sender, "&6/" + s + " status &8- &7Toggle antibot notifications (titles).");
            send(sender, "&6/" + s + " reload &8- &7Reload configuration and messages.");
            send(sender, "&6/" + s + " oplist &8- &7See opped players list.");
            send(sender, "&6/" + s + " player <player> &8- &7See information about specific player.");
            send(sender, "&6/" + s + " whitelist <adress> &8- &7Add specific adress to the whitelist.");
            send(sender, "&6/" + s + " blacklist <adress> &8- &7Add specific adress to the blacklist.");
            return true;
        }

        if (args[0].equalsIgnoreCase("menu")) {
            if (!(sender instanceof Player)) {
                send(sender, "&cThis command is player only.");
                return true;
            }
            GuiMain.show((Player) sender);
        } else if (args[0].equalsIgnoreCase("status")) {
            if (!(sender instanceof Player)) {
                send(sender, "&cThis command is player only.");
                return true;
            }
            final User user = UserManager.getUser((Player) sender);
            send(sender, (user.isNotifications() ? "&cToggled off" : "&aToggled on") + " &7bot notification status!");
            user.setNotifications(!user.isNotifications());
        } else if (args[0].equalsIgnoreCase("reload")) {
            send(sender, "&7Reloading config...");
            GuardBukkit.getInstance().reloadConfig();
            GuardBukkit.loadConfig();
            MessagesBukkit.load();
            send(sender, "&7Reloaded config &asuccesfully&7!");
        } else if (args[0].equalsIgnoreCase("oplist")) {
            Bukkit.getOperators().forEach(player -> {
                Date currentDate = new Date(player.getLastPlayed());
                send(sender, "&7" + player.getName() + " &8[" + (player.isOnline() ? "&aONLINE" : "&4OFFLINE") + "&8], &7Last Online: " + currentDate);
            });
        } else if (args[0].equalsIgnoreCase("player")) {
            if (args.length != 2) {
                send(sender, "&7Correct usage: &f/" + s + " player <player>");
                return true;
            }
            final Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                send(sender, "&7Player &c" + args[1] + " &7not found!");
                return true;
            }
            send(sender, "&7Name: &f" + player.getName());
            send(sender, "&7UUID: &f" + player.getUniqueId());
            send(sender, "&7Country: &f" + GeoAPI.getCountryCode(player.getAddress().getAddress()));
            send(sender, "&7Host Adress: &f" + player.getAddress().getAddress().getHostName());
            send(sender, "&7OP: " + (player.isOp() ? "&a&lYES" : "&c&lNO"));
            if (Config.ipHistoryEnable) {
                send(sender, "&6[IP History]");
                UserManager.getUser(player).getAdresses().forEach(adress -> send(sender, " &7- &f" + adress));
            }
        } else if (args[0].equalsIgnoreCase("whitelist")) {
            if (args.length != 2) {
                send(sender, "&7Correct usage: &f/" + s + " whitelist <adress>");
                return true;
            }
            BlacklistManager.whitelist(args[1]);
            send(sender, "&7Whitelisted IP: " + args[1]);
        } else if (args[0].equalsIgnoreCase("blacklist")) {
            if (args.length != 2) {
                send(sender, "&7Correct usage: &f/" + s + " blacklist <adress>");
                return true;
            }
            BlacklistManager.blacklist(args[1]);
            send(sender, "&7Blacklisted IP: " + args[1]);
        } else {
            send(sender, "&cCommand not found! Use &6/" + s);
        }
        return true;
    }
}
