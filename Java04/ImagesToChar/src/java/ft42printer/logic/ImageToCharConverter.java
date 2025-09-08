

package ft42printer.logic;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class ImageToCharConverter {

	final int black = (255 << 24) | (0 << 16) | (0 << 8) | 0;
	final int white = (255 << 24) | (255 << 16) | (255 << 8) | 255;

	private char mapPixelToChar(int pixel) {
        int r = (pixel >> 16) & 0xFF;
        int g = (pixel >> 8) & 0xFF;
        int b = pixel & 0xFF;
        int gray = (r + g + b) / 3;

        final char[] chars = {' ', '.', ':', '-', '=', '+', '*', '#', '%', '@'};
        int index = gray * (chars.length - 1) / 255;
        return chars[index];
    }


	public ImageToCharConverter(String pathToImage) {

		try  {
			File file = new File(pathToImage);
    BufferedImage image = ImageIO.read(file);

    if (image == null) {
        System.out.println("Failed to load image.");
        return;
    }

			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					int pixel = image.getRGB(x, y);
					char asciiChar = mapPixelToChar(pixel);
					if (pixel == black) {
						System.out.print("@");
					} else {
						System.out.print(".");
					}
					// System.out.print(asciiChar);
				}
				System.out.println();
			}
			}
		catch (Exception e) {
			System.out.println("File not found: " + pathToImage);
			return;
		}
	}
}