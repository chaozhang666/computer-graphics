package volume_rendering;

public class trilinear_interpolation {

	public static int x0, x1, y0, y1, z0, z1;
	public static double xd, yd, zd;
	public static double c000, c100, c001, c101, c010, c110, c011, c111;
	public static double c00, c01, c10, c11;
	public static double c0, c1;
	public static double c;
	public static double s, t;

	public static double main(int[][][] data, double x, double y, double z, String mark) {

		x0 = (int) x;
		x1 = x0 + 1;
		y0 = (int) y;
		y1 = y0 + 1;
		z0 = (int) z;
		z1 = z0 + 1;

		// return 0 if the point is outside the boundary
		if (x1 > 127 || x0 < 0 || y1 > 127 || y0 < 0 || z1 > 127 || z0 < 0)
			return 0;

		c000 = data[z1][y0][x0];
		c100 = data[z1][y0][x1];

		c001 = data[z1][y1][x0];
		c101 = data[z1][y1][x1];

		c010 = data[z0][y0][x0];
		c110 = data[z0][y0][x1];

		c011 = data[z0][y1][x0];
		c111 = data[z0][y1][x1];

		s = x - x0;
		t = 1.0 - s;

		c00 = c000 * t + c100 * s;
		c01 = c001 * t + c101 * s;
		c10 = c010 * t + c110 * s;
		c11 = c011 * t + c111 * s;

		s = z - z0;
		t = 1 - s;

		c0 = c10 * t + c00 * s;
		c1 = c11 * t + c01 * s;

		s = y - y0;
		t = 1 - s;

		c = c0 * t + c1 * s;

		if (mark == "opacity")
			return c / 255; // opacity being normalized in the range [0.0, 1.0]
		else
			return c;

	}

}
