package footballteammanagement;

import Classes.PlayerList;
import Classes.TrainingRecords;
import Classes.MatchList;

public class FootballTeamManagement {    
    
    public static void init(PlayerList team, TrainingRecords tr) {
        System.out.println("Loading database files...");
        team.loadFromFile();
        tr.loadFromFile();
    }
    
    public static void term(PlayerList team, TrainingRecords tr) {
        System.out.println("Saving database files...");
        team.saveToFile();
        tr.saveToFile();
    }
    
    public static void main(String[] args) {
        // 1. Instantiate your core databases and managers
        PlayerList team = new PlayerList();
        TrainingRecords tr = new TrainingRecords();
        MatchList matchManager = new MatchList();

        System.out.println("=================================================");
        System.out.println("      STARTING FOOTBALL MANAGEMENT SYSTEM        ");
        System.out.println("=================================================");

        // 2. Load existing players and records from your text files
        init(team, tr);

        // 3. Launch the Match Management Menu we just built!
        // This will keep running in a loop until you select '0' to exit.
        matchManager.menu();

        // 4. Once you press '0' and the menu loop finishes, save all changes back to the text files
        System.out.println("\n=================================================");
        System.out.println("   SHUTTING DOWN SYSTEM - VERIFYING SAVE DATA    ");
        System.out.println("=================================================");
        term(team, tr);
        
        System.out.println("System runtime state safely archived. Goodbye!");
    }
}