package cc.deepinmc.glyph.command;

import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2017-12-24
 */
public interface SubCommand {

    void execute(CommandSender sender, String[] args);

}
