
package Classes;

import footballteammanagement.FootballTeamManagement;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainingSession {
    private long trainingID;
    private int[] date = new int[3];
    private String location;
    private String topic;
    private ArrayList<Long> presentID = new ArrayList<>();
    public TrainingSession(){
        trainingID=-1;
        date=new int[]{1,1,1};
        location="Unknown";
        topic="Unknown";
    }
    public TrainingSession(long id, int d, int m, int y, String loc, String top) {
    setID(id);
    setDate(d, m, y);
    setLocation(loc);
    setTopic(top);
}
    public boolean setID(long id){
        if(id>0&&id<1000000000)
        {
            trainingID=id;
            return true;
        }
        return false;
    }
    public long getID(){
        return trainingID;
    }
    public boolean setDate(int d, int m, int y){
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
    public int getDay() {
    return date[0];
    }
    public int getMonth() {
        return date[1];
    }
    public int getYear() {
        return date[2];
    }
    public String getLocation(){
        return location;
    }
    public boolean setLocation(String loc){
        if(loc.length()<=10)
            location=loc;
        else return false;
        return true;
    }
    public String getTopic(){
        return topic;
    }
    public boolean setTopic(String top){
        if(top.length()<=20)
        {topic=top; return true;}
        return false;
    }
    public void recordSession(){
        long id;
        int d, m, y;
        String loc, top;
        boolean valid;
        Scanner sc= new Scanner(System.in);
        do {
            System.out.println("Enter Session ID:");
            id = sc.nextLong();
            valid = setID(id);
            if (!valid)
                System.out.println("Invalid ID, please re-enter!");
        } while (!valid);
        do{
        System.out.println("Enter date(dd mm yyyy):");
            d=sc.nextInt();
            m=sc.nextInt();
            y=sc.nextInt();
            valid=setDate(d,m,y);
            if(!valid)
                System.out.println("Invalid date, please re-enter!");
        }while(!valid);
        do{
        valid=setLocation(FootballTeamManagement.inputString("Enter location(max 10 chars): "));
        if(!valid)
                System.out.println("Location name too long, please re-enter!");
        }while(!valid);
        do{
        valid=setTopic(FootballTeamManagement.inputString("Enter topic(max 20 chars): "));
        if(!valid)
                System.out.println("Topic too long, please re-enter!");
        }while(!valid);
        System.out.printf("Session ID %d recorded successfully%n", trainingID);
    }
    public void printSessionRecord(){
        System.out.printf("%010d|%4d/%02d/%04d|%10s|%20s|%n", getID(), getDay(), getMonth(), getYear(), getLocation(), getTopic());
    }
}
