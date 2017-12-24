package cc.deepinmc.glyph.gui;

import cc.deepinmc.glyph.Entry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static cc.deepinmc.glyph.gui.ButtonEnum.*;

/**
 * Inlay glyph into the equipment GUI
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class InlayGUI {

    private static InlayGUI instance;

    private InlayGUI() {
    }

    public static InlayGUI getInstance() {
        if (instance == null) {
            instance = new InlayGUI();
        }
        return instance;
    }

    public void open(Player player) {
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
        inventory.setItem(20, player.getItemInHand());
        inventory.setItem(49, INLAY_AXE.getItemStack());

        player.closeInventory();
        player.openInventory(inventory);
    }

}
