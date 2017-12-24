package cc.deepinmc.glyph.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Easy to build a ItemStack object
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class ItemStackBuilder {

    private ItemStack currentItem;
    private ItemMeta currentMeta;

    public ItemStackBuilder(Material material) {
        currentItem = new ItemStack(material);
        currentMeta = currentItem.getItemMeta();
    }

    public ItemStackBuilder(Material material, int amount) {
        currentItem = new ItemStack(material, amount);
        currentMeta = currentItem.getItemMeta();
    }

    public ItemStackBuilder(Material material, int amount, short damage) {
        currentItem = new ItemStack(material, amount, damage);
        currentMeta = currentItem.getItemMeta();
    }

    public ItemStackBuilder displayName(String name) {
        currentMeta.setDisplayName(name.replaceAll("&", "§"));
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        currentMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStackBuilder lore(List<String> lore) {
        currentMeta.setLore(lore);
        return this;
    }

    public ItemStackBuilder clearLore() {
        currentMeta.setLore(Collections.emptyList());
        return this;
    }

    public ItemStackBuilder itemFlag(ItemFlag... flag) {
        currentMeta.addItemFlags(flag);
        return this;
    }

    public ItemStackBuilder unbreakable(boolean unbreakable) {
        currentItem.getItemMeta().spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        currentItem.setItemMeta(currentMeta);
        return currentItem;
    }

}