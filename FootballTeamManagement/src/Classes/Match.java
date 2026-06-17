package Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Match {
    // Khai báo các thuộc atính private để đảm bảo tính đóng gói (Encapsulation)
    private String id;
    private String homeTeam;
    private String opponent;
    private String venue;
    private String matchDateTime;
    private int homeScore;
    private int opponentScore;
    
    // Using has-
    private PlayerList teamRoster;

    // CONSTRUCTOR WITHOUT PARAMETER
    public Match() {
        this.id = "Unknown";
        this.homeTeam = "Unknown";
        this.opponent = "Unknown";
        this.venue = "Unknown";
        this.matchDateTime = "Unknown";
        this.homeScore = 0;      
        this.opponentScore = 0;
        this.teamRoster = new PlayerList();
    }

    // CONSTRUCTOR WITH PARAMETER
    public Match(String id, String homeTeam, String opponent, String venue, String matchDateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.opponent = opponent;
        this.venue = venue;
        this.matchDateTime = matchDateTime;
        this.homeScore = 0;
        this.opponentScore = 0;
        this.teamRoster = new PlayerList();
    }

    // ================= GETTERS =================
    public String getId() { return id; }
    public String getHomeTeam() { return homeTeam; }
    public String getOpponent() { return opponent; }
    public String getVenue() { return venue; }
    public String getMatchDateTime() { return matchDateTime; }
    public int getHomeScore() { return homeScore; }
    public int getOpponentScore() { return opponentScore; }
    public PlayerList getTeamRoster() { return teamRoster; }

    // ================= SETTERS CÓ ĐIỀU KIỆN (VALIDATION) =================
    
    public boolean setId(String id) {
        if (id == null || id.trim().isEmpty()) return false; // IF EMPTY RETURN FALSE
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
        // OPPONENT NAME CAN NOT BE THE SAME WITH HOMETEAM
        if (opponent.equalsIgnoreCase(this.homeTeam)) return false; 
        this.opponent = opponent;
        return true;
    }

    public boolean setVenue(String venue) {
        if (venue == null || venue.trim().isEmpty()) return false;
        this.venue = venue;
        return true;
    }

    public boolean setMatchDateTime(String matchDateTime) {
        if (matchDateTime == null || matchDateTime.trim().isEmpty()) return false; 
        this.matchDateTime = matchDateTime;
        return true;
    }

    public boolean setScores(int homeScore, int opponentScore) {
        if (homeScore < 0 || opponentScore < 0) return false;
        this.homeScore = homeScore;
        this.opponentScore = opponentScore;
        return true;
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
            System.out.print("Enter MatchDateTime: ");
            String tmpTime = sc.nextLine();
            validity = setMatchDateTime(tmpTime);
            if (!validity) System.out.println("Error: Match Time Can't Be Empty!");
        } while (!validity);
        
        System.out.println(">>> Save Match Information Successfully! <<<\n");
    }

    public void inputPlayerForMatch() {
        System.out.println("--- PlayerList Add To A Match ---");
        teamRoster.addPlayer(); 
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

    public void ScheduleDetails() {
        System.out.println("Match Time Begin: " + matchDateTime + " tại: " + venue);
    }

    public void ChangeKickOff(String newDateTime) {
        if (setMatchDateTime(newDateTime)) {
            System.out.println("Time Competition Change To: " + matchDateTime);
        }
    }

    public void TeamInformation() {
        System.out.println("=== PlayerList Enter A Match " + id + " ===");
        teamRoster.printAllPlayer(); 
    }
}