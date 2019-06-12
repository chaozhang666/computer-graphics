package A2_RayTracing;

/* Definition of the structure for Sphere */
public class SPHERE {

	// define
	/* x y z as center of the circle */
	/* radius as radius of the circle */
	/* kd as diffuse reflection coefficient */
	
	double x, y, z;
	double radius;
	double kd; 

	public SPHERE(double x, double y, double z, double radius, double kd) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = radius;
		this.kd = kd;
	}

}
