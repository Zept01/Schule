package bauer.VSY.DHCPAuflösung;

import java.util.Scanner;

public class Auflösung {

    public static void main(String[] args){
        System.out.println("Bitte Raum und Computer angeben (z.B. A106-15)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char building = input.charAt(0);
        char floor = input.charAt(1);
        String room = "";
        if(input.charAt(2) == '0'){
            room = "" + input.charAt(3);
        }
        else{
            room = "" + input.charAt(2)  + input.charAt(3);
        }
        String pc = input.split("-")[1];
        String buildingAndFloor = "" + building + floor;
        System.out.println(buildingAndFloor);
        int decimal = Integer.parseInt(buildingAndFloor, 16);
        System.out.println(decimal);
        String ip = "10." + decimal + "." + room + "." + pc;
        System.out.println(ip);
    }
}
