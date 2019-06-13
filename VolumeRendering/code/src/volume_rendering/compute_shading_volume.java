package volume_rendering;

/* Compute the shading values at each voxel:
	- Compute the partial derivatives at each voxel;
	- Normalize the partial derivative into a unit-length vector;
	- Apply a simplified illumination model for shading:
	I = Ip*(N*L)
*/

public class compute_shading_volume {

	public static void main() {

		int x, y, z;
		double dx, dy, dz;
		double magnitude;
		double shading_value = 0;

		// boundary layers are skipped.
		for (z = 1; z < (global.LAYS - 1); z++)
			for (y = 1; y < (global.ROWS - 1); y++)
				for (x = 1; x < (global.COLS - 1); x++) {

					dx = (global.CT[z][y][x + 1] - global.CT[z][y][x - 1]) / 2.0;
					dy = (global.CT[z][y + 1][x] - global.CT[z][y - 1][x]) / 2.0;
					dz = (global.CT[z + 1][y][x] - global.CT[z - 1][y][x]) / 2.0;

					// if the magnitude of the gradient is less than a
					// pre-specified epsilon value, set shading to 0.
					// otherwise, compute the diffuse shading.

					magnitude = Math.sqrt(dx * dx + dy * dy + dz * dz);

					if (magnitude < global.Threshold_shading) {
						global.SHADING[z][y][x] = 0;
					} else {
						dx /= magnitude;
						dy /= magnitude;
						dz /= magnitude;

						shading_value = global.IP * global.kd
								* (dx * global.Light[0] + dy * global.Light[1] + dz * global.Light[2]);

						if (shading_value < 0)
							shading_value = 0;

					}

					// save the result in the shading volume.
					global.SHADING[z][y][x] = (int) shading_value;

				}
	}

}
