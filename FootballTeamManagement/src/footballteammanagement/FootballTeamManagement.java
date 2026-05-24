
package footballteammanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class FootballTeamManagement {
    public static void main(String[] args) {

        System.out.println("\n=== RUNNING MATCH DETAILS ===");
        // Step 1: Create a new Match object
        Match game1 = new Match();

        // Step 2: Ask the user to input the details
        game1.MatchDetailsInput();

        // Step 3: Print the details so far (Scores will be 0 - 0)
        System.out.println("Current Match List:");
        game1.MatchInformation();

        // Step 4: Ask the user to update the live scores
        game1.UpdateLiveResult();

        // Step 5: Print the list again to see the updated scores!
        System.out.println("Updated Match List:");
        game1.MatchInformation();
    }
} // Notice there i