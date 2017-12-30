package cc.deepinmc.glyph;

import cc.deepinmc.glyph.command.CommandHandler;
import cc.deepinmc.glyph.dto.EffectType;
import cc.deepinmc.glyph.dto.Glyph;
import cc.deepinmc.glyph.manager.GlyphManager;
import cc.deepinmc.glyph.util.ConfigurationUtils;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Getter
    private File glyphFolder;
    @Getter
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

        glyphFolder = new File(getDataFolder(), "glyphs");
        if (!glyphFolder.exists()) {
            glyphFolder.mkdirs();
            try {
                writeToLocal(new File(glyphFolder, "力量雕纹.yml").getAbsolutePath(), getResource("力量雕纹.yml"));
                writeToLocal(new File(glyphFolder, "吸血雕纹.yml").getAbsolutePath(), getResource("吸血雕纹.yml"));
                writeToLocal(new File(glyphFolder, "狂暴雕纹.yml").getAbsolutePath(), getResource("狂暴雕纹.yml"));
                writeToLocal(new File(glyphFolder, "闪避雕纹.yml").getAbsolutePath(), getResource("闪避雕纹.yml"));
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§6[§eGlyph§6] §cAn error occurred while saving the default glyph");
            }
        }

        // register command
        Bukkit.getPluginCommand("glyph").setExecutor(new CommandHandler());
        // initial glyph manager
        glyphManager = new GlyphManager();

        loadGlyphs();

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

    private void loadGlyphs() {
        File[] files = Validate.notNull(glyphFolder.listFiles());
        Arrays.stream(files).forEach(file -> {
            FileConfiguration fileConfiguration = ConfigurationUtils.loadYml(file);
            String fileName = file.getName();
            // var load
            int material = fileConfiguration.getInt("Glyph.material");
            int data = fileConfiguration.getInt("Glyph.data");
            String name = fileConfiguration.getString("Glyph.name").replaceAll("&", "§");
            List<String> description = fileConfiguration.getStringList("Glyph.description");
            boolean canUseGlyphPattern = fileConfiguration.getBoolean("Glyph.can_use_glyph_pattern");
            String glyphPattern = fileConfiguration.getString("Glyph.glyph_pattern");
            List<EffectType> effects = fileConfiguration.getStringList("Glyph.effects").stream().map(EffectType::valueOf).collect(Collectors.toList());

            // construct glyph
            Glyph glyph = new Glyph(Material.getMaterial(material), data, name, description, effects, canUseGlyphPattern, glyphPattern);
            // add to manager
            this.glyphManager.addGlyph(fileName.replaceAll(".yml", ""), glyph);
            Bukkit.getConsoleSender().sendMessage("§6[§eGlyph§6] §fLoad glyph " + name + " §fsuccessfully");
        });
    }

    private void writeToLocal(String path, InputStream input) throws IOException {
        if (input == null) {
            return;
        }
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile;
        downloadFile = new FileOutputStream(path);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

}
