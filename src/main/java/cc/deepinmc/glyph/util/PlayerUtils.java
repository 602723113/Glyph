package cc.deepinmc.glyph.util;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.dto.Glyph;
import cc.deepinmc.glyph.dto.GlyphAttribute;
import cc.deepinmc.glyph.dto.PlayerAttribute;
import cc.deepinmc.glyph.util.reflect.ReflectionUtils;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public final class PlayerUtils {

    /**
     * 反射获取玩家手中的物品
     * <br />
     * use reflect to get player hand item
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

    public static PlayerAttribute getPlayerEquipmentAttribute(Player player) {
        PlayerAttribute attribute = new PlayerAttribute();

        // player equipments
        List<ItemStack> itemStacks = Arrays.asList(player.getEquipment().getArmorContents());
        ItemStack itemInHand = getItemInMainHand(player);
        if (itemInHand != null && !itemInHand.getType().equals(Material.AIR)) {
            itemStacks.add(itemInHand);
        }

        for (ItemStack itemStack : itemStacks) {
            List<Glyph> glyphList = Entry.getInstance().getGlyphManager().getItemGlyphs(itemStack);
            if (!glyphList.isEmpty()) {
                for (Glyph glyph : glyphList) {
                    for (GlyphAttribute glyphAttribute : glyph.getGlyphAttributes()) {
                        attribute = sumAttribute(attribute, glyphAttribute.toPlayerAttribute());
                    }
                }
            }
        }

        return attribute;
    }

    public static PlayerAttribute sumAttribute(PlayerAttribute... attributes) {
        PlayerAttribute attribute = new PlayerAttribute();
        for (PlayerAttribute playerAttribute : attributes) {
            attribute.setHit(attribute.getHit() + playerAttribute.getHit());
            attribute.setCrit(attribute.getCrit() + playerAttribute.getCrit());
            attribute.setStrength(attribute.getStrength() + playerAttribute.getStrength());
            attribute.setSuckBlood(attribute.getSuckBlood() + playerAttribute.getSuckBlood());
            attribute.setDodge(attribute.getDodge() + playerAttribute.getDodge());
            attribute.setDefense(attribute.getDefense() + playerAttribute.getDefense());
        }
        return attribute;
    }

}
