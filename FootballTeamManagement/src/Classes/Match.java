package Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Match {
    // Khai báo các thuộc atính private để đảm bảo tính đóng gói (Encapsulation)
    private String id;
    private String homeTeam;
    private String opponent;
    private String venue;
    private LocalDate matchDate;
    private int homeScore;
    private int opponentScore;
    Performance perf;
    // Using has-
    private PlayerList teamRoster;

    // CONSTRUCTOR WITHOUT PARAMETER
    public Match() {
        this.id = "Unknown";
        this.homeTeam = "Unknown";
        this.opponent = "Unknown";
        this.venue = "Unknown";
        this.matchDate = null;
        this.homeScore = 0;      
        this.opponentScore = 0;
        this.teamRoster = new PlayerList();
        this.perf=null;
    }

    // CONSTRUCTOR WITH PARAMETER
    public Match(String id, String homeTeam, String opponent, String venue, LocalDate matchDateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.opponent = opponent;
        this.venue = venue;
        this.matchDate = matchDateTime;
        this.homeScore = 0;
        this.opponentScore = 0;
        this.teamRoster = new PlayerList();
        this.perf=null;
    }
    // ================= GETTERS =================
    public String getId() { return id; }
    public String getHomeTeam() { return homeTeam; }
    public String getOpponent() { return opponent; }
    public String getVenue() { return venue; }
    public LocalDate getMatchDate() { return matchDate; }
    public int getHomeScore() { return homeScore; }
    public int getOpponentScore() { return opponentScore; }
    public PlayerList getTeamRoster() { return teamRoster; }
    public Performance getPerf() {return perf;}
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

    public boolean setMatchDateTime(LocalDate matchDateTime) {
        if (matchDateTime == null) return false; 
        this.matchDate = matchDate;
        return true;
    }

    public boolean setScores(int homeScore, int opponentScore) {
        if (homeScore < 0 || opponentScore < 0) return false;
        this.homeScore = homeScore;
        this.opponentScore = opponentScore;
        return true;
    }
    public void setperf(Performance perf)
    {
        this.perf=perf;
    }
    public String getMatchDateString(){
        
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd, mm, yyyy");
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
            System.out.print("Enter Match Date: ");
            int d, m, y;
            d=sc.nextInt();
            m=sc.nextInt();
            y=sc.nextInt();
            validity = setMatchDateTime(LocalDate.of(y, m, d));
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
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd, mm, yyyy");
        System.out.println("Match Time Begin: " + getMatchDate().format(customFormat) + " tại: " + getVenue());
    }

    public void ChangeKickOff(LocalDate newDate) {
        if (setMatchDateTime(newDate)) {
            DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd, mm, yyyy");
            System.out.println("Time Competition Change To: " + matchDate.format(customFormat));
        }
    }

    public void TeamInformation() {
        System.out.println("=== PlayerList Enter A Match " + id + " ===");
        teamRoster.printAllPlayer(); 
    }
    
    public void enterPerf(ArrayList<Player> list){
        if(getPerf()==null){
            setperf(new Performance());
            getPerf().updatePerformance(getId(), getMatchDate());
            Long ID=getPerf().getPlayerID();
            Player p=helperFunctions.findPlayer(ID);
            p.addPerformanceLog(getPerf());
        }
        else{
            Long ID=getPerf().getPlayerID();
            Player p=helperFunctions.findPlayer(ID);
            p.getPerformance().remove(getPerf());
            System.out.println("Overwriting Match Performance Record:");
            getPerf().updatePerformance(getId(), getMatchDate());
            ID=getPerf().getPlayerID();
            p=helperFunctions.findPlayer(ID);
            p.addPerformanceLog(getPerf());
        }
    }
}