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
public class ContractSalary {
    public static void displayPlayerType(Long ID){
        System.out.printf("Player Type: ");
        Player p;
        p=helperFunctions.findPlayer(ID);
        if(p instanceof StarPlayer) System.out.println("Star Player");
        else System.out.println("Normal Player");
    }
    public static void SalaryReport(){
        
    }
}
