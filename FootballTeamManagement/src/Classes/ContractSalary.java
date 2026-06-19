/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.time.LocalDate;

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
    public static void SalaryReport(LocalDate date){
        System.out.println("----------- SALARY SUMMARY REPORT -----------");
        System.out.print("Month: "+date.getMonthValue()+"/"+date.getYear());
        System.out.println("");
        System.out.println("ID   Name               Type            Base Salary  Bonus     Total");
        System.out.println("---------------------------------------------------------------------");
        for(Player p : PlayerList.getList()){
            if(p.isStatus()){
                printSalaryInfo(p, date);
            }
        }
    }
    private static void printSalaryInfo(Player p, LocalDate date){
        if(p instanceof NormalPlayer)
        System.out.printf("%-05d %-20s %-15s %-14.1f %-10d %-14.1f%n",
                  (long)p.getPlayerID(), p.getFullName(), "Regular Player", (double)p.getBaseSalary(), 0, (double)((NormalPlayer) p).calculateSalary());
        else
            System.out.printf("%-05d %-20s %-15s %-14.1f %-10d %-14.1d%n",
                    (long)p.getPlayerID(), p.getFullName(), "Star Player", (double)p.getBaseSalary(), ((StarPlayer) p).calcMonthlyPPoint(date), (double)((StarPlayer)p).calculateSalary(date));
    };
}
