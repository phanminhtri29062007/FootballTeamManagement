package Classes;
import java.util.ArrayList;

/**
 *
 * @author phanm
 */
public class PerfomanceLog {
    private static ArrayList<Performance> Log = new ArrayList<>();

    
    private PerfomanceLog() {}

    public static ArrayList<Performance> getLog() {
        return Log;
    }
    
    public static void setLog(ArrayList<Performance> log){
        Log = log;
    }

    public static void addPerfEntry(Performance perf){
        Log.add(perf);
    }
}