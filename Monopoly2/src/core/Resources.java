package core;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Contains static methods for dealing with resources.
 */
public class Resources {

    private static Map<String, Image> imageCache;
    private static Map<String, Font> fontCache;

    static {
        imageCache = new HashMap<>();
        fontCache = new HashMap<>();
    }

    /**
     * Returns the corresponding resource path for a given regular path. If you
     * want to access a file called "myFile.txt" inside the resources directory,
     * you can pass "/myFile.txt" into this method and get the actual path to
     * the resource.
     * @param regPath path to the file relative to the resources directory
     * @return path to the actual resource
     */
    public static String path(String regPath) throws IOException {
        URL url = Resources.class.getResource(regPath);
        if(url == null) {
            throw new IOException("could not find resource " + regPath);
        }
        String osAppropriatePath = System.getProperty( "os.name" ).contains( "indow" ) ? url.getPath().substring(1) : url.getPath();

        return osAppropriatePath;
    }

    public static InputStream stream(String regPath) throws IOException {
        if (Resources.class.getResourceAsStream(regPath) != null)
            return Resources.class.getResourceAsStream(regPath);
        else {
            File file = new File(regPath);
            return new FileInputStream(file);
        }
    }

    /**
     * Loads and caches an image from the given file path. The path does not
     * need to be passed through core.Resources.path(). If the image has already been
     * loaded, it returns the cached image.
     * @param regPath The path to the image in the resources folder
     * @return image
     * @throws IOException should probably bubble up to the top
     */
    public static Image getImage(String regPath) throws IOException {
        if(imageCache.containsKey(regPath)) {
            return imageCache.get(regPath);
        }

        try {
            imageCache.put(regPath, ImageIO.read(stream(regPath)));
            return imageCache.get(regPath);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Loads and caches a font file from the given file path. The path does not
     * need to be passed through core.Resources.path(). If the font has already been
     * loaded, it returns the cached font.
     * @param regPath The path to the font in the resources folder
     * @return font
     * @throws IOException, FontFormatException should probably bubble up to the top
     */
    public static Font getFont(String regPath) throws IOException, FontFormatException {
        if(fontCache.containsKey(regPath)) {
            return fontCache.get(regPath);
        }

        fontCache.put(regPath, Font.createFont(Font.TRUETYPE_FONT, stream(regPath)));
        return fontCache.get(regPath);
    }

}
