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

    // energy of pixel at column row and row y
    public double energy(int row, int col) {
        if(row == 0 || col == 0 || row == width - 1 || col == height - 1) {
            return BORDER_ENERGY;
        }
        double eX = calculateenergy(row - 1, col, row + 1, col);
        double eY = calculateenergy(row, col - 1, row, col + 1);
        return Math.sqrt(eX + eY);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] sum = new double[width][height];
        int[][] parent = new int[width][height];
        for (int col = 0; col < height; col++) {
            sum[0][col] = BORDER_ENERGY;
            parent[0][col] = col;
        }
        for (int row = 1; row < width; row++) {
            for (int col = 0; col < height; col++) {
                double temp = sum[row - 1][col];
                parent[row][col] = col;
                if (col > 0 && sum[row - 1][col - 1] < temp) {
                    temp = sum[row - 1][col -1];
                    parent[row][col] = col - 1;
                }
                if (col < height - 1 && sum[row - 1][col + 1] < temp) {
                    temp = sum[row - 1][col + 1];
                    parent[row][col] = col + 1;
                }
                sum[row][col] = energy(row, col) + temp;
            }
        }
        int index = 0;
        for (int col = 1; col < height; col++) {
            if (sum[width - 1][col] < sum[width - 1][index]) {
                index = col;
            }
        }
        int[] seam = new int[width];
        seam[width - 1] = index;
        for (int row = width - 2; row >= 0 ; row--) {
            seam[row] = parent[row + 1][index];
            index = parent[row + 1][index];;
        }
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] sum = new double[width][height];
        int[][] path = new int[width][height];
        for (int row = 0; row < width; row++) {
            sum[row][0] = BORDER_ENERGY;
            path[row][0] = row;
            // System.out.print(sum[row][0] + " ");
        }
        // System.out.println();
        for (int col = 1; col < height; col++) {
            for (int row = 0; row < width; row++) {
                double temp = sum[row][col - 1];
                path[row][col] = row;
                if (row > 0 && sum[row - 1][col - 1] < temp) {
                    temp = sum[row - 1][col - 1];
                    path[row][col] = row - 1;
                }
                if (row < width - 1 && sum[row + 1][col - 1] < temp) {
                    temp = sum[row + 1][col - 1];
                    path[row][col] = row + 1;
                }
                sum[row][col] = energy(row, col) + temp;
                // System.out.print(Math.round(sum[row][col] * 100.0) / 100.0 + " ");
            }
            // System.out.println();
        }
        int index = 0;
        for (int row = 1; row < width; row++) {
            if (sum[row][height - 1] < sum[index][height - 1]) {
                index = row;
            }
        }
        int[] seam = new int[height];
        seam[height - 1] = index;
        for (int col = height - 2; col >= 0 ; col--) {
            seam[col] = path[index][col + 1];
            index = path[index][col + 1];
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
