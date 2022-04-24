// Skeletal program for the "Image Threshold" assignment
// Written by:  Minglun Gong
/**
 * ImageThreshold is a class  applyig different thresholding algorithms to an image.
 * author @AlirezaRahnama
 */


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;
import java.lang.Math;


// Main class
public class ImageThreshold extends Frame implements ActionListener {
	BufferedImage input;
	BufferedImage tempImg;
	int width, height;
	TextField texThres, texOffset;
	ImageCanvas source, target;
	PlotCanvas2 plot;
	int[] m_histogramArray = new int[256]; // What drives our histogram
	int m_maximumPixels;
	int[] pixels;
	int[] tempHist = new int[256];
	int x;
	int[] pixelWithIntegerRgb;
	int red, green, blue;
	int cccs;
	int thresholdDifferential;
	int tempThreshold;
	int tempThreshold2;

	// Constructor
	public ImageThreshold(String name) {
		super("Image Histogram");
		// load image
		try {
			input = ImageIO.read(new File(name));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		width = input.getWidth();
		height = input.getHeight();

		// prepare the panel for image canvas.
		Panel main = new Panel();
		source = new ImageCanvas(input);
		plot = new PlotCanvas2(256, 200);


		displayHistogram();

		target = new ImageCanvas(width, height);
		// target.copyImage(input);
		target.resetImage(input);
		main.setLayout(new GridLayout(1, 3, 10, 10));
		main.add(source);
		main.add(plot);
		main.add(target);
		// prepare the panel for buttons.
		Panel controls = new Panel();
		controls.add(new Label("Threshold:"));
		texThres = new TextField("128", 2);
		controls.add(texThres);
		Button button = new Button("Manual Selection");
		button.addActionListener(this);
		controls.add(button);
		button = new Button("Automatic Selection");
		button.addActionListener(this);
		controls.add(button);
		button = new Button("Otsu's Method");
		button.addActionListener(this);
		controls.add(button);
		controls.add(new Label("Offset:"));
		texOffset = new TextField("10", 2);
		controls.add(texOffset);
		button = new Button("Adaptive Mean-C");
		button.addActionListener(this);
		controls.add(button);
		// add two panels
		add("Center", main);
		add("South", controls);
		addWindowListener(new ExitListener());
		setSize(width * 2 + 400, height + 100);
		setVisible(true);
	}

	class ExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}


	//histogram
	public void displayHistogram(){
		m_maximumPixels = 0;
		for (short i = 0; i < 256; i++) {
			m_histogramArray[i] = 0;
		}
		int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
		short currentValue = 0;
		int red, green, blue;
		for (int i = 0; i < pixels.length; i++) {
			red = (pixels[i] >> 16) & 0x000000FF;
			green = (pixels[i] >> 8) & 0x000000FF;
			blue = pixels[i] & 0x000000FF;
			currentValue = (short) ((red + green + blue) / 3); // Current value gives the average //Disregard the alpha
			assert (currentValue >= 0 && currentValue <= 255); // Something is awfully wrong if this goes off...
			m_histogramArray[currentValue] += 1; // Increment the specific value of the array
		}

		m_maximumPixels = 0; // We need to have their number in order to scale the histogram properly
		for (int i = 0; i < m_histogramArray.length; i++) { // Loop through the elements
			if (m_histogramArray[i] > m_maximumPixels) { // And find the biggest value
				m_maximumPixels = m_histogramArray[i];

			}
		}

		//  scale the intensities to fit in the histogram plot(200 is the maximum value of y-axis)

		double factorHeight =(200.0/m_maximumPixels);
		// canvas2
		VerticalBar[] bars = new VerticalBar[256];
		// color, location, length of line
		for (int i = 0; i < m_histogramArray.length; i++) {
			int ff = (int) (m_histogramArray[i] * factorHeight);
			VerticalBar vb = new VerticalBar(Color.BLACK, i, ff);
			bars[i] = vb;
			plot.addObject(vb);
		}

	}

	//this function returns the pixels intensities after aplying threshold value and
	//changing the itensities below threshold to 0 and above the threshold value to 255
	public int[] getAdjustedPixels(int thresh){
		int threshold=thresh;
		int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
		int[] targetPixels = new int[pixels.length];
		
		for (int i = 0; i < pixels.length; i++) {
			red = (pixels[i] >> 16) & 0x000000FF;
			green = (pixels[i] >> 8) & 0x000000FF;
			blue = pixels[i] & 0x000000FF;
			short currentValue = (short) ((red + green + blue) / 3); // Current value gives the average //Disregard alpha(RGB only)
			assert (currentValue >= 0 && currentValue <= 255); // Something is awfully wrong if this goes off...

			
			if (currentValue <= threshold) {
				// int a=0;
				int rgb = 0 << 16 | 0 << 8 | 0;
				targetPixels[i] = rgb;
			}
			if (currentValue > threshold) {
				// int b=1;
				int rgb = 255 << 16 | 255 << 8 | 255;
				targetPixels[i] = rgb;
			}

		}
		return targetPixels;

	}


	/**
	 * Calculate appropriate global threshold. Steps:
	 * 1. Choose initial estimate for global threshold T.
	 * 2. Segment image using T into 2 classes: pixels with intensity
	 * values > T and pixels with intensity values <= T.
	 * 3. Compute average (mean) intensity values m_1 and m_2 for pixels
	 * in each of the two classes.
	 * 4. Compute a new threshold value: T = 1/2 (m_1 + m_2)
	 * 5. Repeat steps 2 through 4 until change in T is less than epsilon.
	 * 
	 * 
	 */


	// Action listener for button click events
	public void actionPerformed(ActionEvent e) {
		// example -- compute the average color for the image

		if (((Button) e.getSource()).getLabel().equals("Adaptive Mean-C")) {
			AdapThresh threshold = new AdapThresh();
			int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
			int[] targetPixels;
			// *@return a thresholded pixel array of the input image array
			targetPixels= threshold.mean_thresh(pixels, width, height, 7, Integer.valueOf(texOffset.getText()));
			BufferedImage tempImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tempImg.setRGB(0, 0, input.getWidth(), input.getHeight(), targetPixels, 0, input.getWidth());
			target.resetImage(tempImg);
			plot.clearObjects();
			displayHistogram();
			plot.addObject(new VerticalBar(Color.red, threshold.getMean(), 250));
		}

		if (((Button) e.getSource()).getLabel().equals("Manual Selection")) {
			int threshold;
			try {
				threshold = Integer.parseInt(texThres.getText());
			} catch (Exception ex) {
				texThres.setText("128");
				threshold = 128;
			}
			plot.clearObjects();
			plot.addObject(new VerticalBar(Color.BLACK, threshold, 100));
			int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
			int[] targetPixels = new int[pixels.length];
			

			targetPixels= getAdjustedPixels(threshold);
			BufferedImage tempImg1 = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
			tempImg1.setRGB(0, 0, input.getWidth(), input.getHeight(), targetPixels, 0, input.getWidth());
			target.resetImage(tempImg1);
			displayHistogram();
			plot.addObject(new VerticalBar(Color.red, threshold, 250));
		
		}

		if (((Button) e.getSource()).getLabel().equals("Automatic Selection")) {
			int tempThreshold;
			
			int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
			int[] Group1Pixels = new int[pixels.length];
			int[] Group2Pixels = new int[pixels.length];
			
			thresholdDifferential = 5;
			tempThreshold = 128;
			tempThreshold2=0;
			while (thresholdDifferential != 0) {
				int sumG1 = 0;
				int sumG2 = 0;
				
				for (int i = 0; i < pixels.length; i++) {
					red = (pixels[i] >> 16) & 0x000000FF;
					green = (pixels[i] >> 8) & 0x000000FF;
					blue = pixels[i] & 0x000000FF;
					short currentValue = (short) ((red + green + blue) / 3); // Current value gives the grayvalue
																				// //Disregard the
					// alpha
					assert (currentValue >= 0 && currentValue <= 255); // Something is awfully wrong if this goes off...

					// ){cccs++;}
					if (currentValue <= tempThreshold) {
						// int a=0;
						Group1Pixels[i] = currentValue;
						sumG1 += currentValue;

					}
					if (currentValue > tempThreshold) {
						// int b=1;
						Group2Pixels[i] = currentValue;
						sumG2 += currentValue;
					}

				}
				int avgG1 = sumG1 / Group1Pixels.length;
				int avgG2 = sumG2 / Group2Pixels.length;
				tempThreshold2 = (avgG1 + avgG2) / 2;
				tempThreshold=tempThreshold2;
				thresholdDifferential = Math.abs(tempThreshold - tempThreshold2);
			}

			int[] targetPixels = new int[pixels.length];
			
			//get the adjustwed pixels after applying the threshold
			targetPixels= getAdjustedPixels(tempThreshold2);

			BufferedImage tempImg2 = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
			tempImg2.setRGB(0, 0, input.getWidth(), input.getHeight(), targetPixels, 0, input.getWidth());
			target.resetImage(tempImg2);
			plot.clearObjects();
			displayHistogram();
			plot.addObject(new VerticalBar(Color.red, tempThreshold2, 250));
		}

		
		if (((Button) e.getSource()).getLabel().equals("Otsu's Method")) {

			int[] pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
			int threshold;
			// compute histogram
			int ptr = 0;
			int[] histData;
			histData= new int[256];
			while (ptr < pixels.length) {
				int h = 0xFF & pixels[ptr];
				histData[h] ++;
				ptr ++;
			}



			// Total number of pixels
			int total = pixels.length;

			float sum = 0;
			for (int t=0 ; t<256 ; t++) sum += t * histData[t];

			float sumB = 0;
			int wB = 0;
			int wF = 0;

			float varMax = 0;
			threshold = 0;

			for (int t=0 ; t<256 ; t++) {
				wB += histData[t];               // Weight Background
				if (wB == 0) continue;

				wF = total - wB;                 // Weight Foreground
				if (wF == 0) break;

				sumB += (float) (t * histData[t]);

				float mB = sumB / wB;            // Mean Background
				float mF = (sum - sumB) / wF;    // Mean Foreground

				// Calculate Between Class Variance
				float varBetween = (float)wB * (float)wF * (mB - mF) * (mB - mF);

				// Check if new maximum found
				if (varBetween > varMax) {
					varMax = varBetween;
					threshold = t;
				}
			}

			int[] targetPixels = new int[pixels.length];

			//get the pixels after applying the threshold computed in otsu's method
			targetPixels=getAdjustedPixels(threshold);
			
			BufferedImage tempImg3 = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
			tempImg3.setRGB(0, 0, input.getWidth(), input.getHeight(), targetPixels, 0, input.getWidth());
			target.resetImage(tempImg3);
			plot.clearObjects();
			displayHistogram();
			plot.addObject(new VerticalBar(Color.red, threshold, 250));
			
		}


	}

	public static void main(String[] args) {
		new ImageThreshold(args.length == 1 ? args[0] : "fingerprint.png");

	}
}
