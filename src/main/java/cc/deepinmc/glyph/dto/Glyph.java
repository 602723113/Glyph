package cc.deepinmc.glyph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
@Data
@AllArgsConstructor
public class Glyph {

    private String name;
    private Attribute attribute;

}
