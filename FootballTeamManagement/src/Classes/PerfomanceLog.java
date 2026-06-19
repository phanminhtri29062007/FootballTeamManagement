/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.ArrayList;

/**
 *
 * @author phanm
 */
public class PerfomanceLog {
    private static ArrayList<Performance> Log;

    public PerfomanceLog() {
        Log=new ArrayList<>();
    }

    public PerfomanceLog(ArrayList<Performance> Log) {
        this.Log = Log;
    }

    public static ArrayList<Performance> getLog() {
        return Log;
    }
    
    public void setLog(ArrayList<Performance> log){
        this.Log=log;
    }
    public void addPerfEntry(Performance perf){
        Log.add(perf);
    }
}
