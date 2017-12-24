package cc.deepinmc.glyph;

import cc.deepinmc.glyph.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class Entry extends JavaPlugin {

    private static Entry instance;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginCommand("glyph").setExecutor(new CommandHandler());

    }

    public static Entry getInstance() {
        return instance;
    }
}
