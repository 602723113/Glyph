package cc.deepinmc.glyph.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Easy to use yml
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public final class ConfigurationUtils {

    private ConfigurationUtils() {
    }

    public static FileConfiguration loadYml(String path) {
        File file = new File(path);
        return loadYml(file);
    }


    public static void saveYml(FileConfiguration fileConfiguration, File file) {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    public static FileConfiguration loadYml(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("错误:" + e.toString());
            }
        }
        FileConfiguration YML = YamlConfiguration.loadConfiguration(file);
        DumperOptions yamlOptions;
        try {
            Field f = YamlConfiguration.class.getDeclaredField("yamlOptions");
            f.setAccessible(true);
            yamlOptions = new DumperOptions() {
                @Override
                public void setAllowUnicode(boolean allowUnicode) {
                    super.setAllowUnicode(false);
                }
                @Override
                public void setLineBreak(LineBreak lineBreak) {
                    super.setLineBreak(LineBreak.getPlatformLineBreak());
                }
            };
            yamlOptions.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
            f.set(YML, yamlOptions);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
        return YML;
    }
}
