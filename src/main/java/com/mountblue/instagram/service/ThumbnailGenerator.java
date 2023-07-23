package com.mountblue.instagram.service;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThumbnailGenerator {
    public static byte[] generateThumbnail(byte[] videoBytes, int width, int height) throws IOException {
        // Convert the video byte array to a temporary file
        Path videoPath = Files.createTempFile("video", ".webm");
        Files.write(videoPath, videoBytes);

        // Generate the thumbnail from the temporary file
        BufferedImage bufferedImage = Thumbnails.of(videoPath.toFile())
                .outputFormat("jpg")
                .size(width, height)
                .asBufferedImage();

        // Save the thumbnail as a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] thumbnailBytes = baos.toByteArray();
        baos.close();

        // Delete the temporary file
        Files.delete(videoPath);

        return thumbnailBytes;
    }
}
