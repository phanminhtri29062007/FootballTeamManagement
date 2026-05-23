package FootballTeamMangement;


import java.util.Scanner;

public class InputUtils {

    static Scanner sc = new Scanner(System.in);

    public static String getString(String msg) {
        String s;

        while (true) {
            System.out.print(msg);
            String raw = sc.nextLine();
            s = raw.trim();

            if (s.length() > 0) {
                String tmp = "";

                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    boolean ok = Character.isLetter(ch) || ch == ' ';
                    if (ok) {
                        tmp += ch;
                    }
                }

                tmp = tmp.trim();

                if (tmp.equals("")) {
                    System.out.println("invaid input data");
                    continue;
                } else {
                    String[] arr = tmp.split("\\s+");
                    String result = "";

                    for (int i = 0; i < arr.length; i++) {
                        String w = arr[i];
                        result += Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase() + " ";
                    }

                    return result.trim();
                }
            } else {
                System.out.println("Error: cant be empty");
                continue;
            }
        }
    }

    public static String checkPosition() {

        while (true) {
            System.out.print("Enter position (Goalkeeper, Defender, Midfielder, Forward): ");
            String val = sc.nextLine().trim();

            if (val.equalsIgnoreCase("Goalkeeper")
                    || val.equalsIgnoreCase("Defender")
                    || val.equalsIgnoreCase("Midfielder")
                    || val.equalsIgnoreCase("Forward")) {

                return val.substring(0, 1).toUpperCase() + val.substring(1).toLowerCase();
            }

            System.out.println("wrong! only Goalkeeper / Defender / Midfielder / Forward");
        }
    }

    public static int getInt(String msg, int min, int max) {

        while (true) {
            System.out.print(msg);

            try {
                int n = Integer.parseInt(sc.nextLine());
                boolean inRange = n >= min && n <= max;

                if (inRange) {
                    return n;
                } else {
                    System.out.println("Error: number must be between " + min + " and " + max);
                }

            } catch (Exception e) {
                System.out.println("invalid number");
            }
        }
    }

    public static double getSalary(String msg) {

        while (true) {
            System.out.print(msg);

            try {
                double sal = Double.parseDouble(sc.nextLine());

                if (sal > 0) {
                    return sal;
                }

                System.out.println("salary must be > 0");

            } catch (Exception e) {
                System.out.println("wrong salary format");
            }
        }
    }
}