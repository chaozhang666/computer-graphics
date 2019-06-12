using System;
using System.IO;
using System.Windows.Forms;
using System.Drawing;


namespace CS_805_Project
{
    public partial class Form : System.Windows.Forms.Form
    {
        string currentDirectory = Directory.GetCurrentDirectory();

        public Form()
        {
            InitializeComponent();
            this.pictureBox1.Image = Image.FromFile("Lenna.png");
        }

        //the output image show area
        private void pictureBox1_Click(object sender, EventArgs e)
        {
        }

        //Button: front face default setting
        private void button2_Click(object sender, EventArgs e)
        {
            VRP0.Text = "54.0";
            VRP0.Show();
            VRP1.Text = "-180.0";
            VRP1.Show();
            VRP2.Text = "64.0";
            VRP2.Show();

            VPN0.Text = "0.0";
            VPN0.Show();
            VPN1.Text = "1.0";
            VPN1.Show();
            VPN2.Text = "0.0";
            VPN2.Show();

            VUP0.Text = "0.0";
            VUP0.Show();
            VUP1.Text = "0.0";
            VUP1.Show();
            VUP2.Text = "1.0";
            VUP2.Show();

            Light0.Text = "1.0";
            Light0.Show();
            Light1.Text = "0.0";
            Light1.Show();
            Light2.Text = "0.0";
            Light2.Show();
        }

        //Button: side face default setting
        private void default_set2_Click_1(object sender, EventArgs e)
        {
            VRP0.Text = "340.0";
            VRP0.Show();
            VRP1.Text = "64.0";
            VRP1.Show();
            VRP2.Text = "64.0";
            VRP2.Show();

            VPN0.Text = "-1.0";
            VPN0.Show();
            VPN1.Text = "0.0";
            VPN1.Show();
            VPN2.Text = "0.0";
            VPN2.Show();

            VUP0.Text = "0.0";
            VUP0.Show();
            VUP1.Text = "0.0";
            VUP1.Show();
            VUP2.Text = "1.0";
            VUP2.Show();

            Light0.Text = "-1.0";
            Light0.Show();
            Light1.Text = "0.0";
            Light1.Show();
            Light2.Text = "0.0";
            Light2.Show();
        }

        //Button: top head default setting
        private void button9_Click(object sender, EventArgs e)
        {
            VRP0.Text = "54.0";
            VRP0.Show();
            VRP1.Text = "64.0";
            VRP1.Show();
            VRP2.Text = "-200.0";
            VRP2.Show();

            VPN0.Text = "0.0";
            VPN0.Show();
            VPN1.Text = "0.0";
            VPN1.Show();
            VPN2.Text = "1.0";
            VPN2.Show();

            VUP0.Text = "0.0";
            VUP0.Show();
            VUP1.Text = "-1.0";
            VUP1.Show();
            VUP2.Text = "0.0";
            VUP2.Show();

            Light0.Text = "1.0";
            Light0.Show();
            Light1.Text = "0.0";
            Light1.Show();
            Light2.Text = "0.0";
            Light2.Show();
        }

        //Button: Display
        private void button1_Click(object sender, EventArgs e)
        {

            //set global camera parameters
            try
            {
                global.VRP[0] = Convert.ToDouble(VRP0.Text);
                global.VRP[1] = Convert.ToDouble(VRP1.Text);
                global.VRP[2] = Convert.ToDouble(VRP2.Text);

                global.VPN[0] = Convert.ToDouble(VPN0.Text);
                global.VPN[1] = Convert.ToDouble(VPN1.Text);
                global.VPN[2] = Convert.ToDouble(VPN2.Text);

                global.VUP[0] = Convert.ToDouble(VUP0.Text);
                global.VUP[1] = Convert.ToDouble(VUP1.Text);
                global.VUP[2] = Convert.ToDouble(VUP2.Text);

                global.Light[0] = Convert.ToDouble(Light0.Text);
                global.Light[1] = Convert.ToDouble(Light1.Text);
                global.Light[2] = Convert.ToDouble(Light2.Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Please set camera parameters!");
            }

            //Start: Volumn Randering
            volumn_randering();

            //display the output image in PictureBox
            this.pictureBox1.Image = show_image();
        }

        //Button: rotatae +90 degree
        private void button2_Click_1(object sender, EventArgs e)
        {
            try
            {
                int i, j;

                //change values in array for rotation
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image_new[j, i] = global.out_image[i, global.IMG_ROWS - 1 - j];

                //overwrite the orignal image array
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = global.out_image_new[j, i];

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Rotation error!");
            }
        }

        //Button: rotatae -90 degree
        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                int i, j;

                //change values in array for rotation
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image_new[j, i] = global.out_image[global.IMG_ROWS - 1 - i, j];

                //overwrite the orignal image array

                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = global.out_image_new[j, i];

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Rotation error!");
            }
        }

        //Button: invert the image
        private void button6_Click(object sender, EventArgs e)
        {
            try
            {
                int i, j;

                //change values in array for rotation
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image_new[j, i] = 255 - global.out_image[j, i];

                //overwrite the orignal image array
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = global.out_image_new[j, i];

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Invert error!");
            }
        }

        //Button: histogram equalization 
        private void button7_Click(object sender, EventArgs e)
        {
            try
            {
                int i, j;
                double values;
                int[] h = new int[256];
                int[] H = new int[256];

                for (i = 0; i < 256; i++) // initialization of h[]
                    h[i] = 0;

                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        h[global.out_image[i, j]]++; // Compute the histogram of the input
                                                     // image and store it in h[ ]

                H[0] = h[0];
                for (i = 1; i < 256; i++) // Compute the cumulative histogram and store it in H[]
                    H[i] = H[i - 1] + h[i];

                double s = 0.00097; // get the scaling factor S = (k-1)/(ROWS*COLS)

                for (i = 0; i < 256; i++) // Normalize H[] with the scaling factor S
                {
                    values = H[i] * s;
                    H[i] = Convert.ToInt32(values);
                }


                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image_new[i, j] = H[global.out_image[i, j]];

                //overwrite the orignal image array
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                    {
                        global.out_image[j, i] = global.out_image_new[j, i];
                    }

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Histogram Equalization error!");
            }
        }

        //Button: brighter: for every pixel, values add 20
        private void button8_Click(object sender, EventArgs e)
        {
            try
            {
                int i, j;
                int data;

                //change values in array for rotation
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                    {
                        data = global.out_image[j, i] + 20;
                        if (data < 0)
                            data = 0;
                        if (data > 255)
                            data = 255;
                        global.out_image_new[j, i] = data;
                    }

                //overwrite the orignal image array
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = global.out_image_new[j, i];

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Brighter error!");
            }
        }

        //Button: darker : for every pixel, values minus 20
        private void button10_Click(object sender, EventArgs e)
        {
            try
            {
                int i, j;
                int data;

                //change values in array for rotation
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                    {
                        data = global.out_image[j, i] - 20;
                        if (data < 0)
                            data = 0;
                        if (data > 255)
                            data = 255;
                        global.out_image_new[j, i] = data;
                    }

                //overwrite the orignal image array
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = global.out_image_new[j, i];

                //display in the picture box 
                this.pictureBox1.Image = show_image();
            }
            catch (Exception)
            {
                MessageBox.Show("Draker error!");
            }
        }

        //Button: reset all parameters (go back to display button)
        private void button11_Click(object sender, EventArgs e)
        {
            //reset global camera parameters
            try
            {
                global.VRP[0] = Convert.ToDouble(VRP0.Text);
                global.VRP[1] = Convert.ToDouble(VRP1.Text);
                global.VRP[2] = Convert.ToDouble(VRP2.Text);

                global.VPN[0] = Convert.ToDouble(VPN0.Text);
                global.VPN[1] = Convert.ToDouble(VPN1.Text);
                global.VPN[2] = Convert.ToDouble(VPN2.Text);

                global.VUP[0] = Convert.ToDouble(VUP0.Text);
                global.VUP[1] = Convert.ToDouble(VUP1.Text);
                global.VUP[2] = Convert.ToDouble(VUP2.Text);

                global.Light[0] = Convert.ToDouble(Light0.Text);
                global.Light[1] = Convert.ToDouble(Light1.Text);
                global.Light[2] = Convert.ToDouble(Light2.Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Please set camera parameters!");
            }

            //Start: Volumn Randering
            volumn_randering();

            //display the output image in PictureBox
            this.pictureBox1.Image = show_image();

        }

        //Button: zoom in
        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                //zoom in the camera len by factor=0.75 to zoom in the picture
                int scale;
                double factor = 0.75;
                for (scale = 0; scale <= 2; scale++)
                    global.VRP[scale] *= factor;

                //Start: Volumn Randering
                volumn_randering();

                //display the output image in PictureBox
                this.pictureBox1.Image = show_image();

            }
            catch (Exception)
            {
                MessageBox.Show("Zoom in error!");
            }

        }

        //Button: zoom out
        private void button5_Click(object sender, EventArgs e)
        {
            try
            {
                //zoom out the camera len by factor=1.25 to zoom out the picture
                int scale;
                double factor = 1.33;
                for (scale = 0; scale <= 2; scale++)
                    global.VRP[scale] *= factor;

                //Start: Volumn Randering
                volumn_randering();

                //display the output image in PictureBox
                this.pictureBox1.Image = show_image();

            }
            catch (Exception)
            {
                MessageBox.Show("Zoom out error!");
            }
        }

        //Function: display the output image in the interface
        public static Bitmap show_image()
        {
            {
                int i, j;

                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.pic[j, i] = Convert.ToByte(global.out_image[j, i]);

                byte[] pixels = new byte[global.pic.GetLength(0) * global.pic.GetLength(1)];

                for (int y = 0; y < global.pic.GetLength(1); y++)
                    for (int x = 0; x < global.pic.GetLength(0); x++)
                        pixels[y * global.pic.GetLength(0) + x] = global.pic[x, y];

                //create a new Bitmap
                Bitmap bmp = new Bitmap(global.pic.GetLength(0), global.pic.GetLength(1), System.Drawing.Imaging.PixelFormat.Format8bppIndexed);

                System.Drawing.Imaging.ColorPalette pal = bmp.Palette;

                //create grayscale palette
                for (int g = 0; g < 256; g++)
                    pal.Entries[g] = Color.FromArgb((int)255, g, g, g);

                //assign to bmp
                bmp.Palette = pal;

                //lock it to get the BitmapData Object
                System.Drawing.Imaging.BitmapData bmData =
                    bmp.LockBits(new Rectangle(0, 0, bmp.Width, bmp.Height), System.Drawing.Imaging.ImageLockMode.WriteOnly, System.Drawing.Imaging.PixelFormat.Format8bppIndexed);

                //copy the bytes
                System.Runtime.InteropServices.Marshal.Copy(pixels, 0, bmData.Scan0, bmData.Stride * bmData.Height);

                //never forget to unlock the bitmap
                bmp.UnlockBits(bmData);

                //display
                return bmp;
            }
        }

        //Function: [Volumn Randering] : rander the volumn data to get the output image stored in global.out_image[,]
        public void volumn_randering()
        {
            try
            {
                int i, j, k;
                int n;

                string path_in = System.IO.Path.Combine(currentDirectory, "smallHead.den");
                FileStream input = new FileStream(path_in, FileMode.Open, FileAccess.Read);

                for (k = 0; k < global.LAYS; k++)
                    for (i = 0; i < global.ROWS; i++)
                        for (j = 0; j < global.COLS; j++)
                            global.CT[k, i, j] = input.ReadByte();
                input.Close();

                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                        global.out_image[j, i] = 0;

                // compute the shading value
                compute_shading_volume.main();

                // get transform matrix
                global.Mwc = Mwc_Mcw.Mwc(global.VRP[0], global.VRP[1], global.VRP[2], global.VPN[0], global.VPN[1],
                        global.VPN[2], global.VUP[0], global.VUP[1], global.VUP[2]);
                global.Mcw = Mwc_Mcw.Mcw(global.VRP[0], global.VRP[1], global.VRP[2], global.VPN[0], global.VPN[1],
                        global.VPN[2], global.VUP[0], global.VUP[1], global.VUP[2]);

                // The Main Ray-Tracing Volume Rendering Part.
                for (i = 0; i < global.IMG_ROWS; i++)
                    for (j = 0; j < global.IMG_COLS; j++)
                    {
                        ray_construction.generate(j, i); // Construct the ray
                        n = ray_box_intersection.main(); // n is the number of intersections found.
                        if (n == 2)
                        {
                            global.out_image[j, i] = volume_ray_tracing.main();
                        }
                    }
            }
            catch (Exception)
            {
                MessageBox.Show("Volumn Randering Error!");
            }
        }

    }
}
