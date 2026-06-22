package Classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class MatchList {
    private final Match[] arr = new Match[100];
    private int count = 0;
    
    public void addManyMatches() {            
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n--- CREATE A NEW MATCH (MATCH: " + (count + 1) + ") ---");
            Match tmp = new Match();
            
            tmp.inputMatchDetails(); 
            
            arr[count] = tmp;
            count++;
            
            System.out.println("\n>>> Match created! Use Menu Option 7 to assign players to Team 1 or Team 2. <<<");

            System.out.print("CREATE ANOTHER MATCH? (true/false): ");
            try {
                cont = sc.nextBoolean();
            } catch (Exception e) {
                cont = false;
            }
            sc.nextLine(); 
        } while(cont);
    }

    public void displayALL() {
        if (count == 0) {
            System.out.println("THERE ARE NO MATCHES.");
            return;
        }
        System.out.println("\n=== PRINT ALL THE MATCHES ===");
        for(int i = 0; i < count; i++) {
            arr[i].MatchInformation();
        }
    }
  
    public void displayMatchRosters() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Match ID to view rosters: ");
        String matchId = sc.nextLine().trim();
        
        Match targetMatch = searchMatchById(matchId);
        if (targetMatch == null) {
            System.err.println("Error: Match ID '" + matchId + "' not found!");
            return;
        }
        targetMatch.TeamInformation(); 
    }

    public int searchPositionById(String id) {
       for(int i = 0; i < count; i++) {
          if(arr[i].getId().equalsIgnoreCase(id)) {  
             return i;
          }
       }
       return -1;
    }

    public Match searchMatchById(String id) {
       for(int i = 0; i < count; i++) {
          if(arr[i].getId().equalsIgnoreCase(id)) {  
             return arr[i];
          }
       }
       return null;
    }

    public void updateScoreById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter MatchID to update score: ");
        String id = sc.nextLine();
        
        Match targetMatch = searchMatchById(id);
        
        if (targetMatch != null) {
            System.out.println("Find Match: " + targetMatch.getHomeTeam() + " vs " + targetMatch.getOpponent());
            System.out.print("Update new score " + targetMatch.getHomeTeam() + ": ");
            int hScore = sc.nextInt();
            System.out.print("Update new score " + targetMatch.getOpponent() + ": ");
            int oScore = sc.nextInt();
            
            targetMatch.MatchLiveResult(hScore, oScore);
        } else {
            System.err.println("Error.Can't find match id: " + id);
        }
    }

    public Match removeMatch(String id) {
       Match result = null;
       int pos = searchPositionById(id);
       
       if(pos != -1) {
          result = arr[pos]; 
          for(int i = pos; i < count - 1; i++) {
              arr[i] = arr[i + 1];
          } 
          arr[count - 1] = null;
          count--;
          System.out.println("Successfully Deleted Match: " + id);
       } else {
           System.err.println("Error:Can't find match to delete");
       }
       return result;
    }

    public void exportTeamToFile() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Match ID to export: ");
        Match m = searchMatchById(sc.nextLine().trim()); 
        
        if (m == null) {
            System.out.println("Error: Match ID not found!");
            return;
        }
       
        System.out.print("Enter file name (Press Enter to default to '" + m.getId() + ".txt'): ");
        String fileName = sc.nextLine().trim();
        if (fileName.isEmpty()) {
            fileName = m.getId() + ".txt"; 
        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./FootballTeamManagement/Datas/"+fileName, false)))) {
            
            pw.printf("===== MATCH =====\nMã trận: %s\n: %s vs %s\n: %s\nTime: %s\nResult: %d - %d\n",
                    m.getId(), m.getHomeTeam(), m.getOpponent(), m.getVenue(), m.getMatchDateString(), m.getHomeScore(), m.getOpponentScore());
            
            pw.println("-----------------------------");
            pw.println("PLAYERS IN THE MATCH:");
            
            pw.println("\n--- " + m.getHomeTeam() + " (HOME TEAM) ---");
            for (Player p : m.getHomeSquad()) {
                if (p.isStatus()) {
                    pw.printf("- ID: %d | Name: %s | Position: %s | Number: %d\n", 
                              p.getPlayerID(), p.getFullName(), p.getPosition(), p.getShirtNumber());
                }
            }

            pw.println("\n--- " + m.getOpponent() + " (OPPONENT TEAM) ---");
            for (Player p : m.getOpponentSquad()) {
                if (p.isStatus()) {
                    pw.printf("- ID: %d | Name: %s | Position: %s | Number: %d\n", 
                              p.getPlayerID(), p.getFullName(), p.getPosition(), p.getShirtNumber());
                }
            }
            
            System.out.println("Successfully exported to: " + fileName);
            
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    public void addTeamToGlobalRoster() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHow many players do you want to add to the global roster? ");
        try {
            int countToRegister = sc.nextInt();
            sc.nextLine(); 
            
            PlayerList globalRoster = new PlayerList();
            for (int i = 0; i < countToRegister; i++) {
                System.out.println("\n--- Registering New Player (" + (i + 1) + " of " + countToRegister + ") ---");
                globalRoster.addPlayer(); 
            }
            System.out.println("\n>>> Bulk registration complete! Players saved to active workspace database. <<<");
        } catch (Exception e) {
            System.err.println("Error: Invalid number entry.");
            sc.nextLine();
        }
    }

    public void addPlayerToSpecificMatchTeam() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Match ID: ");
        String matchId = sc.nextLine().trim();
        
        Match targetMatch = searchMatchById(matchId);
        if (targetMatch == null) {
            System.err.println("Error: Match ID '" + matchId + "' not found!");
            return;
        }

        System.out.println("Match Selected: " + targetMatch.getHomeTeam() + " vs " + targetMatch.getOpponent());
        System.out.println("Select Target Team Destination:");
        System.out.println("1. Team 1: " + targetMatch.getHomeTeam() + " (Home)");
        System.out.println("2. Team 2: " + targetMatch.getOpponent() + " (Opponent)");
        System.out.print(">>> Choose destination (1-2): ");
        
        int teamSelection;
        try {
            teamSelection = sc.nextInt();
            sc.nextLine(); 
        } catch (Exception e) {
            System.err.println("Invalid selection format.");
            sc.nextLine();
            return;
        }

        System.out.print("Enter Player ID to look up from roster: ");
        long playerLookupId;
        try {
            playerLookupId = sc.nextLong();
            sc.nextLine(); 
        } catch (Exception e) {
            System.err.println("Invalid ID format.");
            sc.nextLine();
            return;
        }

        Player activePlayer = helperFunctions.findPlayer(playerLookupId);
        if (activePlayer == null) {
            System.err.println("Error: Player ID " + playerLookupId + " does not exist in your active database!");
            return;
        }

        if (teamSelection == 1) {
            targetMatch.getHomeSquad().add(activePlayer);
            System.out.println(">>> Success: " + activePlayer.getFullName() + " added to " + targetMatch.getHomeTeam() + " (Team 1) <<<");
        } else if (teamSelection == 2) {
            targetMatch.getOpponentSquad().add(activePlayer);
            System.out.println(">>> Success: " + activePlayer.getFullName() + " added to " + targetMatch.getOpponent() + " (Team 2) <<<");
        } else {
            System.err.println("Invalid choice. Operation aborted.");
        }
    }

    public void recordMatchPerformances() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Match ID to record performances: ");
        String matchId = sc.nextLine().trim();
        
        Match targetMatch = searchMatchById(matchId);
        if (targetMatch == null) {
            System.err.println("Error: Match ID '" + matchId + "' not found!");
            return;
        }
        
        targetMatch.recordTeamPerformances();
    }

    public void saveToFile() {
        File dir = new File("./FootballTeamManagement/Datas");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("./FootballTeamManagement/Datas/MatchListData.txt", false))) {
            pw.println(count); 
            for (int i = 0; i < count; i++) {
                Match m = arr[i];
                pw.println(m.getId());
                pw.println(m.getHomeTeam());
                pw.println(m.getOpponent());
                pw.println(m.getVenue());
                pw.println(m.getMatchDate().getDayOfMonth() + " " + m.getMatchDate().getMonthValue() + " " + m.getMatchDate().getYear());
                pw.println(m.getHomeScore() + " " + m.getOpponentScore());
                
                pw.print(m.getHomeSquad().size());
                for (Player p : m.getHomeSquad()) pw.print(" " + p.getPlayerID());
                pw.println();

                pw.print(m.getOpponentSquad().size());
                for (Player p : m.getOpponentSquad()) pw.print(" " + p.getPlayerID());
                pw.println();
            }
            System.out.println("Match records saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving match records: " + e.getMessage());
        }
    }


    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n=============================================");
            System.out.println("=== MATCH MANAGEMENT SYSTEM ===");
            System.out.println("=============================================");
            System.out.println("1. Add a new match layout");
            System.out.println("2. Display all matches (Summary)");
            System.out.println("3. Update match score by ID");
            System.out.println("4. Remove a match by ID");
            System.out.println("5. Export match details to file (.txt)");
            System.out.println("6. Bulk register a full team to current database"); 
            System.out.println("7. Assign roster player to specific Match Team");   
            System.out.println("8. Record player performances for a Match"); 
            System.out.println("9. View specific Match Rosters on screen"); 
            System.out.println("0. Exit program");
            System.out.println("=============================================");
            System.out.print(">>> Please select an option (0-9): "); 
            
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Error: Please enter a valid number!");
                choice = -1;
                continue; 
            }

            switch (choice) {
                case 1:
                    this.addManyMatches();
                    break;
                case 2:
                    this.displayALL(); 
                    break;
                case 3:
                    this.updateScoreById();
                    break;
                case 4:
                    System.out.print("\nEnter Match ID to remove: ");
                    String idToRemove = sc.nextLine();
                    this.removeMatch(idToRemove);
                    break;
                case 5:
                    this.exportTeamToFile();
                    break;
                case 6:
                    this.addTeamToGlobalRoster();
                    break;
                case 7:
                    this.addPlayerToSpecificMatchTeam();
                    break;
                case 8:
                    this.recordMatchPerformances();
                    break;
                case 9:
                    this.displayMatchRosters(); 
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a number between 0 and 9.");
            }
        } while (choice != 0); 
    }
}