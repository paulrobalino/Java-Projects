
public class Pixel {
	
	private int red;
	private int green;
	private int blue;
	private boolean beenSel; // has the pixel already been modulated?
	private int x;
	private int y;
	
	// constructor
	public Pixel(int r, int g, int b, int x, int y) {
		this.red = r;
		this.green = g;
		this.blue = b;
		this.beenSel = false;
		this.x = x;
		this.y = y;
	}
	
	// accessor methods
	public int getRed() {
		return this.red;
	}
	public int getGreen() {
		return this.green;
	}
	public int getBlue() {
		return this.blue;
	}
	public boolean getSel() {
		return this.beenSel;
	}
	public void selSwt() { // switches if the pixel has been selected
		this.beenSel = !this.beenSel;
	}
	public void setRed(int r) {
		this.red = r;
	}
	public void setGreen(int g) {
		this.green = g;
	}
	public void setBlue(int b) {
		this.blue = b;
	}
	public void setSel(boolean bs) {
		this.beenSel = bs;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}

}
