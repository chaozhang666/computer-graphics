package A2_RayTracing;

/* Definition of Polygon with 4 edges */
public class POLY4 {

	double[][] v = new double [4][3];
	double[] N = new double[3]; 
	double kd; 

	public POLY4() {
		
		/* v0 */
		v[0][0] = 0.0;
		v[0][1] = 0.0;
		v[0][2] = 0.0;
		
		/* v1 */
		v[1][0] = 0.0;   
		v[1][1] = 0.0;
		v[1][2] = 2.0;
		
		/* v2 */
		v[2][0] = 2.0;
		v[2][1] = 0.0;
		v[2][2] = 2.0;
		
		/* v3 */
		v[3][0] = 2.0;
		v[3][1] = 0.0;
		v[3][2] = 0.0;
		
		/* normal of the polygon */
		N[0] = 0.0;
		N[1] = 1.0;
		N[2] = 0.0;
		
		/* diffuse reflection coefficient */
		kd = 0.8;
		
	}
	
}