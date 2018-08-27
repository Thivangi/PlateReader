
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class openCVTest {

    public openCVTest() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        while(true){
            String url = "http://138.118.33.201/mjpg/video.mjpg?timestamp=1535125345478";
            VideoCapture camera = new VideoCapture(url);
            String path = "resultados/";

            if (camera.isOpened()) {
                System.out.println("Video is captured");
            } else {
                System.out.println("Video closed");
            }

            videoCamera cam = new videoCamera(camera);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(cam);
            

            if(camera.isOpened()){
                frame.setSize(800, 600);
                frame.setVisible(true);
                ThreadDetection thread = new ThreadDetection(url, path);
                thread.start();
                while (camera.isOpened()) {
                    cam.repaint();
                }
                frame.setVisible(false);
                System.out.println("Stop video stream!");
            }
            System.out.println("Reintentando...");
        }
    }
}
