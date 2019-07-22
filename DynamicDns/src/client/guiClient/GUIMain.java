package client.guiClient;

import client.Client;


public class GUIMain {
    /**
     * paramètres sont:
     *  -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=password
     */
    public static void main(String[] args)  {
        try {
            MyFrame f = new MyFrame();
            f.setVisible(true);


            while(true ) {
                System.out.println("");
                if (f.getPassword() != null) {


                    break;
                    }
            }
            Client client=new Client(f.getPort(),f.getServerName(),f.getPassword(),f.getDomainName());

            if(client.getBool()) {
                WelcomeFrame welcomeFrame = new WelcomeFrame(true);
                welcomeFrame.setVisible(true);
            }
            else {
                WelcomeFrame welcomeFrame = new WelcomeFrame(false);
                welcomeFrame.setVisible(true);
                }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
