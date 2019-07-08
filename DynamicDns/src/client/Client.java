package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private InetAddress lastIp;
    private InetAddress ip;
    private int lastPort;
    private int port;
    private String password;
    private String domain;

    public InetAddress getLastIp() {
        return lastIp;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getLastPort() {
        return lastPort;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getServerName() {
        return serverName;
    }

    private String serverName;

    // constructor

    public Client(int port, String serverName,String password,String domain) throws IOException {

        this.serverName = serverName;
        this.lastPort=port;
        this.port = port;
        this.password=password;
        this.setParameters("src/client/clientLastIp");


        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            MessageClient message = new MessageClient(domain, lastIp, ip, lastPort, port, password);
            sendAuthentificationMessage(client, message);
            // if connection is not finished send our normal message
            Boolean bool = sendMessage(client);
            // the lastIp should be the newIp if there was a change after that
            // on ne change l'ip que si on a recu un message du serveur
            if (setChanged() && bool) {
                System.out.println("last Ip has changed");
                changeLastIpFile("src/client/clientLastIp");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeLastIpFile(String fileName) {
        try {
            File file=new File(fileName);
            if(file.exists()) System.out.println("the file exists.....\n");
            FileWriter fileWriter=new FileWriter(fileName,false);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
            bufferedWriter.write(this.ip.getHostAddress());
            bufferedWriter.close(); // c'est necessaire
            String newIp=this.ip.getHostAddress();
            System.out.println(newIp);



        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    // this will set the new and the old ips
    public void setParameters(String fileName) throws java.net.UnknownHostException{
        // to be geneeralized
        this.lastIp=readLastIp(fileName);
        this.ip=InetAddress.getLocalHost();

    }

    private InetAddress readLastIp(String fileName) {
        // read the LastIp and then change it to the
        try {
            String lastIp="";
            File file=new File(fileName);
            if (file.exists()) System.out.println("file does exist");
            else System.out.println("file does not exist");
            Scanner scanner=new Scanner(file);
            lastIp=scanner.next();

            return  InetAddress.getByName(lastIp);

        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;


    }

    // this will return true if a certain parameter has been changed
    public boolean setChanged() {
        return (lastPort != port || lastIp != ip);
    }


    public void sendAuthentificationMessage(Socket client,MessageClient message) {
        // send the ClientMessage type
        try {
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);
            out.writeObject(message);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // returns true if we have received something from the server
    public Boolean sendMessage(Socket client) throws java.io.IOException{

        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("Hello from " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        if(in==null){
            System.out.println("The connection was broke");
            return false;
        }
        try {
            System.out.println("Server says " + in.readUTF());
            client.close();
        }catch(EOFException e){
            System.out.println("The connection is over ....\n retry again ....");
        }
        return true;

    }
}
