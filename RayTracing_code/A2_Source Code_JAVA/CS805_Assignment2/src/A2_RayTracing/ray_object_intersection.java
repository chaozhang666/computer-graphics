package A2_RayTracing;

public class ray_object_intersection {

	static double C;
	static double[] P0 = new double[4];
	static double[] V0 = new double[4];
	static double[] P = new double[4];

	static double[] N = new double[3];
	static double[] N1 = new double[3];
	static double[] N2 = new double[3];

	static double kd;
	static double kd1;
	static double kd2;

	public static boolean main(double[] P0, double[] V0) {

		double t1 = 0;
		double t2 = 0;

		kd1 = MyModel.obj1.kd;
		kd2 = MyModel.obj2.kd;

		t1 = ray_sphere_intersection(P0, V0);
		t2 = ray_polygon_intersection(P0, V0);

		if (t1 == 0 && t2 == 0)
			return false;
		else if (t2 == 0) {
			shading.P = calc.vector_add(P0, calc.vector_times(V0, t1));
			shading.N = N1;
			shading.kd = kd1;
			return true;
		} else if (t1 == 0) {
			shading.P = calc.vector_add(P0, calc.vector_times(V0, t2));
			shading.N = N2;
			shading.kd = kd2;
			return true;
		} else if (t1 < t2) {
			shading.P = calc.vector_add(P0, calc.vector_times(V0, t1));
			shading.N = N1;
			shading.kd = kd1;
			return true;
		} else {
			shading.P = calc.vector_add(P0, calc.vector_times(V0, t2));
			shading.N = N2;
			shading.kd = kd2;
			return true;
		}
	}

	public static double ray_sphere_intersection(double[] P0, double[] V0) {

		double t, A, B, C, R, delta;
		double t1, t2;

		// using method 2 from notes
		// solving A^2+B^2+C^2=R^2 by PO+t*V0
		
		R = MyModel.obj1.radius;
		A = V0[0] * V0[0] + V0[1] * V0[1] + V0[2] * V0[2];
		B = (P0[0] * V0[0] + P0[1] * V0[1] + P0[2] * V0[2]) * 2;
		C = P0[0] * P0[0] + P0[1] * P0[1] + P0[2] * P0[2] - R * R;

		delta = B * B - 4 * A * C;

		
		// determine if the ray has a intersection with the sphere
		if (delta < 0)
			return 0;
		else {

			t1 = (Math.abs(Math.sqrt(delta)) - B) / (2 * A);
			t2 = (-Math.abs(Math.sqrt(delta)) - B) / (2 * A);
			if (t1 < t2)
				t = t1;
			else
				t = t2;
			N1[0] = (P0[0] + V0[0] * t - MyModel.obj1.x);
			N1[1] = (P0[1] + V0[1] * t - MyModel.obj1.y);
			N1[2] = (P0[2] + V0[2] * t - MyModel.obj1.z);

			return t;
		}

	}

	public static double ray_polygon_intersection(double[] P0, double[] V0) {

		double N_value;
		double D;
		double t;

		double[] V1 = new double[3];
		double[] V2 = new double[3];
		double[] P = new double[3];

		V1[0] = MyModel.obj2.v[1][0] - MyModel.obj2.v[0][0];
		V1[1] = MyModel.obj2.v[1][1] - MyModel.obj2.v[0][1];
		V1[2] = MyModel.obj2.v[1][2] - MyModel.obj2.v[0][2];

		V2[0] = MyModel.obj2.v[2][0] - MyModel.obj2.v[0][0];
		V2[1] = MyModel.obj2.v[2][1] - MyModel.obj2.v[0][1];
		V2[2] = MyModel.obj2.v[2][2] - MyModel.obj2.v[0][2];

		N_value = Math.sqrt(Math.pow((V1[1] * V2[2] - V2[1] * V1[2]), 2) + Math.pow((V2[0] * V1[2] - V1[0] * V2[2]), 2)
				+ Math.pow((V1[0] * V2[1] - V2[0] * V1[1]), 2));
		N[0] = (V1[1] * V2[2] - V2[1] * V1[2]) / N_value;
		N[1] = (V2[0] * V1[2] - V1[0] * V2[2]) / N_value;
		N[2] = (V1[0] * V2[1] - V2[0] * V1[1]) / N_value;

		// System.out.println(N[0]+" "+N[1]+" "+N[2]);

		// compute D

		D = (N[0] * MyModel.obj2.v[0][0] + N[1] * MyModel.obj2.v[0][1] + N[2] * MyModel.obj2.v[0][2]) * (-1);
		// System.out.println(D);

		// t = -(N*O + D)/(N*V)
		t = ((N[0] * P0[0] + N[1] * P0[1] + N[2] * P0[2]) + D) / (N[0] * V0[0] + N[1] * V0[1] + N[2] * V0[2]) * (-1);
		// System.out.println(t);

		P[0] = P0[0] + V0[0] * t;
		P[1] = P0[1] + V0[1] * t;
		P[2] = P0[2] + V0[2] * t;
		// System.out.println(P[0]+" "+P[1]+" "+P[2]);

		if (is_inside(P, N)) {
			N2 = N;
			//System.out.println(t);
			return t;
		}

		else
			return 0;
	}

	public static boolean is_inside(double[] P, double[] N) {

		int number = 0;
		int i;
		int x, y;
		double k, b;
		double[][] V = new double[5][2];
		int[] remain = new int[2];
		remain = drop(N);
		x = remain[0];
		y = remain[1];

		// drop one dimension to make the scene into 2D
		V[0][0] = MyModel.obj2.v[0][x];
		V[0][1] = MyModel.obj2.v[0][y];
		V[1][0] = MyModel.obj2.v[1][x];
		V[1][1] = MyModel.obj2.v[1][y];
		V[2][0] = MyModel.obj2.v[2][x];
		V[2][1] = MyModel.obj2.v[2][y];
		V[3][0] = MyModel.obj2.v[3][x];
		V[3][1] = MyModel.obj2.v[3][y];

		V[0][0] = V[0][0] - P[x];
		V[0][1] = V[0][1] - P[y];
		V[1][0] = V[1][0] - P[x];
		V[1][1] = V[1][1] - P[y];
		V[2][0] = V[2][0] - P[x];
		V[2][1] = V[2][1] - P[y];
		V[3][0] = V[3][0] - P[x];
		V[3][1] = V[3][1] - P[y];
		V[4][0] = V[0][0];
		V[4][1] = V[0][1];

		
		//calculate the y value of the point intersected with x axis
		for (i = 0; i <= 3; i++) {
			k = (V[i][1] - V[i + 1][1]) / (V[i][0] - V[i + 1][0]);
			b = V[i][1] - V[i][0] * k;
			// y = kx +b
			if ((-b / k) >= 0)
				number++;
		}

		// System.out.println(number);

		if (number % 2 == 1)
			return true;
		else
			return false;

	}

	public static int[] drop(double[] N) {
		int[] r = new int[2];
		double max;
		int k = 0;
		max = calc.max(N[0], N[1], N[2]);
		for (int i = 0; i < 3; i++) {
			if (N[i] != max) {
				r[k] = i;
				k++;
			}
		}
		return r;
	}

}
