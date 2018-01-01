package cc.deepinmc.glyph.command.subcommand;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.command.SubCommand;
import cc.deepinmc.glyph.dto.Glyph;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zoyn
 * @since 2018-01-01
 */
public class GetCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("glyph.get")) {
            sender.sendMessage(LanguageConfigManager.getStringByDefault("permission_denied", "&6[&eGlyph&6] &c权限不足!", true));
            return;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player!");
            return;
        }
        if (args.length != 2) {
            sender.sendMessage(LanguageConfigManager.getStringByDefault("command_parameter_length_is_incorrect", "&6[&eGlyph&6] &c指令参数不正确! 请输入指令/glyph help获取帮助", true));
            return;
        }
        Player player = (Player) sender;
        String glyphName = args[1];
        Glyph glyph = Entry.getInstance().getGlyphManager().getGlyphByName(glyphName);
        if (glyph == null) {
            player.sendMessage(LanguageConfigManager.getStringByDefault("glyph_does_not_exist", "&6[&eGlyph&6] &c雕纹不存在!", true));
        } else {
            player.getInventory().addItem(glyph.getItemStack());
            player.sendMessage(LanguageConfigManager.getStringByDefault("get_success: ", "&6[&eGlyph&6] &a获取雕纹成功!", true));
        }
    }
}
