package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phanm
 */
public class PlayerList {
    private static ArrayList<Player> list;
    private static int activeCount;

    public PlayerList() {
        this.list= new ArrayList<>();
        activeCount=0;
    }
    public PlayerList(ArrayList<Player> list, int activeCount) {
        this.list = list;
        this.activeCount = activeCount;
    }
    public static ArrayList<Player> getList() {
        return list;
    }
    public static int getActiveCount() {
        return activeCount;
    }
    public static boolean updateActiveCount(int i){
        int tmp=activeCount+i;
        if(tmp>100||tmp<0) return false;
        activeCount=tmp;
        return true;
    }
    //ADD PLAYER
    public int addPlayer() {
        Scanner sc=new Scanner(System.in);
        Player p = new Player();
        long tmpid;
        boolean validity;
        System.out.print("Enter ID (-1 to go back): ");
        do {
            
            tmpid = sc.nextLong();
            if(tmpid==-1){
                System.out.println("Exited successfully!");
                return 1;
            }
            validity=p.setPlayerID(tmpid);
            if(!validity) System.out.print("Duplicated id, please re-enter: ");
        } while (!validity);
        System.out.println("Enter player type:");
        System.out.println("1-Normal\t2-Star");
        sc=new Scanner(System.in);
        int pt;
        do {
            pt=sc.nextInt();
            if(pt==1) p= new NormalPlayer(p);
            else if(pt==2) p= new StarPlayer(p);
        } while (pt!=1&&pt!=2);
        p.enterPlayerInfo(tmpid, list, updateActiveCount(1));
        list.add(p);
        if(p.isStatus()) activeCount++;
        System.out.println("Added player successfully.");
        return 0;
    }

    //UPDATE
    public int updatePlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID to update: ");
        long id = sc.nextLong();
        Player target = helperFunctions.findPlayer(id);
        if(target == null){
            System.err.println("ID not found, exiting update!");
            return 0;
        }
        target.enterPlayerInfo(id, list, true);
        return 1;
    }

    //DEACTIVATE & activate
    public int deactivatePlayer(long id) {
        Player target = helperFunctions.findPlayer(id);
        if (target == null) {
            System.out.println("Player not found.");
            return -1;
        }
        else{
            target.setStatus(false);
            return 0;
        }
    }
    public int activatePlayer(long id){
        Player target = helperFunctions.findPlayer(id);
        if(target == null){
            System.out.println("Player not found.");
            return -1;
        }
        if(helperFunctions.checkSNavailability(target.getShirtNumber())){
            target.setStatus(true);
            System.out.println("Player activated successfully");
            return 0;
        }
        System.err.println("Shirt number unavailable");
        return -1;
    }
    //PRINT ALL
    public static void printAllPlayer() {
        if (list.isEmpty()) {
            System.out.println("No players in list.");
            return;
        }

        for (Player p : list) {
            if (/*p.isStatus()*/true) {
                p.printPlayer();
                System.out.println("------------------");
            }
        }
    }
        //PRINT BY ID
    public static void printPlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID: ");
        long id = sc.nextLong();

        boolean found = false;

        for (Player p : list) {
            if (p.getPlayerID() == id) {
                p.printPlayer();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }
    /**
 * Saves all players currently in the list to .\Datas\PlayerListData.txt
 * Matches the file format logic used in your TrainingRecords class.
 */
public void saveToFile() {
    // Ensure the Datas directory exists
    File directory = new File(".\\Datas");
    if (!directory.exists()) {
        directory.mkdirs();
    }

    try (PrintWriter pw = new PrintWriter(new FileWriter(".\\FootballTeamManagement\\Datas\\PlayerListData.txt"))) {
        // Write the total size first so the loader knows how many entries to loop through
        pw.println(list.size());

        for (Player p : list) {
            pw.println(p.getPlayerID());
            pw.println(p.getFullName());
            pw.println(p.getAge());
            pw.println(p.getNationality());
            pw.println(p.getPosition());
            pw.println(p.getShirtNumber());
            pw.println(p.getBaseSalary());
            pw.println(p.isStatus()); // Saves as true/false
            if(p instanceof StarPlayer) DriverManager.println("1");
            else DriverManager.println("0");
        }
        System.out.println("Player records saved successfully.");
    }   catch (IOException ex) {
            Logger.getLogger(PlayerList.class.getName()).log(Level.SEVERE, null, ex);
        }}

public void loadFromFile() {
    File file = new File(".\\FootballTeamManagement\\Datas\\PlayerListData.txt");
    if (!file.exists()) {
        System.out.println("No existing player data file found. Starting fresh.");
        return;
    }

    try (Scanner fileScanner = new Scanner(file)) {
        list.clear(); // Clear memory before loading
        this.activeCount = 0;

        if (!fileScanner.hasNextLine()) return;
        
        int totalPlayers = Integer.parseInt(fileScanner.nextLine().trim());
        
        for (int i = 0; i < totalPlayers; i++) {
            if (!fileScanner.hasNextLine()) break;

            // Read consecutive fields from text block sequence
            long id = Long.parseLong(fileScanner.nextLine().trim());
            String name = fileScanner.nextLine().trim();
            int age = Integer.parseInt(fileScanner.nextLine().trim());
            String nationality = fileScanner.nextLine().trim();
            String position = fileScanner.nextLine().trim();
            int shirtNumber = Integer.parseInt(fileScanner.nextLine().trim());
            float baseSalary = Float.parseFloat(fileScanner.nextLine().trim());
            boolean status = Boolean.parseBoolean(fileScanner.nextLine().trim());
            // Reconstruct the Player object using your parameterized constructor
            // Passing an empty ArrayList for the performance log (plog) initially
            int type=Integer.parseInt(fileScanner.nextLine().trim());
            Player p = new Player(id, name, age, nationality, position, shirtNumber, baseSalary, status, new ArrayList<>());
            if(type==0) p= new StarPlayer(p);
            else p=new NormalPlayer(p);
            list.add(p);
            
            if (status) {
                this.activeCount++;
            }
        }
        System.out.println("Player records loaded successfully. Total players: " + list.size());
    } catch (FileNotFoundException e) {
        System.err.println("Save file not found: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error loading player records: " + e.getMessage());
    }
}
}
