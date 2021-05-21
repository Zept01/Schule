package bauer.VSY.subnetting;

public class subnetting {

    public static void main(String[] args){

        int numberOfNetworks = 1;
        System.out.println("===================================================================");
        //Format für die Ausgabe
        String format = "|%1$-12s|%2$-15s|%3$-15s|%4$-20s|\n";

        System.out.format(format, "NetzID-Bits", "Anzahl-Netzte", "Anzahl-Hosts","Subnetztmaske");
        int netBits = 8;
        //Wir gehen bis /30
        for(int i = 0;i <= 22; i++){

            //Unsere Hostbits sind immer 32 - die aktuellen Netbits
            int hostBits = 32-netBits;

            //Wir haben 2 hoch i Networks wobei i die zusätzlichen Bits beschreibt die wir noch dazu nehmen
            int networks = (int) Math.pow(2,i);
            //Die Hosts werden ähnlich berechnet nur das wir noch 2 abziehen für netz und BC
            int hosts = ((int) Math.pow(2,hostBits))-2;
            String mask = getMask(netBits);

            System.out.format(format, netBits, networks, hosts,mask);
            netBits++;
        }
        System.out.println("===================================================================");
    }

    // Hier wird die Maske ermittelt
    private static String getMask(int netBits){
        //wir starten mit /8 also sind die ersten 8 Bit immer auf 1 --> 255
        String mask = "255";

        //wenn wir mindestens 24 Bit als Netzt-ID haben, wissen wir dass die Maske mit 255.255.255 startet
        if(netBits>23){
            mask+=".255.255";
            netBits= netBits-24;
        }
        //wenn wir mindestens 16 Bit als Netzt-ID haben, wissen wir dass die Maske mit 255.255 startet
        else if(netBits>15){
            mask+=".255";
            netBits= netBits-16;
        }
        else{
            netBits=netBits-8;
        }

        String binaryString = "";
        //Die Anzahl der Bits die noch übrig ist, wird als 1en in einen String geschrieben und bis 8 Stellen mit 0 aufgefüllt
        for(int i = 0; i < 8; i++){
            if(i<netBits) {
                binaryString += "1";
            }
            else{
                binaryString += "0";
            }
        }

        //Hier wird der Binär-String in Dezimal umgewandelt
        int decimal = 0;
        if(!binaryString.equals("")) {
            decimal = Integer.parseInt(binaryString, 2);
        }

        //Und die Dezimal Zahl an di Maske angehängt
        mask+="."+decimal;

        //Solange wir keine 4 Oktette haben füll ich mit .0 auf
        while(count(mask,'.') != 3){
            mask+=".0";
        }
        return mask;
    }

    //Check wie oft ein char in einem String ist --> in unserem Fall wie viele '.'
    private static int count(String s,char c){
        int cc=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                cc++;
            }
        }
        return cc;
    }
}
