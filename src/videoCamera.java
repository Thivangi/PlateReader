import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;



@SuppressWarnings("serial")
public class videoCamera extends JPanel
{
    VideoCapture camera; 

    public videoCamera(VideoCapture cam){
        camera  = cam; 
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Mat mat = new Mat();
        if( camera.read(mat))
        {
            BufferedImage image = Utils.Mat2BufferedImage(mat);
            g.drawImage(image,10,10,image.getWidth(),image.getHeight(), null);
        }else{
            System.out.println("Connection lost!");
            camera.release();
        }
    }
}