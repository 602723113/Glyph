package cc.deepinmc.glyph.dto;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public enum EffectType {
    SUCK_BLOOD("吸血"),
    CRIT("暴击"),
    DEFENSE("防御"),
    STRENGTH("力量"),
    DODGE("闪避");

    private String name;

    EffectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
