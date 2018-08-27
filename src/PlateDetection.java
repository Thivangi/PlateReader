import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprResults;

public class PlateDetection {

    public static String readPlate(String imagedata) throws Exception {
        String country = "br", configfile = "src/openalpr.conf", runtimeDataDir = "src/runtime_data";

        Alpr alpr = new Alpr(country, configfile, runtimeDataDir);

        alpr.setTopN(10);
        alpr.setDefaultRegion("br");
        AlprResults results = alpr.recognize(imagedata);  
        alpr.unload();
        return results.getPlates().get(0).getTopNPlates().get(0).getCharacters() + " - " + results.getPlates().get(0).getTopNPlates().get(0).getOverallConfidence();
        
    }
}
