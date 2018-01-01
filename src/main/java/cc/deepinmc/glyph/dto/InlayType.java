package cc.deepinmc.glyph.dto;

import cc.deepinmc.glyph.Entry;
import lombok.Getter;

/**
 * @author Zoyn
 * @since 2018-01-01
 */
public enum InlayType {

    TITLE_LORE(Entry.getInstance().getConfig().getString("general_option.inlay.type.title_lore")),
    VACANCY_LORE(Entry.getInstance().getConfig().getString("general_option.inlay.type.vacancy_lore"));

    @Getter
    private String placeHolder;

    InlayType(String placeHolder) {
        this.placeHolder = placeHolder;
    }

}
