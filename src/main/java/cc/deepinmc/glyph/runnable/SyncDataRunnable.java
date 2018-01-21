package cc.deepinmc.glyph.runnable;

import cc.deepinmc.glyph.util.CommonUtils;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Zoyn
 * @since 2018-01-21
 */
public class SyncDataRunnable extends BukkitRunnable {

    @Override
    public void run() {
        CommonUtils.getOnlinePlayers().forEach(player -> {

        });
    }
}
