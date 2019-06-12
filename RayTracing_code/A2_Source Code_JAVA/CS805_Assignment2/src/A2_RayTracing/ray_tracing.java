package A2_RayTracing;

public class ray_tracing {

	static double C;
	static double[] P0 = new double[4];
	static double[] V0 = new double[4];
	//static double[] P = new double[4];
	//static double[] N = new double[3];
	static double kd;

	public static double main(double[] P0, double[] V0) {

		boolean found;
		found = ray_object_intersection.main(P0, V0);
		
		if (found) {	
			C = shading.get();				
			return C;
		} else
			return 0;
	}
}
