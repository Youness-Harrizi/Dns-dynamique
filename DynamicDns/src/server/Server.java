package server;
import client.MessageClient;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread{

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) throws IOException {
        super();


        this.port=port;

    }

    public void run() {

        while (true) {
            try {
                SSLServerSocketFactory sslServerSocketFactory =
                        (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
                serverSocket =sslServerSocketFactory.createServerSocket(port);
                serverSocket.setSoTimeout(10000000);

                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
              //  ReceiveSimpleMessages(server);

                // the client will send a message that contains ip
                ObjectInputStream objectStream=new ObjectInputStream(server.getInputStream());
                MessageClient messageClient=(MessageClient)objectStream.readObject();


                String[] clientValues={messageClient.getDomain(),""+messageClient.getLastPort(),
                       ""+ messageClient.getPort(),messageClient.getLastIp().getHostAddress(),
                        messageClient.getIp().getHostAddress(),messageClient.getPassword()};



                Boolean check= CsvHandling.readCsv("src/data.csv",clientValues);
                // si tout ce passe bien
                if(check){
                    System.out.println("check is true\n");
                    CsvHandling.deleteEmptyLines("src/data.csv");



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
                if(file2.exists()) file2.delete();}
                else {
                    System.out.println("check is false and the connexion is over ");
                    server.close();
                    serverSocket.close();

                    break;
                }


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

    // test test

    private void ReceiveSimpleMessages(Socket socket) {

        try {

            System.out.println("SSL ServerSocket started");


            System.out.println("ServerSocket accepted");



            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader =
                         new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);

                    out.println("message received");
                }
            }
            System.out.println("Closed");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}