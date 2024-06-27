// purpose: creates a 2-dimensional array object of color nodes
import java.awt.Color;
import java.util.LinkedList;

public class PixelMatrix {
	
	private int width;
	private int height;
	private LinkedList<Pixel> list;
	private Pixel[][] RGBvalues; // a 2D array of ColorValues objects. every cell of this array contains 1 ColorValues object, 
									   // which represents one pixel
	
	public int getH() {
		return this.height;
	}
	public int getW() {
		return this.width;
	}
	public Color setColors(int x, int y, int[] newSet) { // this doesn't need to be here and should not assign new value to the pixelmatrix
		this.RGBvalues[y][x].setRed((int)newSet[0]);
		this.RGBvalues[y][x].setGreen((int)newSet[1]);
		this.RGBvalues[y][x].setBlue((int)newSet[2]);
		Color color = new Color(RGBvalues[y][x].getRed(), RGBvalues[y][x].getGreen(), RGBvalues[y][x].getBlue());
		return color;
	}
	public int getRed(int x, int y) {
		return this.RGBvalues[y][x].getRed();
	}
	public int getGreen(int x, int y) {
		return this.RGBvalues[y][x].getGreen();
	}
	public int getBlue(int x, int y) {
		return this.RGBvalues[y][x].getBlue();
	}
	public boolean getSel(int x, int y) {
		return this.RGBvalues[y][x].getSel();
	}
	public void selSwt(int x, int y) {
		this.RGBvalues[y][x].selSwt();
	}
	public void remove(Pixel clrval) {
		list.remove(clrval);
	}
	
	public PixelMatrix(Picture picture) {                   	// constructor
		
		this.width = picture.width();                           // sets width of object matrix
		this.height = picture.height();                         // sets height of object matrix
		this.RGBvalues = new Pixel[height][width];
		this.list = new LinkedList<>();

		for(int i = 0; i < height; i++) {                  // iterates through ROWS (y)
			for(int j = 0; j < width; j++) {               // iterates through COLUMN elements in current row (x)
				this.RGBvalues[i][j] = new Pixel(picture.getColor(j, i).getRed(),
						picture.getColor(j, i).getGreen(), picture.getColor(j, i).getBlue(), j, i); // adds RGB values and coordinates to matrix
				list.add(RGBvalues[i][j]); // add pixel to list
			}
		}
	}
	
}
