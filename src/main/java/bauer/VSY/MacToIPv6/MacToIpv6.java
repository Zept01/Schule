package bauer.VSY.MacToIPv6;


import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

public class MacToIpv6 {
    public static void main(String[] args) throws SocketException {
        String mac = getMac();
        System.out.println("Your mac: " + mac);
        // Split mac in 6 parts
        String[] macSplit = mac.split(":");

        // get the second Char of the first Split in convert the hex to binary
        // e.g. First Split 30 --> second char = 0 --> hex in binary 0000
        String firstSplit = macSplit[0];
        int secondCharOfFirstSplitAsInt = Character.getNumericValue(firstSplit.charAt(1));
        String charInBin = convertToBin(secondCharOfFirstSplitAsInt);

        //Invert the thrid bit e.g. 0000 --> 0010
        StringBuilder stringBuilder = new StringBuilder(charInBin);
        if(charInBin.charAt(2)=='0'){
            stringBuilder.setCharAt(2,'1');
        }
        else{
            stringBuilder.setCharAt(2,'0');
        }
        String newCharInBin = stringBuilder.toString();
        //Convert the new binary back to hex
        //e.g. 0010 --> 2
        String newHex = Integer.toHexString(Integer.parseInt(newCharInBin, 2));

        //build the ipv6Id
        String ipv6ID = firstSplit.charAt(0) + newHex + macSplit[1] + ":" + macSplit[2] + "FF:FE" + macSplit[3] + ":" + macSplit[4] + macSplit[5];
        System.out.println("Your Ipv6Id: " + ipv6ID);
    }

    // we need this function to fill up the binary number with zeros
    private static String convertToBin(int number) {
        String bin = Integer.toBinaryString(number);
        for(int i = bin.length(); i < 4; i++){
            bin = "0" + bin;
        }
        return bin;
    }

    public static String getMac() throws SocketException {
        String mac = "";
        for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            byte[] adr = ni.getHardwareAddress();
            if (adr == null || adr.length != 6)
                continue;
            mac = String.format("%02X:%02X:%02X:%02X:%02X:%02X",
                    adr[0], adr[1], adr[2], adr[3], adr[4], adr[5]);
        }
        return mac;
    }

}


