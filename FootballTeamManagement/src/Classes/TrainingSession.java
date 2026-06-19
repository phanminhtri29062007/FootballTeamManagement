package Classes;
import java.time.LocalDate;
import footballteammanagement.FootballTeamManagement;
import java.util.ArrayList;
import java.util.Scanner;
public class TrainingSession {
    private long trainingID;
    private int[] date = new int[3];
    private String location;
    private String topic;
    private ArrayList<Long> presentID;
    public TrainingSession(){
        trainingID=-1;
        date=new int[]{1,1,1};
        location="Unknown";
        topic="Unknown";
        presentID= new ArrayList<>();
    }
    public TrainingSession(long id, int d, int m, int y, String loc, String top, ArrayList<Long> presentID) {
    setID(id);
    setDate(d, m, y);
    setLocation(loc);
    setTopic(top);
        setPresentList(presentID);
}
    public boolean setID(long id){
        if(id>0&&id<1000000000)
        {
            trainingID=id;
            return true;
        }
        return false;
    }
    public boolean setID(long id, ArrayList<TrainingSession> existing){
        if(id<=0 || id>=1000000000) return false;
        for(int i=0; i<existing.size(); i++){
            if(existing.get(i).getID()==id) return false;
        }
        trainingID=id;
        return true;
    }
    public long getID(){
        return trainingID;
    }
    public boolean setDate(int d, int m, int y){
        if(y>=2000&&y<=2100)
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
    private ArrayList<Long> recordAbsence(ArrayList<Player> lis){
        Scanner sc= new Scanner(System.in);
        int num;
        int ACount=0;
        for(Player p : lis){
            if(p.isStatus()) ACount++;
        }
        do{
        System.out.println("Enter absence number:");
        num=sc.nextInt();
        if(num>ACount) System.err.println("Exceeded number of active players!");
        if(num<0) System.err.println("Must be >=0!");
        }while(num>ACount || num<0);
        ArrayList<Long> absenceID = new ArrayList<>();
        if(num<=0) return absenceID;
        long playerID;
        boolean valid;
        do{
            absenceID = new ArrayList<>();
            valid=true;
            System.out.printf("Enter %d IDs of absent players:\n", num);
            for (int i = 0; i < num; i++) {
                playerID = sc.nextLong();
                if (helperFunctions.findPlayer(playerID, lis)==-1) {
                    System.err.printf("Player ID:%d not found! Please re-enter.\n", playerID);
                    valid = false;
                    break;
                } else if (absenceID.contains(playerID)) {
                    System.err.printf("Player ID:%d is duplicate! Please re-enter.\n", playerID);
                    valid = false;
                    break;
                } else {
                    absenceID.add(playerID);
                }
            }
        }while(!valid);
        return absenceID;
    }
    private ArrayList<Long> getPresentList(ArrayList<Long> absenceID, ArrayList<Player> lis) {
        ArrayList<Long> presentID = new ArrayList<>();
        for (Player p : lis) {
            if (p.isStatus()&& !absenceID.contains(p.getPlayerID())) {
                presentID.add(p.getPlayerID());
            }
        }
        return presentID;
    }
    public void setPresentList(ArrayList<Long> list)
    {
        this.presentID=list;
    }
    public ArrayList<Long> getPresence()
    {
        return presentID;
    }
    public void recordSession(ArrayList<Player> lis, ArrayList<TrainingSession> existing){
        long id;
        int d, m, y;
        boolean valid;
        Scanner sc= new Scanner(System.in);
        do {
            System.out.println("Enter Session ID:");
            id = sc.nextLong();
            valid = setID(id, existing);
            if (!valid)
                System.out.println("Invalid or duplicate ID, please re-enter!");
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
        valid=setLocation(helperFunctions.inputString("Enter location(max 10 chars): "));
        if(!valid)
                System.out.println("Location name too long, please re-enter!");
        }while(!valid);
        do{
        valid=setTopic(helperFunctions.inputString("Enter topic(max 20 chars): "));
        if(!valid)
                System.out.println("Topic too long, please re-enter!");
        }while(!valid);
        setPresentList(getPresentList(recordAbsence(lis), lis));
        System.out.printf("Session ID %d recorded successfully%n", trainingID);
    }
    public void printSessionRecord(){
        System.out.printf("%010d|%4d/%02d/%04d|%10s|%20s|%n", getID(), getDay(), getMonth(), getYear(), getLocation(), getTopic());
    }
}