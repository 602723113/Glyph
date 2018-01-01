package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.dto.EquipmentType;
import cc.deepinmc.glyph.dto.Glyph;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * A class for managing Glyph
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public final class GlyphManager {

    private Map<String, Glyph> glyphMap = Maps.newHashMap();

    public boolean canInlayGlyph(ItemStack itemStack, Glyph glyph) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }
        List<EquipmentType> types = glyph.getCanInlayEquipmentType();

        for (EquipmentType type : types) {
            List<String> list = Entry.getInstance().getConfig().getStringList("weapon_option.type." + type.toString());
            for (String temp : list) {
                // the temp will be like this  12:1
                String[] idAndData = temp.split(":");
                // get id and data
                int id = Integer.parseInt(idAndData[0]);
                // check when the damage data is *
                if (idAndData[1].equals("*")) {
                    if (itemStack.getTypeId() == id) {
                        return true;
                    }
                }
                int data = Integer.parseInt(idAndData[1]);
                if (itemStack.getTypeId() == id && itemStack.getDurability() == data) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isGlyph(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }
        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName()) {
            return false;
        }

        return glyphMap.values()
                .stream()
                .anyMatch(glyph -> glyph.getDisplayName().equals(itemStack.getItemMeta().getDisplayName()));
    }

    public void addGlyph(String name, Glyph glyph) {
        glyphMap.put(Validate.notNull(name), Validate.notNull(glyph));
    }

    public void removeGlyph(String name) {
        if (glyphMap.containsKey(name)) {
            glyphMap.remove(name);
        }
    }

    public void clearGlyph() {
        glyphMap.clear();
    }

    /**
     * get glyph object by the glyph's name
     *
     * @param name the glyph's name
     * @return {@link Glyph}
     */
    public Glyph getGlyphByName(String name) {
        Validate.notNull(name);
        if (glyphMap.containsKey(name)) {
            return glyphMap.get(name);
        }
        return null;
    }

    /**
     * get glyph object by the glyph item's name
     *
     * @param name the glyph's name
     * @return {@link Glyph}
     */
    public Glyph getGlyphByItemName(String name) {
        Validate.notNull(name);
        return glyphMap.values()
                .stream()
                .filter(glyph -> glyph.getDisplayName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
