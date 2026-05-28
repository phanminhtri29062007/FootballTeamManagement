
package footballteammanagement;
import Classes.Player;
import Classes.TrainingSession;
import Classes.Match;
import java.util.ArrayList;
import java.util.Scanner;

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
        
        System.out.println("\n=== RUNNING MATCH DETAILS ===");
        Match game1 = new Match();
        Match game2 = new Match();

        game1.MatchDetailsInput();
        game2.MatchDetailsInput();

        System.out.println("Current Match List:");
        game1.MatchInformation();
        game2.MatchInformation();
        System.out.println("Update Live Result");
        game1.UpdateLiveResult();
        game2.UpdateLiveResult();

        System.out.println("Updated Match List:");
        game1.MatchInformation();
        game2.MatchInformation();
        System.out.println("Schedule Details");
        // 1. Tạo đối tượng Schedule để quản lý lịch trình
        Schedule sche1 = new Schedule();
        Schedule sche2 = new Schedule();
        
        // Cập nhật trận đấu vào trong lịch trình
        sche1.matchObject = game1;
        sche2.matchObject = game2;

        sche1.ScheduleDetails();
        sche2.ScheduleDetails();
        
        System.out.println("Change Schedules");
        sche1.ChangeSchedules();
        sche2.ChangeSchedules();
        
        System.out.println("Team");
        // 2. Tạo đối tượng Team để quản lý đội bóng
        Team team1 = new Team();
        Team team2 = new Team();
        
        // Lưu ý: Tên hàm bạn viết trong class Team là TeamInformation(), không phải Team()
        team1.TeamInformation(); 
        team2.TeamInformation();
    }
