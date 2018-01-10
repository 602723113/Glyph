package cc.deepinmc.glyph.gui;

import cc.deepinmc.glyph.util.ItemStackBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public enum ButtonEnum {

    BLACK_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 15)
            .displayName("§7§l分割线")
            .build()),
    INLAY_GOLD_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 1)
            .displayName("§6§l请放入武器/装备")
            .build()),
    INLAY_GREEN_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 5)
            .displayName("§a§l请放入雕纹")
            .build()),
    INLAY_BLUE_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 3)
            .displayName("§3§l点击旁边的斧子即可雕纹")
            .build()),
    INLAY_AXE(new ItemStackBuilder(Material.getMaterial(258), 1)
            .displayName("§e§l点击进行装备雕纹")
            .lore(
                    "§e§l● §f§l§m                      ",
                    "§e§l● §7将上方的雕纹与装备进行雕琢",
                    "§e§l● §f§l§m                      "
            )
            .build()),
    CARVE_GOLD_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 1)
            .displayName("§6§l请放入材料")
            .build()),
    CARVE_GREEN_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 5)
            .displayName("§a§l暂时关闭")
            .build()),
    CARVE_YELLOW_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 4)
            .displayName("§e§l请放入雕纹图样")
            .build()),
    CARVE_RED_GLASS_PANE(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 14)
            .displayName("§3§l点击旁边的铁砧即可进行雕刻")
            .build()),
    CARVE_BARRIER(new ItemStackBuilder(Material.getMaterial(160), 1, (short) 14)
            .displayName("§7§l暂时关闭")
            .build()),
    CARVE_ANVIL(new ItemStackBuilder(Material.getMaterial(145), 1)
            .displayName("§e§l点击进行雕纹雕刻")
            .build());

    @Getter
    private ItemStack itemStack;

    ButtonEnum(ItemStack itemStack) {
        this.itemStack = itemStack;
    }


}
