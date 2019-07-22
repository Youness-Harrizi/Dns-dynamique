package server;

import java.io.IOException;

public class MainServer {
    /**
     * Parametres sont :
     *  -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=password
     *
     */
    // ports arbitraires
    private final static int[] ports={5555,8881,8882,8883,8884,8885,8886,8887,8888,8889};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread thread = new Server(ports[i]);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}