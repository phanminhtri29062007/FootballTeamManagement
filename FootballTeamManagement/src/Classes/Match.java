package Classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Match {
    private String id;
    private String homeTeam;
    private String opponent;
    private String venue;
    private LocalDate matchDate;
    private int homeScore;
    private int opponentScore;
    Performance perf;
    
    private ArrayList<Player> homeSquad;      // Team 1
    private ArrayList<Player> opponentSquad;  // Team 2

    // CONSTRUCTOR WITHOUT PARAMETERS
    public Match() {
        this.id = "Unknown";
        this.homeTeam = "Unknown";
        this.opponent = "Unknown";
        this.venue = "Unknown";
        this.matchDate = null;
        this.homeScore = 0;      
        this.opponentScore = 0;
        this.homeSquad = new ArrayList<>();
        this.opponentSquad = new ArrayList<>();
        this.perf = null;
    }

    // CONSTRUCTOR WITH PARAMETERS
    public Match(String id, String homeTeam, String opponent, String venue, LocalDate matchDateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.opponent = opponent;
        this.venue = venue;
        this.matchDate = matchDateTime;
        this.homeScore = 0;
        this.opponentScore = 0;
        this.homeSquad = new ArrayList<>();
        this.opponentSquad = new ArrayList<>();
        this.perf = null;
    }

    // ================= GETTERS FOR BOTH TEAMS =================
    public ArrayList<Player> getHomeSquad() { return homeSquad; }
    public ArrayList<Player> getOpponentSquad() { return opponentSquad; }
    
    public String getId() { return id; }
    public String getHomeTeam() { return homeTeam; }
    public String getOpponent() { return opponent; }
    public String getVenue() { return venue; }
    public LocalDate getMatchDate() { return matchDate; }
    public int getHomeScore() { return homeScore; }
    public int getOpponentScore() { return opponentScore; }
    public Performance getPerf() { return perf; }

    // ================= SETTERS =================
    public boolean setId(String id) {
        if (id == null || id.trim().isEmpty()) return false; 
        this.id = id;
        return true;
    }

    public boolean setHomeTeam(String homeTeam) {
        if (homeTeam == null || homeTeam.trim().isEmpty()) return false;
        this.homeTeam = homeTeam;
        return true;
    }

    public boolean setOpponent(String opponent) {
        if (opponent == null || opponent.trim().isEmpty()) return false;
        if (opponent.equalsIgnoreCase(this.homeTeam)) return false; 
        this.opponent = opponent;
        return true;
    }

    public boolean setVenue(String venue) {
        if (venue == null || venue.trim().isEmpty()) return false;
        this.venue = venue;
        return true;
    }

    public boolean setMatchDateTime(LocalDate matchDateTime) {
        if (matchDateTime == null) return false; 
        this.matchDate = matchDateTime;
        return true;
    }

    public boolean setScores(int homeScore, int opponentScore) {
        if (homeScore < 0 || opponentScore < 0) return false;
        this.homeScore = homeScore;
        this.opponentScore = opponentScore;
        return true;
    }

    public String getMatchDateString(){
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd, MM, yyyy");
        return this.matchDate.format(customFormat);
    }

    public void inputMatchDetails() {
        Scanner sc = new Scanner(System.in);
        boolean validity;

        System.out.println("====== MATCH INFORMATION ======");

        do {
            System.out.print("Enter MatchID: ");
            String tmpId = sc.nextLine();
            validity = setId(tmpId);
            if (!validity) System.out.println("Error: MatchID Can't Be Empty.");
        } while (!validity);

        do {
            System.out.print("Enter Home Team: ");
            String tmpHome = sc.nextLine();
            validity = setHomeTeam(tmpHome);
            if (!validity) System.out.println("Error: HomeTeam Can't Be Empty!.");
        } while (!validity);

        do {
            System.out.print("Enter Opponent Team: ");
            String tmpOpp = sc.nextLine();
            validity = setOpponent(tmpOpp);
            if (!validity) System.out.println("Error: OpponentTeam Can't Be The Same Name With HomeTeam!");
        } while (!validity);

        do {
            System.out.print("Enter Venue: ");
            String tmpVenue = sc.nextLine();
            validity = setVenue(tmpVenue);
            if (!validity) System.out.println("Error: Venue Can't Be Empty");
        } while (!validity);

        do {
            System.out.print("Enter Match Date (dd mm yyyy): ");
            int d = sc.nextInt();
            int m = sc.nextInt();
            int y = sc.nextInt();
            validity = setMatchDateTime(LocalDate.of(y, m, d));
            if (!validity) System.out.println("Error: Match Time Can't Be Empty!");
        } while (!validity);
        
        System.out.println(">>> Save Match Information Successfully! <<<\n");
    }

    public void MatchInformation() {
        System.out.println("Match ID: " + id + " | " + homeTeam + " vs " + opponent);
    }

    public void MatchLiveResult(int homeScore, int opponentScore) {
        if (setScores(homeScore, opponentScore)) {
            System.out.println("Update Score: " + homeTeam + " " + this.homeScore + " - " + this.opponentScore + " " + opponent);
        } else {
            System.err.println("Error: Invalid Score (Can't smaller than 0)!");
        }
    }

    public void TeamInformation() {
        System.out.println("\n=== Players In Match " + id + " ===");
        
        System.out.println("--- " + homeTeam + " (HOME TEAM) ---");
        if (homeSquad.isEmpty()) System.out.println("No players assigned yet.");
        for (Player p : homeSquad) {
            p.printPlayer();
            System.out.println("------------------");
        }

        System.out.println("--- " + opponent + " (OPPONENT TEAM) ---");
        if (opponentSquad.isEmpty()) System.out.println("No players assigned yet.");
        for (Player p : opponentSquad) {
            p.printPlayer();
            System.out.println("------------------");
        }
    }
    
    public void recordTeamPerformances() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== RECORDING PERFORMANCES FOR MATCH " + id + " ===");

        // Record for Home Team
        System.out.println("\n--- " + homeTeam + " (HOME TEAM) ---");
        recordSquadStats(homeSquad, sc);

        // Record for Opponent Team
        System.out.println("\n--- " + opponent + " (OPPONENT TEAM) ---");
        recordSquadStats(opponentSquad, sc);

        System.out.println(">>> Finished recording match performances! <<<");
    }

    // Helper method to keep code clean and avoid repeating the loop twice
    private void recordSquadStats(ArrayList<Player> squad, Scanner sc) {
        if (squad == null || squad.isEmpty()) {
            System.out.println("No players found in this squad.");
            return;
        }

        for (Player p : squad) {
            if (p.isStatus()) {
                System.out.print("Record performance for " + p.getFullName() + " (ID: " + p.getPlayerID() + ")? (true/false): ");
                
                try {
                    boolean record = sc.nextBoolean();
                    if (record) {
                        Performance newPerf = new Performance();
                        newPerf.updatePerformance(p.getPlayerID(), this.id, this.matchDate);
                        
                        p.addPerformanceLog(newPerf);
                        PerfomanceLog.addPerfEntry(newPerf);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, skipping this player.");
                    sc.nextLine(); 
                }
            }
        }
    }
}