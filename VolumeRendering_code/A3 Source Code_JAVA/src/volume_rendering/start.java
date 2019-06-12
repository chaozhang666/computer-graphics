package volume_rendering;

import java.io.*;

public class start {

	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {

			in = new FileInputStream("C:/Users/CHAO/Desktop/smallHead.den");
			out = new FileOutputStream("C:/Users/CHAO/Desktop/output_image.raw");

			int i, j, k;
			int n;

			// read input density file
			for (k = 0; k < global.LAYS; k++)
				for (i = 0; i < global.ROWS; i++)
					for (j = 0; j < global.COLS; j++)
						global.CT[k][i][j] = in.read();

			// compute the shading value
			compute_shading_volume.main();

			// get transform matrix
			global.Mwc = Mwc_Mcw.Mwc(global.VRP[0], global.VRP[1], global.VRP[2], global.VPN[0], global.VPN[1],
					global.VPN[2], global.VUP[0], global.VUP[1], global.VUP[2]);
			global.Mcw = Mwc_Mcw.Mcw(global.VRP[0], global.VRP[1], global.VRP[2], global.VPN[0], global.VPN[1],
					global.VPN[2], global.VUP[0], global.VUP[1], global.VUP[2]);

			// ===================================================
			// The Main Ray-Tracing Volume Rendering Part.
			// ===================================================

			for (i = 0; i < global.IMG_ROWS; i++)
				for (j = 0; j < global.IMG_COLS; j++) {

					ray_construction.generate(j, i); // Construct the ray
					n = ray_box_intersection.main(); // n is the number of intersections found.
					if (n == 2) {
						global.out_image[j][i] = volume_ray_tracing.main();
					}
				}

			/* Save the output image */
			for (i = 0; i < global.IMG_ROWS; i++)
				for (j = 0; j < global.IMG_COLS; j++)
					out.write(global.out_image[j][i]);
			out.close();

		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

}
