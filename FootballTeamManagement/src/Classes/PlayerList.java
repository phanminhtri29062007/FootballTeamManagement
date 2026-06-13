/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author phanm
 */
public class PlayerList {
    ArrayList<Player> list;
    int activeCount;

    public PlayerList() {
        this.list= new ArrayList<>();
        activeCount=0;
    }
    public PlayerList(ArrayList<Player> list, int activeCount) {
        this.list = list;
        this.activeCount = activeCount;
    }
    public ArrayList<Player> getList() {
        return list;
    }

    public int getActiveCount() {
        return activeCount;
    }
    public boolean updateActiveCount(int i){
        int tmp=activeCount+i;
        if(tmp>100||tmp<0) return false;
        this.activeCount=tmp;
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
            validity=p.setPlayerID(tmpid, list);
            if(!validity) System.out.print("Duplicated id, please re-enter: ");
        } while (!validity);
        p.enterPlayerInfo(tmpid, list, updateActiveCount(1));
        list.add(p);
        activeCount++;
        System.out.println("Add player successfully.");
        return 0;
    }

    //UPDATE
    public int updatePlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID to update: ");
        long id = sc.nextLong();
        int position=helperFunctions.findPlayer(id, getList());
        if(position==-1){
            System.err.println("ID not found, exiting update!");
            return 0;
        }
        list.get(position).enterPlayerInfo(id, list, true);
        return 1;
    }

    //DEACTIVATE & activate
    public int deactivatePlayer(long id) {
        int found = helperFunctions.findPlayer(id, getList());
        if (found==-1) {
            System.out.println("Player not found.");
            return -1;
        }
        else{
            list.get(found).setStatus(false);
            return 0;
        }
    }
    public int activatePlayer(long id){
        int found = helperFunctions.findPlayer(id, list);
        Player p= list.get(found);
        if(helperFunctions.checkSNavailability(p.getShirtNumber(), list)){
            p.setStatus(true);
            System.out.println("Player activated successfully");
            return 0;
        }
        System.err.println("Shirt number unavailable");
        return -1;
    }
    //PRINT ALL
    void printAllPlayer() {
        if (list.isEmpty()) {
            System.out.println("No players in list.");
            return;
        }

        for (Player p : list) {
            if (p.isStatus()) {
                p.printPlayer();
                System.out.println("------------------");
            }
        }
    }
        //PRINT BY ID
    void printPlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID: ");
        long id = sc.nextLong();

        boolean found = false;

        for (Player p : list) {
            if (p.getPlayerID() == id && p.isStatus()) {
                p.printPlayer();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }
    
}
