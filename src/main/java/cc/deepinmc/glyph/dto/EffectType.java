package cc.deepinmc.glyph.dto;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public enum EffectType {

    HIT("命中"),
    CRIT("暴击"),
    STRENGTH("力量"),
    SUCK_BLOOD("吸血"),
    DODGE("闪避"),
    DEFENSE("防御");

    private String name;

    EffectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
