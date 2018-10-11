package gurinov;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class MavenVersion {

    public static String getVersion() {
        String path = "/version.prop";
        InputStream stream = MavenVersion.class.getResourceAsStream(path);
        if (stream == null) return "UNKNOWN";
        Properties props = new Properties();
        try {
            props.load(stream);
            stream.close();
            return (String)props.get("version");
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }
}
