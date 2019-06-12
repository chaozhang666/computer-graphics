package volume_rendering;

//Ray-Box Intersection
//Input: ray – P0, V0
//Output: n – the number of intersections found
//				= 0, no intersection
//				= 1, one intersection
//				= 2, two intersections
//				It will be very rare to find more two intersections.
//				In this case, you can set it to 0.
//			ts[2] stores the found intersections.
//			if n = 2, you should have ts[0] < ts[1].
//					kd, the diffuse reflection coefficient of the surface.

public class ray_box_intersection {

	public static int main() {
		int n = 0;
		double t;
		double t0 = 0, t1 = 0;
		double x, y, z;
		double[] intersection = new double[4];

		// x(t) = P0[0] + V0[0] * t
		// y(t) = P0[1] + V0[1] * t
		// z(t) = P0[2] + V0[2] * t

		// for side x = 0
		t = (0 - global.P0[0]) / global.V0[0];
		y = global.P0[1] + global.V0[1] * t;
		z = global.P0[2] + global.V0[2] * t;
		if (y >= 0 && y <= global.ROWS && z >= 0 && z <= global.LAYS) {
			intersection[n] = t;
			n++;
		}

		// for side x = 127
		t = (127 - global.P0[0]) / global.V0[0];
		y = global.P0[1] + global.V0[1] * t;
		z = global.P0[2] + global.V0[2] * t;
		if (y >= 0 && y <= global.ROWS && z >= 0 && z <= global.LAYS) {
			intersection[n] = t;
			n++;
		}

		// for side y = 0
		t = (0 - global.P0[1]) / global.V0[1];
		x = global.P0[0] + global.V0[0] * t;
		z = global.P0[2] + global.V0[2] * t;
		if (x >= 0 && x <= global.COLS && z >= 0 && z <= global.LAYS) {
			intersection[n] = t;
			n++;
		}

		// for side y = 127
		t = (127 - global.P0[1]) / global.V0[1];
		x = global.P0[0] + global.V0[0] * t;
		z = global.P0[2] + global.V0[2] * t;
		if (x >= 0 && x <= global.COLS && z >= 0 && z <= global.LAYS) {
			intersection[n] = t;
			n++;
		}

		// for side z = 0
		t = (0 - global.P0[2]) / global.V0[2];
		x = global.P0[0] + global.V0[0] * t;
		y = global.P0[1] + global.V0[1] * t;
		if (x >= 0 && x <= global.COLS && y >= 0 && y <= global.ROWS) {
			intersection[n] = t;
			n++;
		}

		// for side z = 127
		t = (127 - global.P0[2]) / global.V0[2];
		x = global.P0[0] + global.V0[0] * t;
		y = global.P0[1] + global.V0[1] * t;
		if (x >= 0 && x <= global.COLS && y >= 0 && y <= global.ROWS) {
			intersection[n] = t;
			n++;
		}

		// find more than two intersections
		if (n > 2) {
			n = 0;
		}

		// intersections found
		if (n != 0) {
			if (intersection[0] < intersection[1]) {
				t0 = intersection[0];
				t1 = intersection[1];
			} else {
				t1 = intersection[0];
				t0 = intersection[1];
			}
			global.ts[0] = t0;
			global.ts[1] = t1;
		}

		return n;
	}

}
