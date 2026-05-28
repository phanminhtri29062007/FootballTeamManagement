package Classes;

import Classes.player; // Import danh sách cầu thủ từ package Classes của nhóm
import java.time.LocalDateTime;
import java.util.Scanner;

public class Match {
    // Thuộc tính private bảo đảm tính đóng gói (Encapsulation)
    private int MatchID;
    private String HomeTeam;
    private String OpponentTeam;
    private String Venue;
    private LocalDateTime MatchDateTime;
    private int HomeScore;
    private int OpponentScore;

    void MatchDetailsInput() {
        this.MatchDateTime = LocalDateTime.now();
        Scanner sc = new Scanner(System.in);
        
        // 1. Nhập và kiểm tra Match ID
        while (true) {
            System.out.println("MatchID:");
            if (sc.hasNextInt()) {
                this.MatchID = sc.nextInt();
                sc.nextLine(); // Nuốt phím Enter
                break;
            } else {
                System.out.println("Invalid Match ID! Must be a number.");
                sc.nextLine(); // Xóa bộ nhớ đệm lỗi
            }
        }

        // 2. Nhập và kiểm tra HomeTeam (Chỉ chữ và khoảng trắng, không nhận số hay ký tự lạ)
        while (true) {
            System.out.println("HomeTeam:");
            this.HomeTeam = sc.nextLine().trim();
            if (this.HomeTeam.isEmpty()) {
                System.out.println("Team Name Cannot Be Empty!");
            } else if (!this.HomeTeam.matches("^[a-zA-Z\\s]+$")) { // Force to enter only 
                System.out.println("Invalid Name! Team name cannot contain numbers or special characters.");
            } else {
                break;
            }
        }

        // 3. Nhập và kiểm tra OpponentTeam (Không trống, không chứa số, không trùng HomeTeam)
        while (true) {
            System.out.println("OpponentTeam:");
            this.OpponentTeam = sc.nextLine().trim();
            if (this.OpponentTeam.isEmpty()) {
                System.out.println("Team Name Cannot Be Empty!");
            } else if (!this.OpponentTeam.matches("^[a-zA-Z\\s]+$")) { // Ép buộc chỉ nhập chuỗi chữ
                System.out.println("Invalid Name! Team name cannot contain numbers or special characters.");
            } else if (this.OpponentTeam.equalsIgnoreCase(this.HomeTeam)) {
                System.out.println("Opponent Cannot Be The Same With HomeTeam!");
            } else {
                break;
            }
        }

        // 4. Nhập và kiểm tra Venue
        while (true) {
            System.out.println("Venue:");
            this.Venue = sc.nextLine().trim();
            if (this.Venue.isEmpty()) {
                System.out.println("Venue Cannot Be Empty!");
            } else {
                break;
            }
        }

        // 5. Nhập Ngày giờ thi đấu (Giữ nguyên logic kiểm tra của bạn)
        System.out.println("--Enter Match Date & Time--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        while (day < 1 || day > 31) {
            System.out.println("Day Declined");
            day = sc.nextInt();
        }

        System.out.println("Enter Month:");
        int month = sc.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Month Declined");
            month = sc.nextInt();
        }

        System.out.println("Enter Year:");
        int year = sc.nextInt();
        while (year < 2000 || year > 2100) {
            System.out.println("Year Declined");
            year = sc.nextInt();
        }

        System.out.println("Enter Hour:");
        int hour = sc.nextInt();
        while (hour < 0 || hour > 23) {
            System.out.println("Hour Declined");
            hour = sc.nextInt();
        }

        System.out.println("Enter Minute:");
        int minute = sc.nextInt();
        while (minute < 0 || minute > 59) {
            System.out.println("Minute Declined");
            minute = sc.nextInt();
        }
        sc.nextLine(); // Nuốt phím Enter cuối cùng

        this.MatchDateTime = LocalDateTime.of(year, month, day, hour, minute);
        System.out.println("Match recorded successfully!\n");
    }

    void UpdateLiveResult() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Update Live Score---");
        
        System.out.println("Current Score " + this.HomeTeam + ":");
        if (sc.hasNextInt()) {
            this.HomeScore = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Cannot Update Score For HomeTeam. Invalid Input.");
            sc.nextLine();
        }

        System.out.println("Current Score " + this.OpponentTeam + ":");
        if (sc.hasNextInt()) {
            this.OpponentScore = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Cannot Update Score For OpponentTeam. Invalid Input.");
        }
    }

    void MatchInformation() {
        System.out.format("%d |%20s:%d vs %20s:%d|%20s| %td/%tm/%ty %tl.%tM %tp %n", 
                this.MatchID, this.HomeTeam, this.HomeScore, this.OpponentTeam, this.OpponentScore, this.Venue, 
                this.MatchDateTime, this.MatchDateTime, this.MatchDateTime, this.MatchDateTime, this.MatchDateTime, this.MatchDateTime);
    }

    void ScheduleDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void ChangeSchedules() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void Team() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// === LỚP SCHEDULE (ĐÃ HOÀN THÀNH) ===
class Schedule {
    Match matchObject; 
    String status = "Scheduled"; 

    void ScheduleDetails() {
        System.out.println("=================================================");
        System.out.println("--- SCHEDULE DETAILS ---");
        System.out.println("Current Status: " + this.status);
        if (this.matchObject != null) {
            System.out.println("Linked Match Information:");
            this.matchObject.MatchInformation();
        } else {
            System.out.println("No match has been assigned to this schedule yet.");
        }
        System.out.println("=================================================");
    }

    void ChangeSchedules() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new status for this schedule (e.g., Postponed, Cancelled, Played):");
        this.status = sc.nextLine().trim();
        System.out.println("Schedule status updated successfully to: " + this.status);
    }
}

// === LỚP TEAM (ĐÃ HOÀN THÀNH) ===
class Team {
    String teamName;
    playerList listPlayer = new playerList(); 

    void TeamInformation() {
        Scanner sc = new Scanner(System.in);
        if (this.teamName == null || this.teamName.isEmpty()) {
            System.out.println("Enter Team Name to set profile:");
            this.teamName = sc.nextLine().trim();
        }
        System.out.println("=================================================");
        System.out.println("=== TEAM PROFILE: " + this.teamName.toUpperCase() + " ===");
        System.out.println("List of current players in the team:");
        this.listPlayer.printAllPlayer(); // Gọi trực tiếp hàm in từ playerList của nhóm
        System.out.println("=================================================");
    }
}