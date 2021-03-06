package me.ishift.epicguard.bukkit.task;

import me.ishift.epicguard.bukkit.manager.AttackManager;
import me.ishift.epicguard.bukkit.util.misc.MessagesBukkit;
import me.ishift.epicguard.bukkit.util.misc.Notificator;

public class AttackTitleTask implements Runnable {
    @Override
    public void run() {
        if (AttackManager.isUnderAttack()) {
            Notificator.title(MessagesBukkit.ATTACK_TITLE.replace("{CPS}", String.valueOf(AttackManager.getConnectPerSecond())), MessagesBukkit.ATTACK_SUBTITLE.replace("{MAX}", String.valueOf(AttackManager.getTotalBots())).replace("{TIME}", String.valueOf(HeuristicsTask.getTime())));
        }
    }
}
