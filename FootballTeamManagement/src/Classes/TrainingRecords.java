/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
public class TrainingRecords {
    private ArrayList<TrainingSession> trainingList;
    private int count;

    public TrainingRecords() {
        trainingList=new ArrayList<TrainingSession>();
        count=0;
    }
    public void addSession(int req, playerList list){
        for(int i=0; i<req; i++)
        {
            TrainingSession TS=new TrainingSession();
            TS.recordSession(list);
            trainingList.add(TS);
            count++;
        }
    }
    public void addSession(TrainingSession ses){
            trainingList.add(ses);
    }
    public void viewHistory(){
        System.out.printf("%10s%13s%11s%21s|%n", "ID", "Date", "Location", "Topic");
        for(int i=0; i<count; i++)
        {
            trainingList.get(i).printSessionRecord();
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
    }
}