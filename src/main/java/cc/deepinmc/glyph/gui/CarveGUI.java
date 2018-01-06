package cc.deepinmc.glyph.gui;

import cc.deepinmc.glyph.Entry;
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
 * Carve glyph GUI
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class CarveGUI implements IGUI {

    private static CarveGUI instance;

    private CarveGUI() {
    }

    public static CarveGUI getInstance() {
        if (instance == null) {
            instance = new CarveGUI();
        }
        return instance;
    }

    @Override
    public void open(Player player) {
        Validate.notNull(player);

        Inventory inventory = Bukkit.createInventory(null, 54, Entry.getInstance().getConfig().getString("gui_option.carve_GUI.title").replaceAll("&", "§"));

        int[] golds = {0, 1, 2, 9, 11, 18, 19, 20};
        for (int i : golds) {
            inventory.setItem(i, CARVE_GOLD_GLASS_PANE.getItemStack());
        }
        int[] yellows = {3, 4, 5, 12, 14, 21, 22, 23};
        for (int i : yellows) {
            inventory.setItem(i, CARVE_YELLOW_GLASS_PANE.getItemStack());
        }
        int[] greens = {6, 7, 8, 15, 17, 24, 25, 26};
        for (int i : greens) {
            inventory.setItem(i, CARVE_GREEN_GLASS_PANE.getItemStack());
        }
        int[] reds = {39, 40, 41, 48, 50};
        for (int i : reds) {
            inventory.setItem(i, CARVE_RED_GLASS_PANE.getItemStack());
        }
        int[] blacks = {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 42, 43, 44, 45, 46, 47, 51, 52, 53};
        for (int i : blacks) {
            inventory.setItem(i, BLACK_GLASS_PANE.getItemStack());
        }
        inventory.setItem(16, CARVE_BARRIER.getItemStack());
        inventory.setItem(49, CARVE_ANVIL.getItemStack());

        player.closeInventory();
        player.openInventory(inventory);
    }

    /**
     * to handle a inventory click event
     *
     * @param event inventory click event object
     * @see PlayerUtils#getItemInMainHand(Player)
     */
    @Override
    public void handleClickEvent(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        // prevent bug
        if (event.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) {
            event.setCancelled(true);
            return;
        }
        // let the glyph can be move
        if (Entry.getInstance().getGlyphManager().isGlyph(event.getCurrentItem()) ||
                Entry.getInstance().getGlyphManager().isGlyph(event.getCursor())) {
            event.setCancelled(false);
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 49) {
            ItemStack materialItem = event.getInventory().getItem(10);
            ItemStack patternItem = event.getInventory().getItem(13);

        }
    }

    @Override
    public void handleCloseEvent(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getItem(10) != null) {
            event.getPlayer().getInventory().addItem(inventory.getItem(10));
        }
        if (inventory.getItem(13) != null) {
            event.getPlayer().getInventory().addItem(inventory.getItem(13));
        }
    }

}

