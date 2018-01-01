package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Zoyn
 * @since 2018-01-01
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAttribute {

    private double hit = 0;
    private double crit = 0;
    private double strength = 0;
    private double suckBlood = 0;
    private double dodge = 0;
    private double defense = 0;
    private double counter = 0;

}
