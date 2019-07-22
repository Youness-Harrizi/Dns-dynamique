package client.guiClient;

import client.Client;

import java.io.IOException;

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
            TextingFrame textingFrame=new TextingFrame();
            textingFrame.setVisible(true);
            String text="";
            while(!text.equals("quit")){
                try {
                    String text2=textingFrame.getText();
                    if(!text2.equals(text))
                    {
                        text=text2;
                    }
                    System.out.println(text);

                   // client.setText(text);

                }catch (NullPointerException e){
                    System.out.println("text est null");
                }

            }
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
