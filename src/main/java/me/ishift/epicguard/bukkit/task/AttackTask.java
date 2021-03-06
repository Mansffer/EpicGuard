package me.ishift.epicguard.bukkit.task;

import me.ishift.epicguard.bukkit.manager.AttackManager;
import me.ishift.epicguard.bukkit.util.misc.Notificator;
import me.ishift.epicguard.universal.Config;

public class AttackTask implements Runnable {

    @Override
    public void run() {
        if (AttackManager.getConnectPerSecond() < 0) {
            AttackManager.setConnectPerSecond(0);
        }
        if (AttackManager.getJoinPerSecond() < Config.joinSpeed && AttackManager.getPingPerSecond() < Config.pingSpeed && AttackManager.getConnectPerSecond() < Config.connectSpeed) {
            if (AttackManager.isUnderAttack()) {
                AttackManager.setAttackMode(false);
                Notificator.title("&cATTACK HAS BEEN FINISHED", "&7Bots blocked during last attack: &4" + AttackManager.getTotalBots());
                HeuristicsTask.setRecord(0);
                HeuristicsTask.setTime(0);
                HeuristicsTask.setBlacklistInc(0);
                AttackManager.setTotalBots(0);
            }
        }
    }
}
