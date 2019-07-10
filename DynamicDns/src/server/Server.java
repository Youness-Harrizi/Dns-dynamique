package server;
import client.MessageClient;

import java.io.*;
import java.net.*;

public class Server extends Thread{

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) throws IOException {
        super();
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000000);
        this.port=port;

    }

    public void run() {
        while (true) {
            try {

                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                // the client will send a message that contains ip
                ObjectInputStream objectStream=new ObjectInputStream(server.getInputStream());
                MessageClient messageClient=(MessageClient)objectStream.readObject();
                //System.out.println(messageClient);

                /*now checking the authentification
                System.out.println("the client message is... \n");
                System.out.println(messageClient);
*/
                String[] clientValues={messageClient.getDomain(),""+messageClient.getLastPort(),
                       ""+ messageClient.getPort(),messageClient.getLastIp().getHostAddress(),
                        messageClient.getIp().getHostAddress(),messageClient.getPassword()};




              //  String[] clientValues={"domain5","5555","5555","216.58.198.78","157.240.195.00","admin4"};

                Boolean check= CsvHandling.readCsv("src/data.csv",clientValues);

                if(check){
                    System.out.println("check is true\n");
                    CsvHandling.deleteEmptyLines("src/data.csv");
                }

                else {
                    System.out.println("check is false and the connexion is over ");
                    server.close();
                    // réintialiser le se
                    serverSocket.close();
                    serverSocket = new ServerSocket(port);
                    serverSocket.setSoTimeout(10000000);
                    break;
                }
                // the client now will send now a hello message if the authentification is made
                System.out.println("\n the communication is ok .... \n ");



                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                        + "\nGoodbye!");
                //server.close();

                // deleting non important files
                File file1=new File("src/data2.csv");
                if(file1.exists()) file1.delete();
                File file2=new File("src/myTempFile.csv");
                if(file2.exists()) file2.delete();


            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

}