package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToText {

	public static void main(String[] args) {
		
		File file = new File("src/Data/BackgroundInfo.txt");
		String imagePath = "/Image/Background.png";
		BufferedImage image = null;
		String content = "";
		int width = 0;
		int height = 0;
		int[] pixels;
		
		try {
			image = ImageIO.read(ImageToText.class.getResourceAsStream(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for(int i = 0; i < pixels.length; i++){
			int result = 0;
			int r = 0, g = 0 ,b = 0;
			//get rid of the alpha channel
			r = (pixels[i] >> 24) & 0xff;
			g = (pixels[i] >> 16) & 0xff;
			b = (pixels[i] >> 8) & 0xff;
			result = ((r << 16) & 0xff0000) | ((g << 8) & 0xff00) | ((b << 0) & 0xff);
			
			if(i % width == 0){
				content +="\n";
				System.out.println("processed line:"+ i / width);
			}
			content += ""+result + " ";
		}
	
	System.out.println(content);
		
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
