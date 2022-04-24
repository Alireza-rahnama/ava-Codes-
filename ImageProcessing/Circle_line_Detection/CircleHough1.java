import java.awt.image.BufferedImage; 
import java.awt.*; 
import java.util.Vector;

public class CircleHough1 {

	private static int[] input;
	private static int[] output;
	private static int width;
	private static int height;
	private static int[] acc;
	private static int accSize = 4;
	private static int[] results;
	private static int r; // The radius size of the circle

	public CircleHough1() {
		System.out.println("Hough Circle Detection...");
	}

	public void init(int[] inputIn, int widthIn, int heightIn, int radius) {
		r = radius;
		width = widthIn;
		height = heightIn;
		input = new int[width * height];
		output = new int[width * height];
		input = inputIn;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				output[x + (width * y)] = 0xff000000; //Default image background color is black
			}
		}
	}

	public void setCircles(int circles) {
		accSize = circles; // Number of detections
	}
	
	/**
	 * Hoff transformation processing - detecting the number of circles whose radius size matches
	 * 1. Converting image pixels from 2D space coordinates to polar space
	 * 2. Normalize the intensity of each point in polar space to be between 0 and 255
	 * 3. Find pixel points in 2D space based on the R value of the polar coordinates equal to the input parameter (radius of the circle)
	 * 4. Give the result color (red) to the found spatial pixel points
	 * 5. Returns the result 2D spatial pixel collection
	 * @return int []
	 */
	public int[] process() {

		// For a polar transformation of a circle, we need a 360-degree spatial gradient overlay
		acc = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				acc[y * width + x] = 0;
			}
		}
		int x0, y0;
		double t;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				if ((input[y * width + x] & 0xff) == 255) {

					for (int theta = 0; theta < 360; theta++) {
						t = (theta * 3.14159265) / 180; // Angle value 0 ~ 2*PI
						x0 = (int) Math.round(x - r * Math.cos(t));
						y0 = (int) Math.round(y - r * Math.sin(t));
						if (x0 < width && x0 > 0 && y0 < height && y0 > 0) {
							acc[x0 + (y0 * width)] += 1;
						}
					}
				}
			}
		}

		// now normalise to 255 and put in format for a pixel array
		int max = 0;

		// Find max acc value
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				if (acc[x + (y * width)] > max) {
					max = acc[x + (y * width)];
				}
			}
		}

		// Normalizing gray values in polar space based on maximum
		int value;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				value = (int) (((double) acc[x + (y * width)] / (double) max) * 255.0);
				acc[x + (y * width)] = 0xff000000 | (value << 16 | value << 8 | value);
			}
		}
		
		// Draw discovered circles
		findMaxima();
		System.out.println("please uncomment out the following line in main mathod located in HoughTransforms.java:\n"+"//HoughTransforms lineHoughs= new HoughTransforms(args.length == 1 ....");
		return output;
	}

	public static int[] getOutPut(){
		return output;
	}

	private static int[] findMaxima() {
		results = new int[accSize * 3];
		output = new int[width * height];
		
		// Get the maximum number of first accSize values
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int value = (acc[x + (y * width)] & 0xff);

				// if its higher than lowest value add it and then sort
				if (value > results[(accSize - 1) * 3]) {

					// add to end of array
					results[(accSize - 1) * 3] = value; //pixel value
					results[(accSize - 1) * 3 + 1] = x; // Coordinate X
					results[(accSize - 1) * 3 + 2] = y; // Coordinate Y

					// shift up until its in right place
					int i = (accSize - 2) * 3;
					while ((i >= 0) && (results[i + 3] > results[i])) {
						for (int j = 0; j < 3; j++) {
							int temp = results[i + j];
							results[i + j] = results[i + 3 + j];
							results[i + 3 + j] = temp;
						}
						i = i - 3;
						if (i < 0)
							break;
					}
				}
			}
		}

		// Draw a circle on the original image based on the found radius R, the center point pixel coordinates p(x, y)
		System.out.println("top " + accSize + " matches:");
		for (int i = accSize - 1; i >= 0; i--) {
			drawCircle(results[i * 3], results[i * 3 + 1], results[i * 3 + 2]);
		}
		return output;
	}

    public int getHighestValue() { 
        int max = 0; 
        for (int t = 0; t < height*width; t++) { 
           
            if (input[t] > max) { 
                max = input[t]; 
            } 
            
        } 
        return max; 
    } 



	private static void setPixel(int value, int xPos, int yPos) {
		 output[(yPos * width) + xPos] = 0xff000000 | (value << 16 | value << 8 | value);
		//output[(yPos * width) + xPos] = 0xffff0000;
	}

	// draw circle at x y
	private static void drawCircle(int pix, int xCenter, int yCenter) {
		pix = 250; // Color value, default to white

		int x, y, r2;
		int radius = r;
		r2 = r * r;
		
		// Draw the top, bottom, left and right four points of a circle
		setPixel(pix, xCenter, yCenter + radius);
		setPixel(pix, xCenter, yCenter - radius);
		setPixel(pix, xCenter + radius, yCenter);
		setPixel(pix, xCenter - radius, yCenter);

		y = radius;
		x = 1;
		y = (int) (Math.sqrt(r2 - 1) + 0.5);
		
		// The edge filling algorithm, in fact, can directly cycle through all the pixels and calculate the distance to the center point to do so
		// This method was written by someone else and found to be superb, super good!
		while (x < y) {
			setPixel(pix, xCenter + x, yCenter + y);
			setPixel(pix, xCenter + x, yCenter - y);
			setPixel(pix, xCenter - x, yCenter + y);
			setPixel(pix, xCenter - x, yCenter - y);
			setPixel(pix, xCenter + y, yCenter + x);
			setPixel(pix, xCenter + y, yCenter - x);
			setPixel(pix, xCenter - y, yCenter + x);
			setPixel(pix, xCenter - y, yCenter - x);
			x += 1;
			y = (int) (Math.sqrt(r2 - x * x) + 0.5);
		}
		if (x == y) {
			setPixel(pix, xCenter + x, yCenter + y);
			setPixel(pix, xCenter + x, yCenter - y);
			setPixel(pix, xCenter - x, yCenter + y);
			setPixel(pix, xCenter - x, yCenter - y);
		}
	}

	public int[] getAcc() {
		return acc;
	}

}
