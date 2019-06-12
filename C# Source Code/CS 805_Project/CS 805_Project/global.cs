using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CS_805_Project
{
    class global
    {

        /* Definition of image buffers */

        public static int LAYS = 128;
        public static int ROWS = 128;
        public static int COLS = 128;

        public static int[,,] CT = new int[LAYS, ROWS, COLS]; /* a 3D array for CT data */
        public static int[,,] SHADING = new int[LAYS, ROWS, COLS]; /* a 3D array for shading values */

        public static int IMG_ROWS = 512;
        public static int IMG_COLS = 512;
        public static int[,] out_image = new int[IMG_ROWS, IMG_COLS];
        public static int[,] out_image_save = new int[IMG_ROWS, IMG_COLS];
        public static int[,] out_image_new = new int[IMG_ROWS, IMG_COLS];

        /* Camera parameters */

        public static double[] VRP = new double[3];
        public static double[] VPN = new double[3];
        public static double[] VUP = new double[3];

        /* Image Plane Sizes */

        public static double focal = 0.05; /* 50 mm lens */
        public static double xmin = -0.0175; /* 35 mm "film" */
        public static double ymin = -0.0175;
        public static double xmax = 0.0175;
        public static double ymax = 0.0175;

        public static double[] Light = new double[3]; /* Light direction (unit length vector) */
        public static double IP = 255.0; /* Light Intensity */
        public static double kd = 1.0;

        public static double Threshold_shading = 25;

        public static double[,] Mwc = new double[4, 4];
        public static double[,] Mcw = new double[4, 4];

        public static double[] P0 = new double[4];
        public static double[] V0 = new double[4];

        public static double[] ts = new double[2]; // for storing the intersection points t0 and t1.

        public static byte[,] pic = new byte[IMG_ROWS, IMG_COLS];

    }
}
