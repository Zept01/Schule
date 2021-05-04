package bauer.VSY.IPv4;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4Validator {

    //Regex pattern for the validation of an IPv4 address
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    //Forming the String to a concrete Java Pattern
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static void main(String[] args){

        System.out.println("Hello to the IPv4 Validator");
        Scanner scanner = new Scanner(System.in);
        //The program does not stop after one validation but asks if further IPv4 addresses should be validated
        while(true) {
            System.out.println("Please provide a valid IPv4 address");
            String ipv4 = scanner.next(); //Scanns the user Input

            if (!validateIPv4Address(ipv4)) { //Testing the validation of the provided IP
                System.out.println("Your provided IP wasnt valid, please make sure you provide it in this form: 0-255.0-255.0-255.0-255");
            } else {
                System.out.println("Your ip is: " + ipv4);
                String[] bytes = ipv4.split("[.]"); //Splits the whole IP String at "."
                int firstByteDecimal = Integer.parseInt(bytes[0]); //Takes the first Byte of the IP(String) and parses it into an Integer
                System.out.println("First byte of the IP(dec): " + firstByteDecimal);
                String firstByteBinary = convertDecInBin(firstByteDecimal); //The Byte is converted from decimal into binary
                System.out.println("First byte of the IP(bin): " + firstByteBinary);
                System.out.println(findClass(firstByteBinary)); //find the correct class according to the binary first Byte

            }
            System.out.println("Do you want to validate another IPv4?");
            System.out.println("Type 'Yes' if you want or any key to stop");
            String repeat = scanner.next(); //Answer is scanned
            if (!repeat.equals("Yes")) { //If the Answer is anything else than "Yes" the program will stop
                break;
            }
        }
    }

    private static String findClass(String firstByteBinary) {
        //simple checks whether the string of the binary firstByte starts with the corresponding identifier of the respective class
        if(firstByteBinary.startsWith("0")){
            return "The first bit of your Net-ID is '0', which means it is an A-Class network";
        }
        else if(firstByteBinary.startsWith("10")){
            return "The first bits of your Net-ID are '10', which means it is a B-Class network";
        }
        else if(firstByteBinary.startsWith("110")){
            return "The first bits of your Net-ID are '110', which means it is a C-Class network";
        }
        else if(firstByteBinary.startsWith("1110")){
            return "The first bits of your Net-ID are '1110', which means it is a D-Class network";
        }
        else {
            return "The first bits of your Net-ID are '1111', which means it is an E-Class network";
        }
    }

    private static String convertDecInBin(int firstByteDecimal) {
    String decToBin = Integer.toBinaryString(firstByteDecimal); //Converts an int (dec) into an String (binary)

    while(decToBin.length() < 8){
        decToBin = 0 + decToBin; //Fills the string with zeros until it has a length of 8
    }

    return decToBin;
    }


    private static boolean validateIPv4Address(String ipv4) {
            if (ipv4 == null) { //Check if the String is empty
                return false;
            }
            Matcher matcher = IPv4_PATTERN.matcher(ipv4); //Its valid if it matches the predefined pattern
            return matcher.matches();
        }
    }


