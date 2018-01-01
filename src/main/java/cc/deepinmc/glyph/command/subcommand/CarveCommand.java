package cc.deepinmc.glyph.command.subcommand;

import cc.deepinmc.glyph.command.SubCommand;
import cc.deepinmc.glyph.gui.CarveGUI;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public class CarveCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player!");
            return;
        }
        if (!sender.hasPermission("glyph.open")) {
            sender.sendMessage(LanguageConfigManager.getStringByDefault("permission_denied", "&6[&eGlyph&6] &c权限不足!", true));
            return;
        }
        Player player = (Player) sender;
        CarveGUI.getInstance().open(player);
    }
}
