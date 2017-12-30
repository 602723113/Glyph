package cc.deepinmc.glyph.listener;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.gui.CarveGUI;
import cc.deepinmc.glyph.gui.InlayGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
     * @see CarveGUI#handleEvent(InventoryClickEvent)
     * @see InlayGUI#handleEvent(InventoryClickEvent)
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.carve_GUI.title").replaceAll("&", "§"))) {
            event.setCancelled(true);
            CarveGUI.getInstance().handleEvent(event);
        }
        if (inventory.getTitle().equals(Entry.getInstance().getConfig().getString("gui_option.inlay_GUI.title").replaceAll("&", "§"))) {
            event.setCancelled(true);
            InlayGUI.getInstance().handleEvent(event);
        }
    }

}
