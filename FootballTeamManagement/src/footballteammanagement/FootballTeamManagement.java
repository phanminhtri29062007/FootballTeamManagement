
package footballteammanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class FootballTeamManagement {
    static Scanner sc = new Scanner(System.in);
     static String normalizeString(String data){
         String normed1 = data.toLowerCase().replaceAll("[^a-z]", "-");
         boolean continuous=false;
         String normed;
         normed = "";
         int i;
         for(i=0; i<normed1.length()&&normed1.charAt(i)=='-'; i++){}
         while(i<normed1.length())
         {
             if(normed1.charAt(i)=='-')
             {
                 if(!continuous){
                     normed+='-';
                     continuous=true;
                 }
             }
             else{
                 continuous=false;
                 normed+=normed1.charAt(i);
             }
             i++;
         }
         if(normed.endsWith("-")) return normed.substring(0, normed.length()-1);
         return normed;
     }
     static String inputString(String prompt){
        String value;
        while(true){
            System.out.print(prompt);
            value = sc.nextLine().trim().toLowerCase();
            value = normalizeString(value);
            if(!value.isEmpty()){
                return value;
            }
            System.out.println("Input can't be empty.Please try again! ");
        }
     }

      static boolean isValidPosition(String position){
        return position.equals("goalkeeper")
        || position.equals("defender")
        || position.equals("midfielder")
        || position.equals("forward");
      }

      static int inputInt(String prompt, int min, int max){
        int value;
        while(true){
            System.out.print(prompt);
            try{ String input = sc.nextLine().trim();
                value = Integer.parseInt(input);
               if(value >= min && value <= max){
                return value;
               } 
               System.out.println("Value must between " + min + " and " + max );
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input.Please enter a whole number!");
            }
        }
      }

      static double inputSalary(String prompt){
        double value;
        while(true){
            System.out.print(prompt);
            try{
                String input = sc.nextLine();
                input = input.trim();
                value = Double.parseDouble(input);

                if(value > 0){
                    return value;
                }
                System.out.print("salary must be greater than 0.");
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input.Please enter a valid number!");
            }
        }
      }

    
    public static void main(String [] args) {
        
         System.out.println("\n=== RUNNING MATCH DETAILS ===");
        Match game1 = new Match();

        game1.MatchDetailsInput();

        System.out.println("Current Match List:");
        game1.MatchInformation();

        game1.UpdateLiveResult();

        System.out.println("Updated Match List:");
        game1.MatchInformation();
    }
    
}
