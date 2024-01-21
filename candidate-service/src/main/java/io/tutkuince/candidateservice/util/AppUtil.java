package io.tutkuince.candidateservice.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AppUtil {
    private static final String HOST_NAME;

    static {
        try {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHostName() {
        return HOST_NAME;
    }
}
