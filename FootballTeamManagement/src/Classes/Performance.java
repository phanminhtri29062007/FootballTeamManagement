package Classes;

import java.util.Scanner;
import java.time.LocalDate;
public class Performance {
    private long playerID;
    private String matchID;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int minutesPlayed;
    private LocalDate time;

    public Performance() {
        this.playerID = -1;
        this.matchID = "";
        this.goals = 0;
        this.assists = 0;
        this.yellowCards = 0;
        this.redCards = 0;
        this.minutesPlayed = 0;
    }

    public Performance(long playerID, String matchID, int goals, int assists,
                              int yellowCards, int redCards, int minutesPlayed) {
        this.playerID = playerID;
        this.matchID = matchID;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutesPlayed = minutesPlayed;
    }
    public LocalDate getTime() {return time;}
    public long getPlayerID() { return playerID; }
    public String getMatchID() { return matchID; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getYellowCards() { return yellowCards; }
    public int getRedCards() { return redCards; }
    public int getMinutesPlayed() { return minutesPlayed; }
    
    // BR24
    public int calculatePerformancePoints() {
        int points = goals * 5 + assists * 3 - yellowCards * 1 - redCards * 3;
        return Math.max(points, 0);
    }
    // BR19-21 validation
    public boolean setStats(String matchID, long playerID, int goals, int assists, int yellowCards, int redCards, int minutesPlayed, LocalDate time) {
        if (goals < 0 || assists < 0 || yellowCards < 0 || redCards < 0 || minutesPlayed < 0)
            return false;
        if (minutesPlayed > 120)
            return false;
        if (minutesPlayed == 0 && (goals != 0 || assists != 0 || yellowCards != 0 || redCards != 0))
            return false;
        this.matchID=matchID;
        this.playerID=playerID;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutesPlayed = minutesPlayed;
        this.time = time;
        return true;
    }
    
    public void updatePerformance(String matchID, LocalDate time) {
        Scanner sc = new Scanner(System.in);
        boolean validity = false;

        System.out.println("\n----------- ENTER PLAYER PERFORMANCE STATS -----------");

        // 2. Gather Player ID using your pattern
        do {
            System.out.print("Enter Player ID: ");
            try {
                this.playerID = sc.nextLong();
                sc.nextLine(); // Clear trailing newline
                validity = true;
            } catch (Exception e) {
                System.out.println("Error: Player ID must be a numeric long value!");
                sc.nextLine(); // Clear the bad data token from buffer
                validity = false;
            }
        } while (!validity);

        // 3. Main input loop for match statistics
        do {
            int tmpGoals = 0, tmpAssists = 0, tmpYellow = 0, tmpRed = 0, tmpMinutes = 0;

            try {
                System.out.print("Goals: ");
                tmpGoals = sc.nextInt();
                sc.nextLine();

                System.out.print("Assists: ");
                tmpAssists = sc.nextInt();
                sc.nextLine();

                System.out.print("Yellow Cards: ");
                tmpYellow = sc.nextInt();
                sc.nextLine();

                System.out.print("Red Cards: ");
                tmpRed = sc.nextInt();
                sc.nextLine();

                System.out.print("Minutes Played: ");
                tmpMinutes = sc.nextInt();
                sc.nextLine();

                // Evaluate the inputs using your existing rules in setStats
                validity = setStats(this.matchID, this.playerID, tmpGoals, tmpAssists, tmpYellow, tmpRed, 
                          tmpMinutes, time);

                if (!validity) {
                    System.out.println("\n[Validation Error] Input values violated business rules:");
                    if (tmpGoals < 0 || tmpAssists < 0 || tmpYellow < 0 || tmpRed < 0 || tmpMinutes < 0) {
                        System.out.println("- Statistical fields cannot be negative values.");
                    }
                    if (tmpMinutes > 120) {
                        System.out.println("- Minutes played cannot exceed 120 minutes.");
                    }
                    if (tmpMinutes == 0 && (tmpGoals != 0 || tmpAssists != 0 || tmpYellow != 0 || tmpRed != 0)) {
                        System.out.println("- BR21 Link Rule: If Minutes Played is 0, all match statistics must be 0.");
                    }
                    System.out.println("Please re-enter all statistics correctly.\n");
                }

            } catch (Exception e) {
                System.out.println("Error: Invalid character detected. All numerical stat counters must be integers.\n");
                sc.nextLine(); // Clear out character noise so loop doesn't spin infinitely
                validity = false;
            }

        } while (!validity);

        System.out.println(">>> Performance statistics recorded successfully! <<<");
    }
}