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
    public void addSession(int req){
        for(int i=0; i<req; i++)
        {
            TrainingSession TS=new TrainingSession();
            TS.recordSession();
            trainingList.add(TS);
            count++;
        }
    }
    public void viewHistory(){
        System.out.printf("%10s%13s%11s%21s|%n", "ID", "Date", "Location", "Topic");
        for(int i=0; i<count; i++)
        {
            trainingList.get(i).printSessionRecord();
        }
    }
}