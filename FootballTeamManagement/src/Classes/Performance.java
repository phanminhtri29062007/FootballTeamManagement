package Classes;

public class Performance {
    private long playerID;
    private int matchID;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int minutesPlayed;

    public Performance() {
        this.playerID = -1;
        this.matchID = -1;
        this.goals = 0;
        this.assists = 0;
        this.yellowCards = 0;
        this.redCards = 0;
        this.minutesPlayed = 0;
    }

    public Performance(long playerID, int matchID, int goals, int assists,
                              int yellowCards, int redCards, int minutesPlayed) {
        this.playerID = playerID;
        this.matchID = matchID;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutesPlayed = minutesPlayed;
    }

    public long getPlayerID() { return playerID; }
    public int getMatchID() { return matchID; }
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
    public boolean setStats(int matchID, long playerID, int goals, int assists, int yellowCards, int redCards, int minutesPlayed) {
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
        return true;
    }
}