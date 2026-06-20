/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author phanm
 */
public class NormalPlayer extends Player{
    public NormalPlayer(Player existingPlayer) {
        super(
            existingPlayer.getPlayerID(),
            existingPlayer.getFullName(),
            existingPlayer.getAge(),
            existingPlayer.getNationality(),
            existingPlayer.getPosition(),
            existingPlayer.getShirtNumber(),
            existingPlayer.getBaseSalary(),
            existingPlayer.isStatus(),
            new ArrayList<Performance>()
        );
    }

    public NormalPlayer(long playerID, String fullName, int age, String nationality, String position, int shirtNumber, float baseSalary, boolean status, ArrayList<Performance> log) {
        super(playerID, fullName, age, nationality, position, shirtNumber, baseSalary, status, log);
    }
    
    @Override
    public float calculateSalary(){
        return super.getBaseSalary();
    }
    
    @Override
    void printPlayer(){
        super.printPlayer();
        System.out.println("Type: Regular Player");
    }
}
