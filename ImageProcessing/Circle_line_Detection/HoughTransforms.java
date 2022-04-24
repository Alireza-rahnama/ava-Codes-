import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Vector;
import java.util.Collections;
import java.util.List;
import javax.imageio.*;

// Main class
public class HoughTransforms extends Frame implements ActionListener {
	static BufferedImage input;
	private BufferedImage houghImage;
	private BufferedImage resultImage;
	int width, height, diagonal;
	ImageCanvas source, target;
	static TextField texRad;
	static TextField texThres;
	private static int[] pixels;

	public static void main(String[] args) throws IOException {

		// HoughTransforms circleHoughs=new HoughTransforms(args.length == 1 ? args[0] : "HoughCircles3.png");
		HoughTransforms lineHoughs= new HoughTransforms(args.length == 1 ? args[0] : "rectangle.png");

       
	}
	// Constructor
	public HoughTransforms(String name) {
		super("Hough Transforms");
		//load image
		File originalFile= new File(name);
		try {
			input = ImageIO.read(originalFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}


		width = input.getWidth();
		height = input.getHeight();
		diagonal = (int) Math.sqrt(width * width + height * height);




		// get the pixels rgb in a 2d array
		short currentValue = 0;
		int red, green, blue;
		// pixels = new int[width][height];
		pixels = input.getRGB(0, 0, input.getWidth(), input.getHeight(), null, 0, input.getWidth());
		int count=0;
		for (int i = 0; i < pixels.length; i++) {
			red = (pixels[i] >> 16) & 0x000000FF;
			green = (pixels[i] >> 8) & 0x000000FF;
			blue = pixels[i] & 0x000000FF;
			currentValue = (short) ((red + green + blue) / 3); // Current value gives the average //Disregard the alpha
			pixels[i] = currentValue;
			count++;

		}
		//System.out.println(count);



		
		// prepare the panel for two images.
		Panel main = new Panel();
		source = new ImageCanvas(input);
		target = new ImageCanvas(input);
		main.setLayout(new GridLayout(1, 2, 10, 10));
		main.add(source);
		main.add(target);
		// prepare the panel for buttons.
		Panel controls = new Panel();
		Button button = new Button("Line Transform");
		button.addActionListener(this);
		controls.add(button);
		controls.add(new Label("Radius:"));
		texRad = new TextField("10", 3);
		controls.add(texRad);
		button = new Button("Circle Transform");
		button.addActionListener(this);
		controls.add(button);
		controls.add(new Label("Threshold:"));
		texThres = new TextField("25", 3);
		controls.add(texThres);
		button = new Button("Search");
		button.addActionListener(this);
		controls.add(button);
		// add two panels
		add("Center", main);
		add("South", controls);
		addWindowListener(new ExitListener());
		setSize(diagonal * 2 + 100, Math.max(height, 360) + 100);
		setVisible(true);
	}

	class ExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static int[] getPixels(){
		return pixels;
	}

	public static BufferedImage getInputImage(){
		return input;

	}
	public static int getTextThreshold(){
		return  Integer.parseInt(texThres.getText());
	}

	public static int getTextRadius(){
		return  Integer.parseInt(texRad.getText());
	}

	//converts given file into a grayscale image
    private static BufferedImage toGrayScale(File f) throws Exception{
        BufferedImage img = ImageIO.read(f);
        BufferedImage grey = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_BYTE_GRAY);
        grey.getGraphics().drawImage(img, 0 , 0, null);

        return grey;
    }

	// Action listener
	public void actionPerformed(ActionEvent e) {
		// perform one of the Hough transforms if the button is clicked.
		int threshol= Integer.parseInt(texThres.getText());
		if (((Button) e.getSource()).getLabel().equals("Line Transform")) {
			//int[][] g = new int[360][doubleHeight];
			// insert your implementation for straight-line here.

			HoughTransform HoughT= new HoughTransform(width,height);
			HoughT.addPoints(input);
			
			Vector<HoughLine> lines = HoughT.getLines(threshol);
			for (int j = 0; j < lines.size(); j++) { 
				//System.out.println("helooooooooooooooo");
				HoughLine line = lines.elementAt(j); 
				line.draw(input, Color.RED.getRGB()); 
				
			} 

			BufferedImage tempImg= HoughT.getHoughArrayImage();
			int[][] g= HoughT.getHoughArray();
			DisplayTransform(diagonal, 360, g);
			// target.resetImage(tempImg);
			// target.repaint();

		} 
		else if (((Button) e.getSource()).getLabel().equals("Circle Transform")) {
			
		 	int radius = Integer.parseInt(texRad.getText());
			CircleHough1 cH1= new CircleHough1();
			cH1.init(getPixels(), width,height, radius);
			cH1.setCircles(3);
			
			cH1.process();
			//int[] accs=cH1.process();
			int[] accs= cH1.getAcc();
			int[][] array2ds =monoToBidi(accs,height,width);
			// System.out.println("size of 2d array: "+array2ds.length);
			//for(int i=0; i<255; i++)for(int j=0;j<255;j++)System.out.println(array2ds[i][j]);
			DisplayTransform(width, height, array2ds);
			BufferedImage tempImg = input;
			tempImg.setRGB(0, 0, input.getWidth(), input.getHeight(), accs, 0, input.getWidth());
			target.resetImage(tempImg);



			try {
				// writing the image in my system is a alengthy process so i just commented this part out but the results of the images 
				//with the default threshold and radius is generated it can be tested by uncommenting the line below out
				// for other radius and threshold, this will creates an instance of circle =detection and writes a new image with the
				//circels detected with a given radius and thrshold and the number of circles as parameters(please be patient if trying to uncomment and run below)


				// threshold and radius are given by user
				//CircleDetection circleD= new CircleDetection(3,threshol,radius,0);

				BufferedImage mydetection = ImageIO.read(new File("result//detectedCircles.png"));
				BufferedImage houghspaceImg= ImageIO.read(new File("result//houghSpace.png"));
				
				// target.resetImage(houghspaceImg);
				source.resetImage(mydetection);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			

	
		 }
	}

	public int[][] monoToBidi(final int[] array, final int rows, final int cols) {
		if (array.length != (rows * cols)){
			throw new IllegalArgumentException("Invalid array length");
		}

		int[][] bidi = new int[rows][cols];
		for (int i = 0; i < rows; i++)
			System.arraycopy(array, (i * cols), bidi[i], 0, cols);

		return bidi;
	}

	// display the spectrum of the transform.
	public void DisplayTransform(int wid, int hgt, int[][] g) {
		target.resetBuffer(wid, hgt);
		for (int y = 0, i = 0; y < hgt; y++)
			for (int x = 0; x < wid; x++, i++) {
				  if(g[y][x]>0){
					int value = g[y][x] > 255 ? 255 : g[y][x];
					target.image.setRGB(x, y, new Color(value, value, value).getRGB());
				  }

			}
		target.repaint();
	}


}
