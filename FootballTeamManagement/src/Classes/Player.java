/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private long playerID;
    private String fullName;
    private int age;
    private String nationality;
    private String position;
    private int shirtNumber;
    private float baseSalary;
    private boolean status;
    public Player() {
    this.playerID = 0;
    this.fullName = "Unknown";
    this.age = 0;
    this.nationality = "Unknown";
    this.position = "Unknown";
    this.shirtNumber = 0;
    this.baseSalary = 0;
    this.status = false;
}
    
    public Player(long playerID, String fullName, int age, String nationality, String position, 
              int shirtNumber, int baseSalary, boolean status) {
        this.playerID = playerID;
        this.fullName = fullName;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.baseSalary = baseSalary;
        this.status = status;
    }
    //getters
    public long getPlayerID() {
    return playerID;}
    
    public String getFullName() {
        return fullName;}
    
    public int getAge() {
        return age;}
    
    public String getNationality() {
        return nationality;}
    
    public String getPosition() {
        return position;}
    
    public int getShirtNumber() {
        return shirtNumber;}
    
    public float getBaseSalary() {
        return baseSalary;}

    public boolean isStatus() {
        return status;
    }
    //input validation
    boolean validate(String s)
    {
        return !s.isEmpty();
    }
    //setters

    public boolean setPlayerID(long playerID, ArrayList<Player> list) {
        if(helperFunctions.findPlayer(playerID, list)==-1) return false;
        this.playerID = playerID;
        return true;
    }
    
    public boolean setFullName(String fullName) {
        if(!validate(fullName)) return false;
        this.fullName = fullName;
        return true;
    }
    
    public boolean setAge(int age) {
        if(age<16 || age>45) return false;
        this.age = age;
        return true;}
    
    public boolean setNationality(String nationality) {
        if(validate(nationality)) return false;
        this.nationality = nationality;
        return true;}
    
    public boolean setPosition(int positionIndex) {
        switch (positionIndex) {
            case 1:
                this.position = ("Goalkeeper");
                return true;
            case 2:
                this.position = ("Defender");
                return true;
            case 3:
                this.position = ("Midfielder");
                return true;
            case 4:
                this.position = ("Forward");
                return true;
            default:
                return false;
            }
    }
    
    public boolean setShirtNumber(int shirtNumber) {
        if(shirtNumber<1||shirtNumber>99) return false;
        this.shirtNumber = shirtNumber;
    return true;}
    
    public boolean setBaseSalary(float baseSalary) {
        if(baseSalary<=0.0) return false;
        this.baseSalary = baseSalary;
        return true;}
   
    public boolean setStatus(boolean status) {
        this.status = status;
        return true;
    }
    //ENTER PLAYER
    void enterPlayerInfo(Long ID, ArrayList<Player> list, boolean activate) {
        int tmpAge;
        int positionIndex;
        float tmpBaseSalary;

        Scanner sc = new Scanner(System.in);
        boolean validity;
        
        System.out.print("Enter full name: ");
        do{
            validity=setFullName(helperFunctions.inputString("Enter full name (max 20 chars: "));
        if(!validity)
                System.err.println("Name too long, please re-enter!");
        }while(!validity);
        sc = new Scanner(System.in);
        System.out.print("Enter age(16-45): ");
        do {
            tmpAge=sc.nextInt();
            validity=setAge(tmpAge);
            if(!validity) System.err.println("Invalid age, please re-enter(16-45):");
        } while (!validity);
        sc = new Scanner(System.in);

        System.out.print("Enter nationality: ");
        do{
        validity=setNationality(helperFunctions.inputString("Enter nationality (max 15 chars: "));
        }while(!validity);
        System.out.println("1-Goal keeper\t2-Defender\t3-Midfielder\t4Forward");
        System.out.print("Enter position (1-4): ");
        positionIndex=sc.nextInt();
        do{
            validity=setPosition(positionIndex);
            if(!validity)System.out.println("Invalid input, please re-enter position:");
        }while(!validity);
        System.out.print("Enter shirt number(1-99): ");
        do {
            validity=setShirtNumber(sc.nextInt());
            if(activate && validity) 
                activate=helperFunctions.checkSNavailability(getShirtNumber(), list);
            if(!validity) 
                System.err.println("Invalid shirt number, please re-enter(1-99):");
        } while (!validity);
        System.out.print("Enter base salary: ");
        do {
            tmpBaseSalary = sc.nextFloat();
            validity=setBaseSalary(tmpBaseSalary);
            if(!validity) System.err.println("Invalid salary, please re-enter:");
        } while (!validity);
        if(!activate){
            System.out.println("No more available shirt numbers left, status set to deactivated");
            setStatus(false);
        }
        else{
            System.out.println("Activate this player?(true/false)");
            do{
            try {
                validity=setStatus(sc.nextBoolean());
            } catch (Exception InputMismatchException) {
                validity=false;
                sc=new Scanner(System.in);
            }
            if(!validity) System.out.println("Input mismatch, please re-enter(true/false)");
            }while(!validity);
        }
    }

    //PRINT PLAYER
    void printPlayer() {
        System.out.println("ID: " + playerID);
        System.out.println("Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Nationality: " + nationality);
        System.out.println("Position: " + position);
        System.out.println("Shirt Number: " + shirtNumber);
        System.out.println("Salary: " + baseSalary);
        System.out.println("Status: " + status);
    }
}