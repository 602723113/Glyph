package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.dto.Attribute;
import cc.deepinmc.glyph.dto.EffectType;
import cc.deepinmc.glyph.dto.Glyph;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public final class GlyphManager {

    private static Map<String, Glyph> glyphMap = Maps.newHashMap();

    static {
        glyphMap.put("吸血雕纹", new Glyph("吸血雕纹", new Attribute("吸血", Collections.singletonList(EffectType.SUCK_BLOOD))));
        glyphMap.put("力量雕纹", new Glyph("力量雕纹", new Attribute("力量", Collections.singletonList(EffectType.STRENGTH))));
        glyphMap.put("暴击雕纹", new Glyph("暴击雕纹", new Attribute("暴击", Collections.singletonList(EffectType.CRIT))));
    }



}
