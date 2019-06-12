package A2_RayTracing;

import java.io.*;

public class MainBody {

	public static void main(String[] args) throws IOException {

		int i, j;
		double C = 0;

		//get transform matrix
		MyModel.Mwc = Mwc_Mcw.Mwc(MyModel.VRP[0], MyModel.VRP[1], MyModel.VRP[2], MyModel.VPN[0], MyModel.VPN[1],
				MyModel.VPN[2], MyModel.VUP[0], MyModel.VUP[1], MyModel.VUP[2]);
		MyModel.Mcw = Mwc_Mcw.Mcw(MyModel.VRP[0], MyModel.VRP[1], MyModel.VRP[2], MyModel.VPN[0], MyModel.VPN[1],
				MyModel.VPN[2], MyModel.VUP[0], MyModel.VUP[1], MyModel.VUP[2]);

		
		//scan each pixel in the image 
		for (i = 0; i < MyModel.ROWS; i++)
			for (j = 0; j < MyModel.COLS; j++) {

				// construct the ray, V, started from the CenterOfProjection, P0, and passing
				// through the pixel (i, j);

				ray_construction.generate(j, i);
				//print.vector(MyModel.V0);

				C = ray_tracing.main(MyModel.P0, MyModel.V0);

				if (C != 0)
					MyModel.image[j][i] = (int) C;

			}

		// output the final image;
		FileOutputStream out = null;
		out = new FileOutputStream("C:/Users/CHAO/Desktop/output_image.raw"); // file path to write

		// output the image array into .raw file
		for (i = 0; i < MyModel.ROWS; i++)
			for (j = 0; j < MyModel.COLS; j++)
				out.write(MyModel.image[j][i]);
		out.close();

		
		
	}

}
