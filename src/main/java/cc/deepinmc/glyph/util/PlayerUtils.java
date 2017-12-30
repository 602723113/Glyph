package cc.deepinmc.glyph.util;

import cc.deepinmc.glyph.util.reflect.ReflectionUtils;
import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public final class PlayerUtils {

    /**
     * 反射玩家手中的物品
     *
     * @param player 玩家
     * @return {@link ItemStack}
     */
    public static ItemStack getItemInMainHand(Player player) {
        Validate.notNull(player);

        if (!player.isOnline()) {
            return null;
        }
        boolean hasMethod = ReflectionUtils.hasMethod(NMSUtils.getOBCClass("inventory.CraftInventoryPlayer"), "getItemInMainHand");
        if (hasMethod) {
            return player.getInventory().getItemInMainHand();
        } else {
            return player.getItemInHand();
        }
    }

}
