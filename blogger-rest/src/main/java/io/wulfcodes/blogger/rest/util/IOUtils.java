package io.wulfcodes.blogger.rest.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class IOUtils {
    private IOUtils() {}
    public static void saveFile(String path, String fileName, String fileExtension, byte[] data) {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(String.format("%s/%s.%s", path, fileName, fileExtension))))) {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveImageFile(String fileName, String encodedImage) {
        String[] imageParts = encodedImage.split(",");

        String fileExtension = switch (imageParts[0]) {
            case "data:image/jpeg;base64"   -> "jpeg";
            case "data:image/png;base64"    -> "png";
            case "data:image/webp;base64"   -> "webp";
            default                         -> "jpg";
        };

        byte[] data = Base64.getDecoder().decode(imageParts[1]);

        saveFile("/blogger-rest/src/main/resources/files/images", fileName, fileExtension, data);
    }
}
