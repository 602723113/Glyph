package cc.deepinmc.glyph.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public interface IGUI {

    void handleEvent(InventoryClickEvent event);

    void open(Player player);

}
