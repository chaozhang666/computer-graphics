using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

//Construction of ray
//Input: pixel index (i, j) in the screen coordinates
//Output: P0 and V0 (for parametric ray equation P = P0 + V0*t)
//in the world coordinates.

namespace CS_805_Project
{
    class ray_construction
    {
        static int i;
        static int j;
        static double[] P0 = new double[4];
        static double[] V0 = new double[4];

        public static void generate(int j, int i)
        {

            // Step 1:
            // Map (j, i) in the screen coordinates to (xc, yc) in the
            // camera coordinates.

            double xc, yc;

            xc = j * (global.xmax - global.xmin) / (global.IMG_COLS - 1) + global.xmin;
            yc = i * (global.ymax - global.ymin) / (global.IMG_ROWS - 1) + global.ymin;

            // Step 2:
            // Transform the origin (0.0, 0.0, 0.0) of the camera
            // coordinates to P0 in the world coordinates using the
            // transformation matrix Mcw. 

            double[] origin_camera = { 0.0, 0.0, 0.0, 1.0 };
            P0 = calc.transform(global.Mcw, origin_camera);

            //System.out.println(P0[0]+" "+P0[1]+" "+P0[2]);

            // Step 3:
            // Transform the point (xc, yc, f) on the image plane in
            // the camera coordinates to P1 in the world coordinates using
            // the transformation matrix Mcw.

            double[] data = new double[4];
            double[] P1 = new double[4];

            data[0] = xc;
            data[1] = yc;
            data[2] = global.focal;
            data[3] = 1;
            P1 = calc.transform(global.Mcw, data);

            // V0 = P1 – P0;
            V0[0] = P1[0] - P0[0];
            V0[1] = P1[1] - P0[1];
            V0[2] = P1[2] - P0[2];

            // normalize V0 into unit length;
            V0 = calc.normalize(V0);

            global.P0 = P0;
            global.V0 = V0;

        }


    }
}
