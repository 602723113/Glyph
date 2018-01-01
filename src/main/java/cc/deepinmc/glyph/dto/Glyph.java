package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
@Data
@AllArgsConstructor
public class Glyph {

    private Material material;
    private int data;
    private String name;
    private String displayName;
    private String loreName;
    private List<String> description;
    private List<GlyphAttribute> glyphAttributes;
    private boolean canUseGlyphPattern;
    private String glyphPattern;
    private List<EquipmentType> canInlayEquipmentType;

    public ItemStack getItemStack() {
        ItemStack is = new ItemStack(material, 1, (short) data);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayName.replaceAll("&", "§"));

        List<String> translated = description.stream()
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList());
        im.setLore(translated);
        is.setItemMeta(im);
        return is;
    }

}
