package cc.deepinmc.glyph.util;

import org.bukkit.Bukkit;

/**
 * Easy to use NMS
 *
 * @author Zoyn
 * @since 2017/4/26
 */
public final class NMSUtils {

    private static String version;

    // Prevent accidental construction
    private NMSUtils() {
    }

    static {
        // org.bukkit.craftbukkit.vX_XX_RX;
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    }

    /**
     * 取服务器版本 如 v1_10_R1
     * <br />
     * get the server version, returns a string similar to v1_10_R1
     *
     * @return server version
     */
    public static String getVersion() {
        return version;
    }

    /**
     * 取 org.bukkit.craftbukkit 包下的类对象
     * <br />
     * get org.bukkit.craftbukkit's class object
     *
     * @param className a class's name in the package obc
     * @return {@link Class}
     */
    public static Class<?> getOBCClass(String className) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + NMSUtils.getVersion() + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取对应的 NMS 下的类
     * <br />
     * get net.minecraft.server's class object
     *
     * @param className a class's name in the package nms
     * @return {@link Class}
     */
    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }
}
