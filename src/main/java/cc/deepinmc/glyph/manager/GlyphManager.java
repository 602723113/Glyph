package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.dto.Glyph;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;

import java.util.Map;

/**
 * A class for managing Glyph
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public final class GlyphManager {

    private Map<String, Glyph> glyphMap = Maps.newHashMap();

    public void addGlyph(String name, Glyph glyph) {
        glyphMap.put(Validate.notNull(name), Validate.notNull(glyph));
    }

    public void removeGlyph(String name) {
        if (glyphMap.containsKey(name)) {
            glyphMap.remove(name);
        }
    }

    /**
     * get glyph object by the glyph's name
     *
     * @param name the glyph's name
     * @return {@link Glyph}
     */
    public Glyph getGlyphByName(String name) {
        Validate.notNull(name);
        if (glyphMap.containsKey(name)) {
            return glyphMap.get(name);
        }

        return null;
    }

}
