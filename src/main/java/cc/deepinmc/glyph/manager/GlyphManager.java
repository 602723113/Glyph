package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.dto.EquipmentType;
import cc.deepinmc.glyph.dto.Glyph;
import com.google.common.collect.Lists;
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
    private Map<String, Glyph> patternMap = Maps.newHashMap();
    private List<String> materialList = Lists.newArrayList();

    /**
     * 检查物品是否可以镶嵌该雕纹
     * <p>
     * check whether a glyph can be inlay in the item
     *
     * @param itemStack the item
     * @param glyph     the glyph
     * @return true mean can be inlay
     */
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

    /**
     * 检测一个物品是否为图样
     * <p>
     * check if an item is pattern
     *
     * @param itemStack the item
     * @return true mean the item is a pattern
     */
    public boolean isPattern(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }
        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName()) {
            return false;
        }

        return patternMap.keySet()
                .stream()
                .anyMatch(s -> s.equals(itemStack.getItemMeta().getDisplayName()));
    }

    /**
     * 检测一个物品是否为材料
     * <p>
     * check if an item is material
     *
     * @param itemStack the item
     * @return true mean the item is a material
     */
    public boolean isMaterial(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }
        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName()) {
            return false;
        }
        return materialList.contains(itemStack.getItemMeta().getDisplayName());
    }

    public void addGlyph(String name, Glyph glyph) {
        glyphMap.put(Validate.notNull(name), Validate.notNull(glyph));
    }

    public void addPattern(String pattern, Glyph glyph) {
        if (pattern == null || pattern.isEmpty() || pattern.equalsIgnoreCase("")) {
            return;
        }
        patternMap.put(Validate.notNull(pattern), Validate.notNull(glyph));
    }

    public void addMaterial(String material) {
        if (material == null || material.isEmpty() || material.equalsIgnoreCase("")) {
            return;
        }
        this.materialList.add(Validate.notNull(material));
    }

    public void removeGlyph(String name) {
        if (glyphMap.containsKey(name)) {
            glyphMap.remove(name);
        }
    }

    public void clearGlyph() {
        glyphMap.clear();
    }

    public void clearPattern() {
        patternMap.clear();
    }

    public void clearMaterial() {
        materialList.clear();
    }

    /**
     * get glyph object by the glyph's name
     *
     * @param name the glyph's name
     * @return {@link Glyph}
     */
    public Glyph getGlyphByName(String name) {
        return glyphMap.getOrDefault(Validate.notNull(name), null);
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


    public Glyph getPatternGlyphByItemName(String pattern) {
        return glyphMap.values()
                .stream()
                .filter(glyph -> glyph.getGlyphPattern().equals(pattern))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取图样所对应的雕纹, 如果无法获取则返回null
     * <p>
     * get the glyph corresponding to the pattern, or null if it is not available
     *
     * @param pattern the pattern
     * @return {@link Glyph}
     */
    public Glyph getPatternGlyph(String pattern) {
        return patternMap.getOrDefault(pattern, null);
    }
}
