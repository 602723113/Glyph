package cc.deepinmc.glyph;

import cc.deepinmc.glyph.command.CommandHandler;
import cc.deepinmc.glyph.manager.GlyphManager;
import cc.deepinmc.glyph.util.ConfigurationUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
    private GlyphManager glyphManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        File languageFile = new File(getDataFolder(), "language.yml");
        if (!languageFile.exists()) {
            saveResource("language.yml", true);
        }
        languageConfig = ConfigurationUtils.loadYml(languageFile);

        // register command
        Bukkit.getPluginCommand("glyph").setExecutor(new CommandHandler());
        // initial glyph manager
        glyphManager = new GlyphManager();

        Bukkit.getConsoleSender().sendMessage("§6[§eGlyph§6] §fPower By DeepinMC, URL: https://github.com/DeepinMC");
    }

    /**
     * get Entry's instance
     *
     * @return {@link Entry}
     */
    public static Entry getInstance() {
        return instance;
    }

    public GlyphManager getGlyphManager() {
        return glyphManager;
    }
}
