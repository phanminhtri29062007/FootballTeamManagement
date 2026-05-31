
package footballteammanagement;
import Classes.Player;
import Classes.TrainingSession;
import Classes.Match;
import Classes.playerList;
import java.util.ArrayList;
import java.util.Scanner;
import Classes.TrainingRecords;
import java.util.Arrays;
public class FootballTeamManagement {
    static Scanner sc = new Scanner(System.in);
    public static String normalizeString(String data){
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
    public static String inputString(String prompt){
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

     public static boolean isValidPosition(String position){
        return position.equals("goalkeeper")
        || position.equals("defender")
        || position.equals("midfielder")
        || position.equals("forward");
      }

      public static int inputInt(String prompt, int min, int max){
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

      public static double inputSalary(String prompt){
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
        playerList lis=new playerList();
        Player p1 = new Player(1, "John Smith", 22, "English", "Forward", 10, 25000000, 2, true);
        Player p2 = new Player(2, "Mike Brown", 24, "English", "Defender", 5, 18000000, 1, true);
        Player p3 = new Player(3, "Tom Clark", 28, "English", "Goalkeeper", 1, 20000000, 1, true);
        Player p4 = new Player(4, "Jake Lee", 30, "English", "Midfielder", 8, 22000000, 2, false);
        lis.getList().add(p1);
        lis.getList().add(p2);
        lis.getList().add(p3);
        lis.getList().add(p4);
        TrainingRecords recList= new TrainingRecords();
//        recList.addSession(1, lis);
        ArrayList<Long> present = new ArrayList<>(Arrays.asList(1L, 3L, 4L));
        TrainingSession ses = new TrainingSession(123, 1, 2, 3, "idk", "idk", present);
        recList.addSession(ses);
        recList.printSessionDetails(123);
}
}
