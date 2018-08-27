
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GENESYS
 */
public class Utils {
    public static Mat turnGray( Mat img){
        Mat mat1 = new Mat();
        Imgproc.cvtColor(img, mat1, Imgproc.COLOR_RGB2GRAY);
        return mat1;
    }
    public static Mat threash(Mat img){
        Mat threshed = new Mat();
        int SENSITIVITY_VALUE = 100;
        Imgproc.threshold(img, threshed, SENSITIVITY_VALUE,255,Imgproc.THRESH_BINARY);
        return threshed;
    }
    public static byte[] Mat2ByteArray(Mat m){
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
    public static BufferedImage Mat2BufferedImage(Mat m){

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
}
