package team5.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class MavenVersion {

    public static String getVersion() {
        String path = "/version.prop";
        InputStream stream = MavenVersion.class.getResourceAsStream(path);

        if (stream == null) return "UNKNOWN";

        Properties properties = new Properties();

        try {
            properties.load(stream);
            stream.close();
            return (String) properties.get("version");
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }
}
