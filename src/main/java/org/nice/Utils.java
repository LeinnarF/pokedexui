package org.nice;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Utils {
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
