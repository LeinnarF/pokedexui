package org.nice;

import javax.swing.*;
import java.awt.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Pattern;

public class Utils {

    public static ImageIcon getImage(String resourcePath) {
        return new ImageIcon(getResource(resourcePath));
    }

    public static ImageIcon getImage(String resourcePath, int width, int height) {
        return resizeImage(getImage(resourcePath), width, height);
    }

    public static String escapeRegex(String s) {
        String regexChars = "([\\\\.^$|?*+()\\[{])";

        // Escape these characters in the input string
        return s.replaceAll(regexChars, "\\\\$1");
    }

    public static ImageIcon resizeImage(ImageIcon orig, int width, int height) {
        var scaled = orig.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public static String getResource(String resourcePath) {
        try {
            var path = Objects.requireNonNull(
                    Utils.class.getResource(resourcePath)
            );
            return URLDecoder.decode(path.getPath(), StandardCharsets.UTF_8);
        }catch(NullPointerException e) {
            throw  new RuntimeException("The file with path: " + resourcePath + " does not exists.");
        }
    }
}
