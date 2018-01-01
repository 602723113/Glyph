package cc.deepinmc.glyph.gui;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.dto.Glyph;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import cc.deepinmc.glyph.util.PlayerUtils;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static cc.deepinmc.glyph.gui.ButtonEnum.*;

/**
 * Inlay glyph into the equipment GUI
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class InlayGUI implements IGUI {

    private static InlayGUI instance;

    private InlayGUI() {
    }

    public static InlayGUI getInstance() {
        if (instance == null) {
            instance = new InlayGUI();
        }
        return instance;
    }

    @Override
    public void open(Player player) {
        Validate.notNull(player);

        ItemStack itemInHand = PlayerUtils.getItemInMainHand(player);
        if (itemInHand == null) {
            player.sendMessage(LanguageConfigManager.getStringByDefault("player_hand_cannot_be_null", "&c手上不能没有任何物品!", true));
            return;
        }
        if (Entry.getInstance().getGlyphManager().isGlyph(itemInHand)) {
            player.sendMessage(LanguageConfigManager.getStringByDefault("glyphs_cannot_be_inlay_in_the_glyphs", "&6[&eGlyph&6] &c雕纹不能镶嵌在雕纹上!", true));
            return;
        }

        // 容器填充
        Inventory inventory = Bukkit.createInventory(null, 54, Entry.getInstance().getConfig().getString("gui_option.inlay_GUI.title").replaceAll("&", "§"));

        int[] blacks = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22, 26, 27, 31, 35, 36, 37, 38, 42, 43, 44, 45, 46, 47, 51, 52, 53};
        for (int i : blacks) {
            inventory.setItem(i, BLACK_GLASS_PANE.getItemStack());
        }
        int[] golds = {10, 11, 12, 19, 21, 28, 29, 30};
        for (int i : golds) {
            inventory.setItem(i, INLAY_GOLD_GLASS_PANE.getItemStack());
        }
        int[] greens = {14, 15, 16, 23, 25, 32, 33, 34};
        for (int i : greens) {
            inventory.setItem(i, INLAY_GREEN_GLASS_PANE.getItemStack());
        }
        int[] blues = {39, 40, 41, 48, 50};
        for (int i : blues) {
            inventory.setItem(i, INLAY_BLUE_GLASS_PANE.getItemStack());
        }
        inventory.setItem(20, itemInHand);
        inventory.setItem(49, INLAY_AXE.getItemStack());

        player.closeInventory();
        player.openInventory(inventory);
    }

    @Override
    public void handleClickEvent(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        // let the glyph can be move
        if (Entry.getInstance().getGlyphManager().isGlyph(event.getCurrentItem()) || Entry.getInstance().getGlyphManager().isGlyph(event.getCursor())) {
            event.setCancelled(false);
            return;
        }
        // prevent bug
        if (event.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) {
            event.setCancelled(true);
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 49) {
            ItemStack handItem = PlayerUtils.getItemInMainHand(player);
            ItemStack glyphItem = event.getInventory().getItem(24);

            if (glyphItem == null) {
                player.sendMessage(LanguageConfigManager.getStringByDefault("glyph_slot_null", "&6[&eGlyph&6] &c请放入雕纹符!", true));
                return;
            }
            if (!Entry.getInstance().getGlyphManager().isGlyph(glyphItem)) {
                player.sendMessage(LanguageConfigManager.getStringByDefault("put_wrong_glyph", "&6[&eGlyph&6] &c请放入正确的雕纹符!", true));
                return;
            }
            Glyph glyph = Entry.getInstance().getGlyphManager().getGlyphByItemName(glyphItem.getItemMeta().getDisplayName());
            if (!Entry.getInstance().getGlyphManager().canInlayGlyph(handItem, glyph)) {
                player.sendMessage(LanguageConfigManager.getStringByDefault("the_glyph_cannot_be_inlay_in_the_item", "&6[&eGlyph&6] &c该雕纹无法镶嵌在该物品上!", true));
                return;
            }
            // TODO...

            //clear item, avoid bugs when handlingCloseEvent
            event.getInventory().setItem(49, null);
        }
    }

    @Override
    public void handleCloseEvent(InventoryCloseEvent event) {

    }

}
