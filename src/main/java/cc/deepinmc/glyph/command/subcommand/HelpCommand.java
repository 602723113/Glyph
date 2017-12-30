package cc.deepinmc.glyph.command.subcommand;

import cc.deepinmc.glyph.command.SubCommand;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public class HelpCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        LanguageConfigManager.getStringListByDefault("help", Collections.singletonList("&c帮助信息丢失!"), true)
                .forEach(sender::sendMessage);
    }
}
