import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;



@SuppressWarnings("serial")
public class videoCamera extends JPanel
{
    VideoCapture camera; 

    public videoCamera(VideoCapture cam) 
    {

        camera  = cam; 

    }

    public byte[] Mat2ByteArray(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage img = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return targetPixels;
    }
    public BufferedImage Mat2BufferedImage(Mat m)
    {

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage img = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return img;


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Mat mat = new Mat();

        if( camera.read(mat))
        {
            
            int random = (int) (Math.random()*1000000);
            File outputfile = new File("resultados/" + random + ".jpg");
            BufferedImage image = Mat2BufferedImage(mat);
            try {
            ImageIO.write(image, "jpg", outputfile);
                String response = PlateDetection.readPlate("resultados/" + random + ".jpg");
                System.out.println("-------------------->"+response);
                
                if(outputfile.exists()){
                    File renamed = new File("resultados/"+response.substring(0, 7)+".jpg");
                    if(!renamed.exists()){
                        outputfile.renameTo(renamed);
                    }else{
                        outputfile.delete();
                    }
                } 
            } catch (Exception ex) {
                if(outputfile.exists()){
                    outputfile.delete();
                }
            }
            g.drawImage(image,10,10,image.getWidth(),image.getHeight(), null);
        }

        

    }
    public Mat turnGray( Mat img)

    {
        Mat mat1 = new Mat();
        Imgproc.cvtColor(img, mat1, Imgproc.COLOR_RGB2GRAY);
        return mat1;
    }
    public Mat threash(Mat img)
    {
        Mat threshed = new Mat();
        int SENSITIVITY_VALUE = 100;
        Imgproc.threshold(img, threshed, SENSITIVITY_VALUE,255,Imgproc.THRESH_BINARY);
        return threshed;
    }


}