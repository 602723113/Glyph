package cc.deepinmc.glyph.listener;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.gui.CarveGUI;
import cc.deepinmc.glyph.gui.InlayGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public class GUIListener implements Listener {

    /**
     * handle when player click inlay or carve gui
     *
     * @param event inventory click event
     * @see CarveGUI#handleClickEvent(InventoryClickEvent)
     * @see InlayGUI#handleClickEvent(InventoryClickEvent)
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.carve_GUI.title").replaceAll("&", "§"))) {
            event.setCancelled(true);
            CarveGUI.getInstance().handleClickEvent(event);
        }
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.inlay_GUI.title").replaceAll("&", "§"))) {
            event.setCancelled(true);
            InlayGUI.getInstance().handleClickEvent(event);
        }
    }

    /**
     * handle when player close inlay or carve gui
     *
     * @param event inventory close event
     * @see CarveGUI#handleCloseEvent(InventoryCloseEvent)
     * @see InlayGUI#handleCloseEvent(InventoryCloseEvent)
     */
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.carve_GUI.title").replaceAll("&", "§"))) {
            CarveGUI.getInstance().handleCloseEvent(event);
        }
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.inlay_GUI.title").replaceAll("&", "§"))) {
            InlayGUI.getInstance().handleCloseEvent(event);
        }
    }
}
