package A2_RayTracing;

// debug printing tools

public class print {

	public static int i, j;

	public static void matrix(double[][] a) {
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				System.out.print(a[j][i] + " ");
			}
			System.out.println();
		}
	}

	public static void vector(double[] b) {
		for (i = 0; i < 4; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}

	public static void image(int[][] image) {
		for (i = 0; i < 512; i++) {
			for (j = 0; j < 512; j++)
				System.out.print(MyModel.image[i][j] + " ");
			System.out.println();
		}
	}

}
