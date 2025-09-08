

package ft42printer.app;

import ft42printer.logic.ImageToCharConverter;

public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main <image_path>");
			return;
		}

		new ImageToCharConverter(args[0]);

	}
}