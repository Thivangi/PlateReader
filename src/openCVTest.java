
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
        VideoCapture camera = new VideoCapture("http://138.118.33.201/mjpg/video.mjpg?timestamp=1535125345478");

        if (camera.isOpened()) {
            System.out.println("Video is captured");
        } else {
            System.out.println("");
        }
        videoCamera cam = new videoCamera(camera);
        JLabel label = new JLabel();
        label.contains(1000, 100);
        label.setText("PATENTE:");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cam);
        //frame.add(label);
        frame.setSize(1280, 720);
        frame.setVisible(true);

        while (camera.isOpened()) {
            cam.repaint();
        }

    }

}
