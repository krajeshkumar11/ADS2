import java.awt.Color;
import java.util.Arrays;
import java.lang.Math;
public class SeamCarver {
	public int BORDER_ENERGY = 1000;
    public int width;
    public int height;
    public Picture picture;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if(picture == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.width = picture.width();
        this.height = picture.height();
        this.picture = picture;
	}
	// current picture
	public Picture picture() {
		return picture;
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
        double[][] sum = new double[2][height];
        int[][] parent = new int[width][height];
        for (int y = 0; y < height; ++y) {
            sum[0][y] = BORDER_ENERGY;
            parent[0][y] = y;
        }

        for (int x = 1; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                double temp = sum[(x - 1) % 2][y];
                parent[x][y] = y;
                if (y > 0 && sum[(x - 1) % 2][y - 1] < temp) {
                    temp = sum[(x - 1) % 2][y - 1];
                    parent[x][y] = y - 1;
                }

                if (y < height - 1 && sum[(x - 1) % 2][y + 1] < temp) {
                    temp = sum[(x - 1) % 2][y + 1];
                    parent[x][y] = y + 1;
                }
                sum[x % 2][y] = energy(x, y) + temp;
            }
        }

        int index = 0;
        for (int y = 1; y < height; ++y) {
            if (sum[(width - 1) % 2][y] < sum[(width - 1) % 2][index]) {
                index = y;
            }
        }
        int[] seam = new int[width];
        seam[width - 1] = index;
        for (int x = width - 2; x >= 0; --x) {
            seam[x] = parent[x + 1][index];
            index = parent[x + 1][index];
        }
        return seam;
    }

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		double[][] sum = new double[width][2];
        int[][] parent = new int[width][height];
        for (int x = 0; x < width; ++x) {
            sum[x][0] = BORDER_ENERGY;
            parent[x][0] = x;
        }
        // System.out.println();
        // for (int i = 0; i < sum.length; i++) {
        //     System.out.println(Arrays.toString(sum[i]));
        // }
        for (int y = 1; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
            	// System.out.println("1: " + Arrays.toString(sum[x]));
                double temp = sum[x][(y - 1) % 2];
                // System.out.println(x + " " + y + " " + (y - 1) % 2 + " " + temp);
                parent[x][y] = x;
                if (x > 0 && sum[x - 1][(y - 1) % 2] < temp) {
                    temp = sum[x - 1][(y - 1) % 2];
                    parent[x][y] = x - 1;
                }

                if (x < width - 1 && sum[x + 1][(y - 1) % 2] < temp) {
                    temp = sum[x + 1][(y - 1) % 2];
                    parent[x][y] = x + 1;
                }
                sum[x][y % 2] = energy(x, y) + temp;
                // System.out.print(Math.round(sum[x][y % 2] * 100.0) / 100.0 + " ");
            }
            // System.out.println();
        }
        // for (int i = 0; i < parent.length; i++) {
        //     System.out.println(Arrays.toString(parent[i]));
        // }
        // System.out.println();
        // for (int i = 0; i < sum.length; i++) {
        //     System.out.println(Arrays.toString(sum[i]));
        // }
        int index = 0;
        for (int x = 1; x < width; ++x) {
            if (sum[x][(height - 1) % 2] < sum[index][(height - 1) % 2]) {
                index = x;
            }
        }
        int[] seam = new int[height];
        seam[height - 1] = index;
        for (int y = height - 2; y >= 0; --y) {
        	// System.out.println(index + " " + (y + 1));
            seam[y] = parent[index][y + 1];
            index = parent[index][y + 1];
        }
        return seam;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
