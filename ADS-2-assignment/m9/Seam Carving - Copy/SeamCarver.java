import java.awt.Color;
import java.lang.Math;

public class SeamCarver {
    public static final int BORDER_ENERGY = 1000;
    public Picture picture;
    public int width;
    public int height;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
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

    /**
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double calculateenergy(int x1, int y1, int x2, int y2) {
        Color c1 = picture.get(x1, y1);
        Color c2 = picture.get(x2, y2);
        double red = c1.getRed() - c2.getRed();
        double green = c1.getGreen() - c2.getGreen();
        double blue = c1.getBlue() - c2.getBlue();
        return red * red + green * green + blue * blue;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if(x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return BORDER_ENERGY;
        }
        double eX = calculateenergy(x - 1, y, x + 1, y);
        double eY = calculateenergy(x, y - 1, x, y + 1);
        return Math.sqrt(eX + eY);
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
