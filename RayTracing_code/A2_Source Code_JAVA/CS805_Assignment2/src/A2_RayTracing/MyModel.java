package A2_RayTracing;

public class MyModel {

	// Initialize the global data structures;

	/* definition of the image buffer */
	public static final int ROWS = 512;
	public static final int COLS = 512;

	// public static String image[][] = new String[ROWS][COLS]; ???
	public static int image[][] = new int[ROWS][COLS];

	/* definition of window on the image plane in the camera coordinates */
	/* They are used in mapping (j, i) in the screen coordinates into */
	/* (x, y) on the image plane in the camera coordinates */
	/* The window size used here simulates the 35 mm film. */
	public static double xmin = 0.0175;
	public static double ymin = -0.0175;
	public static double xmax = -0.0175;
	public static double ymax = 0.0175;

	/* definition of the camera parameters */
	public static double VRP[] = new double[] { 1.0, 2.0, 3.5 };
	 public static double VPN[] = new double[] { 0.0, -1.0, -2.5};
	public static double VUP[] = new double[] { 0.0, 1.0, 0.0 };

	//public static double VRP[] = new double[] { 0.0, -6.0, 0.0 };
	//public static double VPN[] = new double[] { 0.0, 1.0, 0.0 };
	//public static double VUP[] = new double[] { 0.0, 0.0, 1.0 };

	/* focal length simulating 50 mm lens */
	public static double focal = 0.05;

	/* definition of light source */
	public static double LPR[] = new double[] { -10.0, 10.0, 2.0 };

	public static double IP = 200.0; /* intensity of the point light source */

	public static double Mwc[][] = new double[4][4];
	public static double Mcw[][] = new double[4][4];

	public static double[] P0 = new double[4];
	public static double[] V0 = new double[4];

	public static SPHERE obj1 = new SPHERE(1.0, 1.0, 1.0, 1.0, 0.75); /* create a spherical object */

	public static POLY4 obj2 = new POLY4(); /* create a polygon object */

}
