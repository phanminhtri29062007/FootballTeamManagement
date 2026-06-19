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
    public float calculateSalary(){
        return super.getBaseSalary();
    }
}
