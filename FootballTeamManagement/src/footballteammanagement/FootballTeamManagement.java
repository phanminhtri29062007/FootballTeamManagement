
package footballteammanagement;
import Classes.Player;
import Classes.TrainingSession;
import Classes.Match;
import java.util.ArrayList;
import java.util.Scanner;
import Classes.TrainingRecords;
import java.util.Arrays;
import Classes.MatchList;
import Classes.PlayerList;
import Classes.helperFunctions;
public class FootballTeamManagement {    
    public static void init(PlayerList team, TrainingRecords tr)
    {
        team.loadFromFile();
        tr.loadFromFile();
    }
    public static void term(PlayerList team, TrainingRecords tr)
    {
        team.saveToFile();
        tr.saveToFile();
    }
    public static void main(String [] args) {
        // 1. Instantiate the list
    PlayerList team = new PlayerList();
    TrainingRecords tr= new TrainingRecords();
    int commandSelection = -1;
    init(team, tr);
    Scanner sc=new Scanner(System.in);
    System.out.println("=================================================");
    System.out.println("         TEST RUN: CAPTURING EXACTLY 2 PLAYERS   ");
    System.out.println("=================================================");

    // 2. Trigger console wizard input for Player #1
    System.out.println("\n[1/2] Please enter details for Player 1:");
    team.addPlayer();

    // 3. Trigger console wizard input for Player #2
    System.out.println("\n[2/2] Please enter details for Player 2:");
    team.addPlayer();

    System.out.println("\n=================================================");
    System.out.println("   REGISTRATION COMPLETE - VERIFYING SAVE DATA   ");
    System.out.println("=================================================");

    // 4. Print out both entries to verify uniqueness and parameters
    team.printAllPlayer();
    do {
        System.out.println("\n=============================================");
        System.out.println("   TRAINING SESSIONS CONTINUOUS TEST CONTROLLER");
        System.out.println("=============================================");
        System.out.println("1. Record and Add 1 New Training Session");
        System.out.println("2. Display All Logged Sessions (printAll)");
        System.out.println("3. Look Up Attendance Stats by Training ID");
        System.out.println("4. Force Save Current Workspace Cache to File");
        System.out.println("0. Save and Terminate Test Engine");
        System.out.println("=============================================");
        System.out.print(">>> Select action (0-4): ");

        commandSelection=sc.nextInt();
        sc.nextLine();
        switch (commandSelection) {
            case 1:
                System.out.println("\n--- Triggering addSession() Process ---");
                // Uses your console scan prompts to gather ID, Date, Location, and Topic strings
                tr.addSession(1, team.getList());
                break;

            case 2:
                System.out.println("\n--- Triggering printAll() Process ---");
                tr.viewHistory();
                break;

            case 3:
                System.out.println("\n--- Triggering printAttendance() Process ---");
                System.out.print("Enter target numeric Training ID to scan: ");
                try {
                    long lookupID = sc.nextLong();
                    sc.nextLine();
                    helperFunctions.printList(tr.searchSession(lookupID).getPresence(), "Present List:\n");
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Entry: Target Training ID must be numeric.");
                }
                break;

            case 4:
                System.out.println("\n--- Syncing Memory Cache directly to Storage ---");
                tr.saveToFile();
                break;

            case 0:
                System.out.println("\n--- Shutting Down Test Runner... Writing State Changes ---");
                tr.saveToFile();
                System.out.println("System runtime state safely archived. Goodbye!");
                break;

            default:
                System.out.println("Invalid selection option. Try again.");
                break;
        }

    } while (commandSelection != 0);
        term(team, tr);
    }
}