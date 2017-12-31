package cc.deepinmc.glyph.manager;

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
        int id = itemStack.getTypeId();
        List<EquipmentType> types = glyph.getCanInlayEquipmentType();
        // TODO...
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
                .anyMatch(glyph -> glyph.getName().equals(itemStack.getItemMeta().getDisplayName()));
    }

    public void addGlyph(String name, Glyph glyph) {
        glyphMap.put(Validate.notNull(name), Validate.notNull(glyph));
    }

    public void removeGlyph(String name) {
        if (glyphMap.containsKey(name)) {
            glyphMap.remove(name);
        }
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
                .filter(glyph -> glyph.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
