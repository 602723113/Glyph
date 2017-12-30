package cc.deepinmc.glyph.dto;

import lombok.Getter;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public enum EquipmentType {

    HEAD("头"),
    CHEST("身"),
    LEGS("腿"),
    SHOES("鞋"),
    WEAPON("武器"),
    ALL("所有");

    @Getter
    private String alias;

    EquipmentType(String alias) {
        this.alias = alias;
    }

}
