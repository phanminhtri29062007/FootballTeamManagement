
package footballteammanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainingSession {
    long trainingID;
    int[] date= new int[3];
    String location;
    String topic;
    ArrayList<Long> presentID;

    void recordSession(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Session ID:");
        trainingID=sc.nextLong();
        System.out.println("Enter date:");
        date[0]=sc.nextInt();
        date[1]=sc.nextInt();
        date[2]=sc.nextInt();
        System.out.println("Enter location:");
        sc.nextLine();
        location=sc.nextLine();
        System.out.println("Enter topic:");
        topic=sc.nextLine();
        System.out.printf("Session ID %d recorded successfully%n", trainingID);
    }
    void printSessionRecord(){
        System.out.printf("%010d%4d/%02d/%04d%10s%10s%n", trainingID, date[0], date[1], date[2], location, topic);
    }
}

class trainingRecords {
    ArrayList<TrainingSession> trainingList;
    int count;
    void addSession(int req){
        for(int i=0; i<req; i++)
        {
            TrainingSession TS=new TrainingSession();
            TS.recordSession();
            trainingList.add(TS);
            count++;
        }
    }
    void viewHistory(){
        System.out.printf("%10s%12s%10s%10s%n", "ID", "Date", "Location", "Topic");
        for(int i=0; i<count; i++)
        {
            trainingList.get(i).printSessionRecord();
        }
    }
}
