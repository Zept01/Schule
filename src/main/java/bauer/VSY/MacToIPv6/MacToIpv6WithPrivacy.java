package bauer.VSY.MacToIPv6;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.net.ntp.TimeStamp;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Collections;

public class MacToIpv6WithPrivacy {
    public static void main(String[] args) throws SocketException {
        String mac = getMac();
        System.out.println("Your mac: " + mac);
        String time = "e451f7c0ec49ba5e";
        // Gives back a 64Bit timestamp as 16 Hex chars
        // time = TimeStamp.getCurrentTime().toString().replace(".", "");
        System.out.println("Your timestamp: " + time);
        String sha1Long = DigestUtils.sha1Hex((time+mac));
        String sha1Short="";
        for(int i = 0; i< 16; i++){
            sha1Short += sha1Long.charAt(i);
        }
        System.out.println("Your sha1: " + sha1Short);
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
