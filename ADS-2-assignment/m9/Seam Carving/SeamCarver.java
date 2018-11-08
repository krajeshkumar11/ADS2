import java.awt.Color;
public class SeamCarver {
	public static final int BORDER_ENERGY = 1000;
    public int width;
    public int height;
    public final Picture picture;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		this.width = picture.width();
        this.height = picture.height();
        this.picture = picture;
	}
	// current picture
	public Picture picture() {
		return null;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
            return BORDER_ENERGY;
        }

        int deltaX = gradientSquare(x + 1, y, x - 1, y);
        int deltaY = gradientSquare(x, y + 1, x, y - 1);
        return Math.sqrt(deltaX + deltaY);
	}

	public int gradientSquare(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);
        int red = color1.getRed() - color2.getRed();
        int green = color1.getGreen() - color2.getGreen();
        int blue = color1.getBlue() - color2.getBlue();
        return red * red + green * green + blue * blue;
    }

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
