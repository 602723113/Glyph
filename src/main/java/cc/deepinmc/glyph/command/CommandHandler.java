package cc.deepinmc.glyph.command;

import cc.deepinmc.glyph.command.subcommand.CarveCommand;
import cc.deepinmc.glyph.command.subcommand.HelpCommand;
import cc.deepinmc.glyph.command.subcommand.InlayCommand;
import cc.deepinmc.glyph.command.subcommand.ReloadCommand;
import cc.deepinmc.glyph.manager.LanguageConfigManager;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * To handle glyph's command
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class CommandHandler implements CommandExecutor {

    private static Map<String, SubCommand> commandMap = Maps.newHashMap();

    /**
     * Initialize all sub commands
     */
    public CommandHandler() {
        registerCommand("help", new HelpCommand());
        registerCommand("carve", new CarveCommand());
        registerCommand("inlay", new InlayCommand());
        registerCommand("reload", new ReloadCommand());
    }

    private void registerCommand(String commandName, SubCommand subCommand) {
        if (commandMap.containsKey(commandName)) {
            Bukkit.getLogger().warning("duplicate add command!");
        }
        commandMap.put(commandName, subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            commandMap.get("help").execute(sender, args);
            return true;
        }
        if (!commandMap.containsKey(args[0])) {
            sender.sendMessage(LanguageConfigManager.getStringByDefault("unknown_command", "&c未知指令!", true));
            return true;
        }
        // args[0] ---> SubCommand name
        SubCommand subCommand = commandMap.get(args[0]);
        subCommand.execute(sender, args);
        return true;
    }
}
