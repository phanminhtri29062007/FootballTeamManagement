/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author phanm
 */
public class Reportation {
    public static void printAllTimeScorer(){
        ArrayList<Long> scorer=new ArrayList<>();
        HashMap<Long, Integer> scoringTable;
        scoringTable=new HashMap<>();
        for(Performance perf : PerfomanceLog.getLog()){
            if(scorer.contains(perf.getPlayerID()))
                scoringTable.put(perf.getPlayerID(), scoringTable.get(perf.getPlayerID())+perf.getGoals());
            else {
                scorer.add(perf.getPlayerID());
                scoringTable.put(perf.getPlayerID(), perf.getGoals());
            }
        }
        //bubble sort
        for (int i = 0; i < scorer.size() - 1; i++) {
            for (int j = 0; j < scorer.size() - i - 1; j++) {
                Long id1 = scorer.get(j);
                Long id2 = scorer.get(j + 1);

                if (scoringTable.get(id1) < scoringTable.get(id2)) {
                    // swap IDs
                    scorer.set(j, id2);
                    scorer.set(j + 1, id1);
                }
            }
        }
        for (Long id : scorer) {
            System.out.println("PlayerID: " + id + " Goals: " + scoringTable.get(id));
        }
    }
}
