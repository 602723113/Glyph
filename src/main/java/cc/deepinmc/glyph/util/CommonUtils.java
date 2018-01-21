package cc.deepinmc.glyph.util;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2018-01-21
 */
public final class CommonUtils {

    /**
     * 获取在线玩家
     * <p>
     * get online players
     *
     * @return {@link List}
     */
    public static List<Player> getOnlinePlayers() {
        List<Player> playerList = Lists.newArrayList();
        Bukkit.getWorlds().forEach(world -> playerList.addAll(world.getPlayers()));

        return playerList;
    }

}
