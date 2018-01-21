package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zoyn
 * @since 2017-12-31
 */
@Data
@AllArgsConstructor
public class GlyphAttribute {

    private EffectType type;
    private double odds = 100;
    private double value;

    public GlyphAttribute(EffectType type, double value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type.toString() + ":" + odds + ":" + value;
    }

    public PlayerAttribute toPlayerAttribute() {
        PlayerAttribute attribute = new PlayerAttribute();
        switch (type) {
            case HIT:
                attribute.setHit(value);
            case CRIT:
                attribute.setCrit(value);
            case STRENGTH:
                attribute.setStrength(value);
            case SUCK_BLOOD:
                attribute.setSuckBlood(value);
            case DODGE:
                attribute.setDodge(value);
            case DEFENSE:
                attribute.setDefense(value);
            default:
                return attribute;
        }
    }
}
