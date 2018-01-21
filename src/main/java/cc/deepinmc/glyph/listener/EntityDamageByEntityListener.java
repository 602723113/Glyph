package cc.deepinmc.glyph.listener;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.dto.PlayerAttribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author Zoyn
 * @since 2018-01-06
 */
public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamage() == 0) {
            return;
        }

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            PlayerAttribute attribute = Entry.getInstance().getAttributeManager().getPlayerAttribute(player.getName());
        }
    }

}
