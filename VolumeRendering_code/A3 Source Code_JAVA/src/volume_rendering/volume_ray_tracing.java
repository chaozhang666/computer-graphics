package volume_rendering;

//Main Volume Ray-Tracing Function:
//Input:
//		O – starting point of the ray
//		V – unit-length vector pointing in the direction of the ray
//		ts[0] and ts[1] are two points on the ray. Assuming
//		ts[0] < ts[1]
//Output: the integrated shading value along the ray between
//			ts[0] and ts[1]

public class volume_ray_tracing {

	public static int main() {

		double t, t0, t1;
		double x, y, z;
		double a, c;
		double dt = 1.0; // the interval for sampling along the ray
		double C = 0.0; // for accumulating the shading value
		double T = 1.0; // for accumulating the transparency

		t0 = global.ts[0];
		t1 = global.ts[1];

		/* Marching through the CT volume from t0 to t1 by step size dt. */

		for (t = t0; t <= t1; t += dt) {

			/* Compute the 3D coordinates of the current sample position in the volume */

			x = global.P0[0] + t * global.V0[0];
			y = global.P0[1] + t * global.V0[1];
			z = global.P0[2] + t * global.V0[2];

			/*
			 * Obtain the shading value C and opacity value A from the shading volume and CT
			 * volume, respectively, by using tri-linear interpolation.
			 */

			a = trilinear_interpolation.main(global.CT, x, y, z, "opacity"); // obtain opacity(density) value
			c = trilinear_interpolation.main(global.SHADING, x, y, z, "shading"); // obtain shading value c

			/* Accumulate the shading values in the front-to-back order. */

			C += T * a * c;
			T *= (1.0 - a);

		}

		return (int) C;

	}

}
