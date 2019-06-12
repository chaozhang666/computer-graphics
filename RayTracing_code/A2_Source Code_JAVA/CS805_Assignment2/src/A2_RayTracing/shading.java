// Shading:
// Input: P[3] – point position
// N[3] – surface normal at that point
// kd – diffuse reflection coefficient of the surface
// Output: C – shading value

package A2_RayTracing;

public class shading {

	public static double[] P = new double[3];
	public static double[] N = new double[3];
	public static double kd;
	public static double C;
	public static double[] L = new double[4]; // LPR is the light position

	public static double get() {

		// L = LPR - P;
		L[0] = MyModel.LPR[0] - P[0];
		L[1] = MyModel.LPR[1] - P[1];
		L[2] = MyModel.LPR[2] - P[2];

		// normalize L to unit length;
		L = calc.normalize(L);

		C = MyModel.IP * kd * (N[0] * L[0] + N[1] * L[1] + N[2] * L[2]);

		return C;
	}

}
