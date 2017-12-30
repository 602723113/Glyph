package cc.deepinmc.glyph.command.subcommand;

import cc.deepinmc.glyph.Entry;
import cc.deepinmc.glyph.command.SubCommand;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import cc.deepinmc.glyph.util.ConfigurationUtils;
import org.bukkit.command.CommandSender;

import java.io.File;

/**
 * @author Zoyn
 * @since 2017-12-30
 */
public class ReloadCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(LanguageConfigManager.getStringByDefault("permission_denied", "&6[&eGlyph&6] &c权限不足!", true));
            return;
        }
        Entry.getInstance().reloadConfig();
        // reload language file
        File languageFile = new File(Entry.getInstance().getDataFolder(), "language.yml");
        if (!languageFile.exists()) {
            Entry.getInstance().saveResource("language.yml", true);
        }
        Entry.getInstance().setLanguageConfig(ConfigurationUtils.loadYml(languageFile));
        Entry.getInstance().loadGlyphs();

        sender.sendMessage(LanguageConfigManager.getStringByDefault("reload_success", "&6[&eGlyph&6] &a重载成功!", true));
    }
}
