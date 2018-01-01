package cc.deepinmc.glyph.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * An interface to perfect GUI
 * @author Zoyn
 * @since 2017-12-30
 */
public interface IGUI {

    void open(Player player);

    void handleClickEvent(InventoryClickEvent event);

    void handleCloseEvent(InventoryCloseEvent event);

}
