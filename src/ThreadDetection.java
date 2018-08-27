
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GENESYS
 */
public class ThreadDetection extends Thread {
    VideoCapture camera;
    String path = "resultados/";
    public ThreadDetection(String url, String pPath) {
        camera = new VideoCapture(url);
        path = pPath;
    }
    @Override
    public void run(){
        System.out.println("Running daemon");
        while(camera.isOpened()){
            Mat mat = new Mat();
            if( camera.read(mat))
            {
                int random = (int) (Math.random()*1000000);
                File outputfile = new File(path + random + ".jpg");
                BufferedImage image = Utils.Mat2BufferedImage(mat);
                try {
                ImageIO.write(image, "jpg", outputfile);
                    String response = PlateDetection.readPlate(path + random + ".jpg");
                    System.out.println("-------------------->"+response);
                    if(outputfile.exists()){
                        File renamed = new File(path+response.substring(0, 7)+".jpg");
                        if(!renamed.exists()){
                            outputfile.renameTo(renamed);
                        }else{
                            outputfile.delete();
                        }
                    }
                    TimeUnit.SECONDS.sleep(0);
                } catch (Exception ex) {
                    if(outputfile.exists()){
                        outputfile.delete();
                    }
                }
            }else{
                System.out.println("Connection lost!!");
                camera.release();
                this.interrupt();
            }
        }
        System.out.println("Stop daemon!");
    }
}
