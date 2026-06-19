
package Classes;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nguyenlongvu
 */
public class MatchList{
    private final Match[] arr = new Match[100];
    private int count = 0;
    public void addManyMatches() {            
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n--- CREATE A NEW MATCH (MATCH: " + (count + 1) + ") ---");
            Match tmp = new Match();
            
            tmp.inputMatchDetails(); 
            
            System.out.print("DO YOU WANT TO INPUT PLAYERLIST? (true/false): ");
            boolean addPlayers = sc.nextBoolean();
            if (addPlayers) {
                boolean contPlayer = false;
                do {
                    tmp.inputPlayerForMatch();
                    System.out.print("ADD ANOTHER PLAYER TO A LIST? (true/false): ");
                    contPlayer = sc.nextBoolean();
                } while (contPlayer);
            }

            arr[count] = tmp;
            count++;
            
            System.out.print("CREATE ANOTHER MATCH? (true/false): ");
            cont = sc.nextBoolean();
        } while(cont);
    }

    public void displayALL() {
        if (count == 0) {
            System.out.println("THERE ARE NO MATCH.");
            return;
        }
        System.out.println("\n=== PRINT ALL THE MATCHES ===");
        for(int i = 0; i < count; i++) {
            arr[i].MatchInformation();
        }
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
            
            pw.printf("===== MATCH  =====\nMã trận: %s\n: %s vs %s\n: %s\nTime: %s\nResult: %d - %d\n",
                    m.getId(), m.getHomeTeam(), m.getOpponent(), m.getVenue(), m.getMatchDateTime(), m.getHomeScore(), m.getOpponentScore());
            
            pw.println("-----------------------------");
            pw.println("PLAYERLIST ENTER A MATCH:");
            
            
            for (Player p : m.getTeamRoster().getList()) {
                if (p.isStatus()) {
                    pw.printf("- ID: %d | Name: %s | Position: %s | : %d\n", 
                              p.getPlayerID(), p.getFullName(), p.getPosition(), p.getShirtNumber());
                }
            }
            System.out.println("Successfully exported to: " + fileName);
            
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
    
     public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n=============================================");
            System.out.println("=== MATCH MANAGEMENT SYSTEM ===");
            System.out.println("=============================================");
            System.out.println("1. Add a new match (with players)");
            System.out.println("2. Display all matches");
            System.out.println("3. Update match score by ID");
            System.out.println("4. Remove a match by ID");
            System.out.println("5. Export match details to file (.txt)");
            System.out.println("0. Exit program");
            System.out.println("=============================================");
            System.out.print(">>> Please select an option (0-5): ");
            
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
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a number between 0 and 5.");
            }
        } while (choice != 0); 
    }
}
