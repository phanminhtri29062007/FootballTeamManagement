
package footballteammanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainingSession {
    private long trainingID;
    private int[] date = new int[3];
    private String location;
    private String topic;
    private ArrayList<Long> presentID = new ArrayList<>();
    TrainingSession(){
        trainingID=-1;
        date=new int[]{1,1,1};
        location="Unknown";
        topic="Unknown";
    }
    /*
    TrainingSession(long id, int[] Date, String loc, String top)
    {
        boolean ch;
        setID(id);
        setDate(Date[0], Date[1], Date[2]);
        setLocation(loc);
        setTopic(top);
    }
    */
    boolean setID(long id){
        if(id>0&&id<1000000000)
        {
            trainingID=id;
            return true;
        }
        return false;
    }
    long getID(){
        return trainingID;
    }
    boolean setDate(int d, int m, int y){
        if(y>0)
            {if(m>0&&m<=12){
                if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
                    if(d>0&&d<=31){
                        date[0]=d;
                        date[1]=m;
                        date[2]=y;
                        return true;
                    }
                }
                else if(m==2){
                    if(d>0&&d<=28){
                        date[0]=d;
                        date[1]=m;
                        date[2]=y;
                        return true;
                    }
                }
                else{
                    if(d>0&&d<=30){
                        date[0]=d;
                        date[1]=m;
                        date[2]=y;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    int getDay() {
    return date[0];
    }
    int getMonth() {
        return date[1];
    }
    int getYear() {
        return date[2];
    }
    String getLocation(){
        return location;
    }
    boolean setLocation(String loc){
        if(loc.length()<=10)
            location=loc;
        else return false;
        return true;
    }
    String getTopic(){
        return topic;
    }
    void setTopic(String top){
        if(top.length()<=10)
            topic=top;
    }
    void recordSession(){
        long id;
        int d, m, y;
        String loc, top;
        Scanner sc= new Scanner(System.in);
        do{
        System.out.println("Enter Session ID:");
            id=sc.nextLong();
            if(!setID(id))
                System.out.println("Invalid ID, please re-enter!");
        }while(!setID(id));
        do{
        System.out.println("Enter date:");
            d=sc.nextInt();
            m=sc.nextInt();
            y=sc.nextInt();
            if(!setDate(d,m,y))
                System.out.println("Invalid date, please re-enter!");
        }while(!setDate(d,m,y));
        setLocation(FootballTeamManagement.inputString("Enter location: "));
        setTopic(FootballTeamManagement.inputString("Enter topic: "));
        System.out.printf("Session ID %d recorded successfully%n", trainingID);
    }
    void printSessionRecord(){
        System.out.printf("%010d|%4d/%02d/%04d|%10s|%10s%n", getID(), getDay(), getMonth(), getYear(), getLocation(), getTopic());
    }
}

class trainingRecords {
    ArrayList<TrainingSession> trainingList;
    int count;
    void addSession(int req){
        for(int i=count; i<req; i++)
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
