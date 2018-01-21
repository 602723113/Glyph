package cc.deepinmc.glyph;

import cc.deepinmc.glyph.command.CommandHandler;
import cc.deepinmc.glyph.dto.*;
import cc.deepinmc.glyph.listener.EntityDamageByEntityListener;
import cc.deepinmc.glyph.listener.GUIListener;
import cc.deepinmc.glyph.manager.GlyphManager;
import cc.deepinmc.glyph.manager.PlayerAttributeManager;
import cc.deepinmc.glyph.util.ConfigurationUtils;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
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

    @SuppressWarnings("unused")
    private static final String INFORMATION = "You do not have to decompile the plugin to get the source code, this plugin has push the source code to Github, URL: https://github.com/DeepinMC/Glyph";
    @SuppressWarnings("unused")
    private static final String INFORMATION_CN = "你不必反编译本插件来获取源码, 本插件已将源码托管到Github, URL: https://github.com/DeepinMC/Glyph";

    private static Entry instance;
    @Getter
    @Setter
    private FileConfiguration languageConfig;
    @Getter
    private File glyphFolder;
    @Getter
    private GlyphManager glyphManager;
    @Getter
    private PlayerAttributeManager attributeManager;
    @Getter
    private InlayType inlayType;

    @Override
    @SuppressWarnings("all")
    public void onEnable() {
        instance = this;

        // config
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
        inlayType = InlayType.valueOf(getConfig().getString("general_option.inlay.type"));

        // register command
        Bukkit.getPluginCommand("glyph").setExecutor(new CommandHandler());
        // register listener
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);

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

    public void loadGlyphs() {
        File[] files = Validate.notNull(glyphFolder.listFiles());
        Arrays.stream(files).forEach(file -> {
            FileConfiguration fileConfiguration = ConfigurationUtils.loadYml(file);

            // var load
            int material = fileConfiguration.getInt("Glyph.material");
            int data = fileConfiguration.getInt("Glyph.data");
            String name = fileConfiguration.getString("Glyph.name").replaceAll("&", "§");
            String displayName = fileConfiguration.getString("Glyph.display_name").replaceAll("&", "§");
            String loreName = fileConfiguration.getString("Glyph.lore_name").replaceAll("&", "§");
            List<String> description = fileConfiguration.getStringList("Glyph.description");
            boolean canUseGlyphPattern = fileConfiguration.getBoolean("Glyph.can_use_glyph_pattern");
            String carveMaterial = fileConfiguration.getString("Glyph.carve_material").replaceAll("&", "§");
            int carveMaterialAmount = fileConfiguration.getInt("Glyph.carve_material_amount");
            String glyphPattern = fileConfiguration.getString("Glyph.glyph_pattern").replaceAll("&", "§");

            // load glyphAttributes
            List<GlyphAttribute> glyphAttributes = Lists.newArrayList();
            fileConfiguration.getStringList("Glyph.glyphAttributes")
                    .forEach(s -> {
                        GlyphAttribute glyphAttribute;
                        // split string
                        String[] split = s.split(":");
                        switch (split.length) {
                            case 2:
                                glyphAttribute = new GlyphAttribute(EffectType.valueOf(split[0]), Double.valueOf(split[1]));
                                glyphAttributes.add(glyphAttribute);
                            case 3:
                                glyphAttribute = new GlyphAttribute(EffectType.valueOf(split[0]), Double.valueOf(split[1]), Double.valueOf(split[2]));
                                glyphAttributes.add(glyphAttribute);
                            default:
                                break;
                        }
                    });

            // load can inlay equipments
            List<EquipmentType> equipments = fileConfiguration.getStringList("Glyph.equipments").stream()
                    .map(EquipmentType::valueOf)
                    .collect(Collectors.toList());

            // construct glyph
            Glyph glyph = new Glyph(Material.getMaterial(material), data, name, displayName, loreName, description, glyphAttributes, canUseGlyphPattern, carveMaterial, carveMaterialAmount, glyphPattern, equipments);

            // add to manager
            this.glyphManager.addGlyph(name, glyph);
            this.glyphManager.addPattern(glyphPattern, glyph);
            this.glyphManager.addMaterial(carveMaterial);
            Bukkit.getConsoleSender().sendMessage("§6[§eGlyph§6] §fLoad glyph " + name + "§f successfully");
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
