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
public class helperFunctions {
        static Scanner sc=new Scanner(System.in);
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
    //Player lookup
    public static int findPlayer(long id, ArrayList<Player> list) {
    for (int i=0; i<list.size(); i++) {
        if (list.get(i).getPlayerID() == id)
            return i;
    }
    return -1;
    }
    public static ArrayList<Player> findPlayers(Object parameter, ArrayList<Player> list, int choice){
        switch (choice){
            case 1:
                return findName((String) parameter, list);
            case 2:
                return findPosition((String) parameter, list);
            case 3:
                return findNationality((String)parameter, list);
            case 4:
                return findStatus((boolean)parameter, list);
        }
        return null;
    }
    public static ArrayList<Player> findName(String name, ArrayList<Player> list){
        ArrayList<Player> pa=new ArrayList<>();
        for(Player p : list){
            if(p.getFullName().contains(name)) pa.add(p);
        }
        return pa;
    };
    public static boolean checkSNavailability(int shirtNum, ArrayList<Player> list){
        for(Player p : list){
            if(p.getShirtNumber()==shirtNum && p.isStatus()){
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Player> findStatus(boolean stat, ArrayList<Player> list) {
        ArrayList<Player> pa=new ArrayList<>();
        for(Player p : list){
            if(p.isStatus()==stat) pa.add(p);
        }
        return pa;
    }

    private static ArrayList<Player> findNationality(String nat, ArrayList<Player> list) {
        ArrayList<Player> pa=new ArrayList<>();
        for(Player p : list){
            if(p.getNationality().contains(nat)) pa.add(p);
        }
        return pa;
    }

    private static ArrayList<Player> findPosition(String pos, ArrayList<Player> list) {
        ArrayList<Player> pa=new ArrayList<>();
        for(Player p : list){
            if(p.getPosition().equals(pos)) pa.add(p);
        }
        return pa;
    }
    
    public static void printList(ArrayList<Long> list, String prompt) {
        System.out.println(prompt);
        for(Long i : list)
        {
            System.out.print(i+"   ");
        }
    }
}
