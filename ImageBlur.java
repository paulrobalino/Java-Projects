// Image_Blur.java

import java.util.Random;
import java.util.Scanner;
import java.awt.Color;

public class ImageBlur {
	
	private static Picture picture;
	private static Random random;
	private static int iters; // number of iterations all pixels get blurred
	private static Scanner scanner;
	private static PixelMatrix pixels; // MAIN OBJECT
	private String filename;
	
	public ImageBlur(String filename) {
		initialize(filename);
		this.filename = filename;
	}
	
	public static void initialize(String filename) { // this is where assign values
		picture = new Picture(filename);       		// creates the picture
		random = new Random();                      // initializes the random function I guess
		scanner = new Scanner(System.in);           // scanner to enter values
		pixels = new PixelMatrix(picture);			// creates the main object
		System.out.println("Initialization Complete for " + filename);
	}
	
	public static void blurIterator() {
		int x = 0;
		int y = 0;
		int i = 0;
		int oldProg = 0;
		for (int j = 0; j < iters; j++) {
			i = 0;
			System.out.println();
			System.out.print("\rBlur Frame " + (j+1));
			while (i < (pixels.getH() * pixels.getW())) {
				y = random.nextInt(pixels.getH());
				x = random.nextInt(pixels.getW());
				if (pixels.getSel(x, y) == false) {
					blur(x, y);
					pixels.remove();
					i++;
					int prog = (int)((float)i/((float)(pixels.getH() * pixels.getW())) * 100);
					if (oldProg != prog) {
						// \r brings the cursor to the beginning of the line
						System.out.print("\rProgress = " + prog + "%");
						// Flush the output to make sure it gets printed immediately
						System.out.flush();
					}
					oldProg = prog;
				}
			}
			reset();
		}
	}
	
	public static void blur(int x, int y) {
		int[] newSet = processNeighboringPixels(x, y);
		pixels.selSwt(x, y);
		Color newColor = pixels.setColors(x, y, newSet);
		picture.set(x, y, newColor);
		picture.show();
	}
	
	public static void reset() {
		for(int i = 0; i < pixels.getH(); i++) {
			for(int j = 0; j < pixels.getW(); j++) {
				pixels.selSwt(j, i);
			}
		}
	}
	
	public static int[] processNeighboringPixels(int x, int y) {
        // Define the range of neighboring pixels
        int startX = Math.max(0, x - 1);
        int startY = Math.max(0, y - 1);
        int endX = Math.min(pixels.getW() - 1, x + 1);
        int endY = Math.min(pixels.getH() - 1, y + 1);
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int div = 0;
        
        // Iterate through neighboring pixels
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                // actual pixel processing space
                redSum += pixels.getRed(i, j);
                greenSum += pixels.getGreen(i, j);
                blueSum += pixels.getBlue(i, j);
            	div++;
            }
        }
        int[] avgs = {Math.round(redSum/div), Math.round(greenSum/div), Math.round(blueSum/div)};
        return avgs; 
    }

	public static void main(String[] args) {
		initialize("forest.jpg");
		System.out.println("Enter How Many Total Blur Iterations");
		iters = scanner.nextInt();
		scanner.close();
		blurIterator();
		//picture.show();
		System.out.println();
		System.out.println("\rBlurring Complete");
	}

}
