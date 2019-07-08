package client.guiClient;

import client.Client;

import java.io.IOException;

public class GUIMain {
    public static void main(String[] args)  {
        try {
            MyFrame f = new MyFrame();
            f.setVisible(true);


            while(true ) {
                System.out.println(f.getPassword());
                if (f.getPassword() != null) {


                    break;
                    }
            }
            Client client=new Client(5555,f.getServerName(),f.getPassword(),f.getDomainName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
