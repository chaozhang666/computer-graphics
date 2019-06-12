package A2_RayTracing;

public class ray_construction {

	static int i;
	static int j;
	static double[] P0 = new double[4];
	static double[] V0 = new double[4];

	// Construction of ray
	// Input: pixel index (i, j) in the screen coordinates
	// Output: P0, V0 (for parametric ray equation P = P0 + V0*t)
	// in the world coordinates.
	public static void generate(int j, int i) {

		// Step 1:
		// Map (j, i) in the screen coordinates to (xc, yc) in the
		// camera coordinates.

		double xc, yc;
		
		xc = j * (MyModel.xmax - MyModel.xmin) / (MyModel.COLS - 1) + MyModel.xmin;
		yc = i * (MyModel.ymax - MyModel.ymin) / (MyModel.ROWS - 1) + MyModel.ymin;

		// Step 2:
		// Transform the origin (0.0, 0.0, 0.0) of the camera
		// coordinates to P0 in the world coordinates using the
		// transformation matrix Mcw. Note that the transformed result
		// should be VRP.

		double[] origin_camera = { 0.0, 0.0, 0.0, 1.0 };
		P0 = calc.transform(MyModel.Mcw, origin_camera);

		// Step 3:
		// Transform the point (xc, yc, f) on the image plane in
		// the camera coordinates to P1 in the world coordinates using
		// the transformation matrix Mcw.

		double[] data = new double[4];
		double[] P1 = new double[4];

		data[0] = xc;
		data[1] = yc;
		data[2] = MyModel.focal;
		data[3] = 1;
		P1 = calc.transform(MyModel.Mcw, data);
		//print.vector(P1);
		
		// V0 = P1 – P0;
		V0[0] = P1[0] - P0[0];
		V0[1] = P1[1] - P0[1];
		V0[2] = P1[2] - P0[2];

		// normalize V0 into unit length;
		V0 = calc.normalize(V0);

		MyModel.P0 = P0;
		MyModel.V0 = V0;

	}

}
