package Classes;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class TrainingRecords {
    private ArrayList<TrainingSession> trainingList;
    
    public TrainingRecords() {
        trainingList=new ArrayList<>();
    }

    public ArrayList<TrainingSession> getTrainingList() {
        return trainingList;
    }
    public void addSession(int req, ArrayList<Player> list){
        for(int i=0; i<req; i++)
        {
            TrainingSession TS=new TrainingSession();
            TS.recordSession(list, trainingList );
            trainingList.add(TS);
        }
    }
    public void addSession(TrainingSession ses){
            trainingList.add(ses);
    }
    public void viewHistory(){
        System.out.printf("%10s%13s%11s%21s|%n", "ID", "Date", "Location", "Topic");
        for(TrainingSession i : getTrainingList())
        {
            i.printSessionRecord();
        }
    }
    public void printSessionDetails(long sesID){
        int i=0;
        for(i=0; i<trainingList.size()&&sesID!=trainingList.get(i).getID(); i++){}
        System.out.printf("%10s%13s%11s%21s|%n", "ID", "Date", "Location", "Topic");
        System.out.printf("%010d|%4d/%02d/%04d|%10s|%20s|%n", trainingList.get(i).getID(), trainingList.get(i).getDay(), trainingList.get(i).getMonth(), trainingList.get(i).getYear(), trainingList.get(i).getLocation(), trainingList.get(i).getTopic());
        System.out.println("Presence ID:");
        for(int j=0; j<trainingList.get(i).getPresence().size(); j++)
        {
            System.out.printf("%d\t",trainingList.get(i).getPresence().get(j));
        }
        System.out.println("");
    }
    public TrainingSession searchSession(long sesid) {
        for(TrainingSession s : getTrainingList())
            if(s.getID()==sesid) return s;
        return null;
    }
    public void saveToFile() {
    File dir = new File("./FootballTeamManagement/Datas");
    if (!dir.exists()) {
        dir.mkdirs();
    }

    try (PrintWriter pw = new PrintWriter(new FileWriter("./FootballTeamManagement/Datas/TrainingRecordsData.txt", false))) 
    {
        pw.println(trainingList.size());
        for (TrainingSession ts : trainingList) {
            pw.println(ts.getID());
            pw.printf("%d %d %d%n", ts.getDay(), ts.getMonth(), ts.getYear());
            pw.println(ts.getLocation());
            pw.println(ts.getTopic());
            ArrayList<Long> presence = ts.getPresence();
            pw.print(presence.size());
            for (Long id : presence) {
                pw.print(" " + id);
            }
            pw.println();
        }
        System.out.println("Training records saved successfully.");
    } catch (IOException e) {
        System.err.println("Error saving training records: " + e.getMessage());
    }
}

public void loadFromFile() {
    try (Scanner sc = new Scanner(new File("./FootballTeamManagement/Datas/TrainingRecordsData.txt"))) {
        trainingList.clear();

        int total = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < total; i++) {
            long id = Long.parseLong(sc.nextLine().trim());
            String[] dateParts = sc.nextLine().trim().split(" ");
            int d = Integer.parseInt(dateParts[0]);
            int m = Integer.parseInt(dateParts[1]);
            int y = Integer.parseInt(dateParts[2]);
            String loc = sc.nextLine().trim();
            String top = sc.nextLine().trim();

            String[] presenceParts = sc.nextLine().trim().split(" ");
            int presenceCount = Integer.parseInt(presenceParts[0]);
            ArrayList<Long> presenceList = new ArrayList<>();
            for (int j = 1; j <= presenceCount; j++) {
                presenceList.add(Long.parseLong(presenceParts[j]));
            }

            TrainingSession ts = new TrainingSession(id, d, m, y, loc, top, presenceList);
            trainingList.add(ts);
        }
        System.out.println("Training records loaded successfully.");
    } catch (FileNotFoundException e) {
        System.err.println("Save file not found: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error loading training records: " + e.getMessage());
    }
}

}