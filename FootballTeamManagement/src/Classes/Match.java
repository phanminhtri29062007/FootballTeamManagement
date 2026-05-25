package Classes;
import java.time.LocalDateTime;
import java.util.Scanner;
        
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguyenlongvu
 */
//use private data type for encapsulation//
public class Match {
       private int MatchID;
       private String HomeTeam;
       private String OpponentTeam;
       private String Venue;
       private LocalDateTime MatchDateTime;
       private int HomeScore;
       private int OpponentScore;
        void MatchDetailsInput(){
            MatchDateTime = LocalDateTime.now();
            Scanner sc = new Scanner(System.in);
            System.out.println("MatchID:");
             if(sc.hasNextInt()){ //If input is integer ( valid )
            MatchID = sc.nextInt();
            sc.nextLine();
             } else{ // if input is not integer ( return invalid and we need to input again )
                 System.out.println("Invalid Match ID");
                 sc.nextLine();
             }//This fix consumes leftover "Enter" key//
            System.out.println("HomeTeam:");
             HomeTeam = sc.nextLine();
            if(HomeTeam.isEmpty()){
            System.out.println("Team Name Cannot Be Empty");
             HomeTeam = sc.nextLine();
            } 
            System.out.println("OpponentTeam:");
            OpponentTeam = sc.nextLine();
            //EqualsIgnoreCase methods compare input between opponentTeam and HomeTeam, if OpponentTeam has same name with HomeTeam ( refuse to receive that input )
             while (OpponentTeam.isEmpty() || OpponentTeam.equalsIgnoreCase(HomeTeam)) {
            if(OpponentTeam.isEmpty()){
                System.out.println("TeamName Cannot Be Empty");
            }
            else{
                System.out.println("Opponent Cannot Be The Same With HomeTeam");
            }
            OpponentTeam = sc.nextLine();
            sc.nextLine();
             }
            System.out.println("Venue:");
            Venue = sc.nextLine();
            if(Venue.isEmpty()){
                System.out.println("Venue Cannot Be Empty");
               Venue = sc.nextLine();
            }
             //Use LocalDateTime object for input date and time for match details input
            System.out.println("--Enter Match Date & Time--");
            System.out.println("Enter Day:");
            int day = sc.nextInt();
            while (day < 1 || day > 31){
                    System.out.println("Day Declined");
                    day = sc.nextInt();
            }
            System.out.println("Enter Month:");
            int month = sc.nextInt();
            while (month < 1 || month > 12){
                System.out.println(" Month Declined");
                month = sc.nextInt();
            }
           
            System.out.println("Enter Year:");
            int year = sc.nextInt();
            while (year < 2000 || year > 2100){
                System.out.println("Year Declined");
                year = sc.nextInt();
            }          
            System.out.println("Enter Hour:");
            int hour = sc.nextInt();
            while (hour < 0 || hour > 23){
                System.out.println("Hour Declined");
                hour = sc.nextInt();
            }
            System.out.println("Enter Minute:");
            int minute = sc.nextInt();
            while (minute < 0|| minute > 59){
                System.out.println("Minute Declined");
                minute = sc.nextInt();
            }
            sc.nextLine();
            
            
                
            
           
}
        void UpdateLiveResult(){
          Scanner sc = new Scanner(System.in);
          System.out.println("----Update Live Score---");
          System.out.println("Current Score"+ HomeTeam+":");
          if(sc.hasNextInt()){
              HomeScore = sc.nextInt();
              sc.nextLine();
          }
          else{
              System.out.println("Cannot Update Score For HomeTeam");
              sc.nextLine();
          }
          
       
          System.out.println("Current Score"+OpponentTeam+":");
          if(sc.hasNextInt()){
              OpponentScore = sc.nextInt();
              sc.nextLine();
          }
          else{
              System.out.println("Cannot Update Score For OpponentTeam");
          }         
        }
    void MatchInformation(){
          System.out.format("%d | %20s:%d vs %20s:%d |%20s| %td/%tm/%ty %tl.%tM %tp %n", 
                MatchID, HomeTeam,HomeScore,OpponentTeam,OpponentScore, Venue, 
                MatchDateTime, MatchDateTime, MatchDateTime, MatchDateTime, MatchDateTime, MatchDateTime);
    }
    
}
class Schedule{
        void ScheduleDetails(){
            
        }
        void ChangeSchedules(){
            
        }
}
class Team{
    
    void TeamInformation(){
        
    }
}
