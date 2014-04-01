package test;

import gfx.SpriteSheet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToText {

	private int width;
	private int height;
	private int[] pixels;

	public static void main(String[] args) {
		
		File file = new File("src/Data/TubeInfo.txt");
		String imagePath = "src/Data/TubeInfo.txt";
		BufferedImage image = null;

		try {
			image = ImageIO.read(ImageToText.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		try (FileOutputStream fop = new FileOutputStream(file)) {
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
