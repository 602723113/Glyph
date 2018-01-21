package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.dto.PlayerAttribute;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;

import java.util.Map;

/**
 * @author Zoyn
 * @since 2018-01-21
 */
public final class PlayerAttributeManager {

    private Map<String, PlayerAttribute> attributeMap = Maps.newHashMap();

    public void addPlayerAttribute(String playerName, PlayerAttribute attribute) {
        attributeMap.put(Validate.notNull(playerName), Validate.notNull(attribute));
    }

    public PlayerAttribute getPlayerAttribute(String playerName) {
        if (attributeMap.containsKey(playerName)) {
            return attributeMap.get(playerName);
        } else {
            addPlayerAttribute(playerName, new PlayerAttribute());
            return getPlayerAttribute(playerName);
        }
    }

    public void clearPlayerAttribute() {
        attributeMap.clear();
    }

}
