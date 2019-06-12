package A2_RayTracing;

//created math tools for convenience

public class calc {

	public static double[][] MatrixTimes(double[][] a, double[][] b) {

		double[][] c = new double[4][4];
		int i, j, k;
		double s;

		for (j = 0; j <= 3; j++) {
			for (i = 0; i <= 3; i++) {
				s = 0;
				for (k = 0; k <= 3; k++) {
					s += a[k][j] * b[i][k];
				}
				c[i][j] = s;
			}

		}
		return c;
	}

	public static double[] transform(double[][] M, double[] data) {

		int i, j;
		double s;
		double[] newdata = new double[4];

		for (j = 0; j <= 3; j++) {
			s = 0;
			for (i = 0; i <= 3; i++) {
				s += M[i][j] * data[i];
			}
			newdata[j] = s;
		}

		return newdata;
	}

	public static double[] vector_minus(double[] a, double[] b) {
		double[] c = new double[4];
		c[0] = a[0] - b[0];
		c[1] = a[1] - b[1];
		c[2] = a[2] - b[2];
		c[3] = a[3] - b[3];
		return c;

	}

	public static double[] vector_add(double[] a, double[] b) {
		double[] c = new double[4];
		c[0] = a[0] + b[0];
		c[1] = a[1] + b[1];
		c[2] = a[2] + b[2];
		c[3] = a[3] + b[3];
		return c;
	}

	public static double[] normalize(double[] c) {
		double d;
		d = Math.sqrt(c[0] * c[0] + c[1] * c[1] + c[2] * c[2]);
		c[0] = c[0] / d;
		c[1] = c[1] / d;
		c[2] = c[2] / d;
		return c;
	}

	public static double[] vector_times(double[] d, double t) {
		d[0] *= t;
		d[1] *= t;
		d[2] *= t;
		return d;
	}

	public static double dot_product(double[] a, double[] b) {
		double c;
		c = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
		return c;
	}

	public static double max(double a, double b, double c) {
		double d = 0;
		if (a > d)
			d = a;
		if (b > d)
			d = b;
		if (c > d)
			d = c;
		return d;

	}

}