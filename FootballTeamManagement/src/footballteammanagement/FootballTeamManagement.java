
package footballteammanagement;

import java.util.ArrayList;

public class FootballTeamManagement {
    public static void main(String[] args) {
        System.out.println("Hi");
        trainingRecords tList= new trainingRecords();
        tList.trainingList=new ArrayList<>();
        tList.count=0;
        tList.addSession(2);
        tList.viewHistory();
    }
    
}
