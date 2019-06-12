using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CS_805_Project
{
    class Mwc_Mcw
    {
        public static double[,] get_matrix(double VRP_x, double VRP_y, double VRP_z, double VPN_x, double VPN_y,
            double VPN_z, double VUP_x, double VUP_y, double VUP_z, String mark)
        {

            double u_x, u_y, u_z;
            double v_x, v_y, v_z;
            double n_x, n_y, n_z;
            double n_value, u_value, v_value;
            double[,] R = new double[4, 4];
            double[,] T = new double[4, 4];
            double[,] M = new double[4, 4];

            // n = VPN / |VPN|
            n_value = Math.Sqrt(VPN_x * VPN_x + VPN_y * VPN_y + VPN_z * VPN_z);
            n_x = VPN_x / n_value;
            n_y = VPN_y / n_value;
            n_z = VPN_z / n_value;

            // u = VUP X VPN / |VUP X VPN|
            u_value = Math.Pow((VUP_y * VPN_z - VPN_y * VUP_z), 2) + Math.Pow((VPN_x * VUP_z - VUP_x * VPN_z), 2)
                + Math.Pow((VUP_x * VPN_y - VPN_x * VUP_y), 2);
            u_value = Math.Sqrt(u_value);
            u_x = (VUP_y * VPN_z - VPN_y * VUP_z) / u_value;
            u_y = (VPN_x * VUP_z - VUP_x * VPN_z) / u_value;
            u_z = (VUP_x * VPN_y - VPN_x * VUP_y) / u_value;

            // v = VPN X u / |VPN X u|
            v_value = Math.Pow((VPN_y * u_z - u_y * VPN_z), 2) + Math.Pow((u_x * VPN_z - VPN_x * u_z), 2)
                + Math.Pow((VPN_x * u_y - u_x * VPN_y), 2);
            v_value = Math.Sqrt(v_value);
            v_x = (VPN_y * u_z - u_y * VPN_z) / v_value;
            v_y = (u_x * VPN_z - VPN_x * u_z) / v_value;
            v_z = (VPN_x * u_y - u_x * VPN_y) / v_value;

            R[0, 0] = u_x;
            R[1, 0] = u_y;
            R[2, 0] = u_z;
            R[3, 0] = 0.0;

            R[0, 1] = v_x;
            R[1, 1] = v_y;
            R[2, 1] = v_z;
            R[3, 1] = 0.0;

            R[0, 2] = n_x;
            R[1, 2] = n_y;
            R[2, 2] = n_z;
            R[3, 2] = 0.0;

            R[0, 3] = 0.0;
            R[1, 3] = 0.0;
            R[2, 3] = 0.0;
            R[3, 3] = 1.0;

            // Because R is an orthogonal matrix,invertible matrix of R is equal to R's
            // transposed matrix

            // Matrix of T
            T[0, 0] = 1.0;
            T[1, 0] = 0.0;
            T[2, 0] = 0.0;
            T[3, 0] = -VRP_x;

            T[0, 1] = 0.0;
            T[1, 1] = 1.0;
            T[2, 1] = 0.0;
            T[3, 1] = -VRP_y;

            T[0, 2] = 0.0;
            T[1, 2] = 0.0;
            T[2, 2] = 1.0;
            T[3, 2] = -VRP_z;

            T[0, 3] = 0.0;
            T[1, 3] = 0.0;
            T[2, 3] = 0.0;
            T[3, 3] = 1.0;

            if (mark == "mcw")
            {

                // get the invertible matrix of T
                T[3, 0] *= (-1);
                T[3, 1] *= (-1);
                T[3, 2] *= (-1);

                // get the invertible matrix of R
                R[1, 0] = v_x;
                R[2, 0] = n_x;

                R[0, 1] = u_y;
                R[1, 1] = v_y;
                R[2, 1] = n_y;

                R[0, 2] = u_z;
                R[1, 2] = v_z;

                M = calc.MatrixTimes(T, R); // calculation of R(inverse)*T(inverse)

            }

            // calculation of R*T
            if (mark == "mwc")
                M = calc.MatrixTimes(R, T);

            return M;
        }

        public static double[,] Mwc(double VRP_x, double VRP_y, double VRP_z, double VPN_x, double VPN_y, double VPN_z,
            double VUP_x, double VUP_y, double VUP_z)
        {
            double[,] M = new double[4, 4];
            M = get_matrix(VRP_x, VRP_y, VRP_z, VPN_x, VPN_y, VPN_z, VUP_x, VUP_y, VUP_z, "mwc");
            return M;
        }

        public static double[,] Mcw(double VRP_x, double VRP_y, double VRP_z, double VPN_x, double VPN_y, double VPN_z,
             double VUP_x, double VUP_y, double VUP_z)
        {
            double[,] M = new double[4, 4];
            M = get_matrix(VRP_x, VRP_y, VRP_z, VPN_x, VPN_y, VPN_z, VUP_x, VUP_y, VUP_z, "mcw");
            return M;
        }


    }
}
