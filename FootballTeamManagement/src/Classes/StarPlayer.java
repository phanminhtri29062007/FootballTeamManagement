/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author phanm
 */
public class StarPlayer extends Player{
    public StarPlayer(Player existingPlayer) {
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
    @Override
    public float calculateSalary(){
        return super.getBaseSalary();
    }
    public float calculateSalary(LocalDate x){
        return calcMonthlyBonus(x)+super.getBaseSalary();
    }
    public int calcMonthlyPPoint(LocalDate x){
        int ppoint=0;
        if(super.getPerformance()!=null)
        {for(Performance perf : super.getPerformance())
        {
            if(x.getYear()==perf.getTime().getYear() && x.getMonthValue()==perf.getTime().getMonthValue())
                ppoint+=perf.calculatePerformancePoints();
        }}
        return ppoint;
    }
    public int calcMonthlyBonus(LocalDate x){
        return calcMonthlyPPoint(x)*500000;
    }
    @Override
    void printPlayer(){
        super.printPlayer();
        System.out.println("Type: star Player");
    }
}
