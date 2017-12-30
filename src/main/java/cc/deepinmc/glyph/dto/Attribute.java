package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zoyn
 * @since 2017-12-31
 */
@Data
@AllArgsConstructor
public class Attribute {

    private EffectType type;
    private double odds = 100;
    private double value;

    public Attribute(EffectType type, double value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type.toString() + ":" + odds + ":" + value;
    }
}
