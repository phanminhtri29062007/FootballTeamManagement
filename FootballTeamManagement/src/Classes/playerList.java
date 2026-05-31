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
public class playerList {
    ArrayList<Player> list = new ArrayList<>();
    int count = 0;

    //ADD PLAYER
    void addPlayer() {
        Player p = new Player();
        p.enterPlayer();
        list.add(p);
        count++;
        System.out.println("Add player successfully.");
    }

    public ArrayList<Player> getList() {
        return list;
    }
    //ADD MANY PLAYERS
    void addManyPlayer() {
        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        do {
            Player p = new Player();
            p.enterPlayer();
            list.add(p);
            count++;

            System.out.println("Add more? (true|false)");
            cont = sc.nextBoolean();

        } while (cont);

        System.out.println("Finish adding players.");
    }
    
    //UPDATE
    void updatePlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID to update: ");
        long id = sc.nextLong();
        sc.nextLine();

        boolean found = false;

        for (Player p : list) {
            if (p.playerID == id && p.status == true) {

                System.out.print("Enter new full name: ");
                p.fullName = sc.nextLine();

                System.out.print("Enter new age: ");
                p.age = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter new nationality: ");
                p.nationality = sc.nextLine();

                System.out.print("Enter new position: ");
                p.position = sc.nextLine();

                System.out.print("Enter new shirt number: ");
                p.shirtNumber = sc.nextInt();

                System.out.print("Enter new base salary: ");
                p.baseSalary = sc.nextInt();

                System.out.print("Enter new player type: ");
                p.playerType = sc.nextInt();

                System.out.println("Update successful.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }

    //DEACTIVATE
    void deactivatePlayer() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID to deactivate: ");
        long id = sc.nextLong();

        boolean found = false;

        for (Player p : list) {
            if (p.playerID == id && p.status == true) {
                p.status = false;
                System.out.println("Deactivate successful.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }

    //PRINT ALL
    void printAllPlayer() {
        if (list.isEmpty()) {
            System.out.println("No players in list.");
            return;
        }

        for (Player p : list) {
            if (p.status == true) {
                p.printPlayer();
                System.out.println("------------------");
            }
        }
    }

    //SEARCH BY NAME
    void searchPlayer() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Player p : list) {
            if (p.fullName.toLowerCase().contains(name.toLowerCase())
                    && p.status == true) {
                p.printPlayer();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Player not found.");
        }
    }
    //search by id
    public boolean findPlayer(long id) {
    for (Player p : list) {
        if (p.playerID == id)
            return true;
    }
    return false;
}
    //PRINT BY ID
    void printPlayerInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player ID: ");
        long id = sc.nextLong();

        boolean found = false;

        for (Player p : list) {
            if (p.playerID == id && p.status == true) {
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
