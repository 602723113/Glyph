package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zoyn
 * @since 2018-01-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAttribute {

    private double hit = 0;
    private double crit = 0;
    private double strength = 0;
    private double suckBlood = 0;
    private double dodge = 0;
    private double defense = 0;

}
