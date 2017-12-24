package cc.deepinmc.glyph;

import cc.deepinmc.glyph.command.CommandHandler;
import cc.deepinmc.glyph.util.ConfigurationUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class Entry extends JavaPlugin {

    private static Entry instance;
    @Getter
    private FileConfiguration languageConfig;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("language.yml", false);
        languageConfig = ConfigurationUtils.loadYml(getDataFolder().getAbsolutePath() + "\\language.yml");

        // register command
        Bukkit.getPluginCommand("glyph").setExecutor(new CommandHandler());
    }

    public static Entry getInstance() {
        return instance;
    }

}
