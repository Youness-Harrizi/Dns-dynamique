package client;
import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws java.io.IOException{
        // changer l'ip du client chaque fois
        // 0: fill the data.csv
        //1:create 10 clients (example)

        Scanner scanner=new Scanner(System.in);

         String serverName; String password; String domain;
        // let do the scanner thing
        System.out.println("Enter the server name\n");
        serverName=scanner.nextLine();
        System.out.println("Enter the domain name\n");
        domain=scanner.nextLine();
        System.out.println("Enter the password\n");
        password=scanner.nextLine();
  //      Console console = System.console();
        Console console = System.console();
        if(console==null) System.out.println("CONSOLE EST NULLE");
       // password=new String(console.readPassword("Enter the password"));
       // System.out.println("the password is :"+password);



        Client client=new Client(5555,serverName,password,domain);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
       // client.start();
        //Client client=new Client(5555,"localhost","admin","domain1");









    }

}
