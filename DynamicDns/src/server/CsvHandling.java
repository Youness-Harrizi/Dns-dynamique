package server;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CsvHandling {
        public static  Boolean readCsv(String fileName,String[] clientValues) {

            File file=new File(fileName);
            if (file.exists()){
                System.out.println("file does exist");
            }
            else{
                System.out.println("file does not exist");
            }
            try {
                Scanner scanner = new Scanner(file) ;
                int compteur=0;
                // escape the first line
                scanner.next();
                while(scanner.hasNext()){
                    compteur++;//line starts from 0
                    String data=scanner.next();
                    //System.out.println(data);
                    String[]values= data.split(",");
                    //System.out.println(values[1]);
                    // let's compare the client values with the file values in terms of password and domain names
                    /*
                    0:domain , 3:lastIp , 4:ip , 5:password

                    System.out.println("les valeurs 0 sont \n");
                    System.out.println(""+values[0]+"  :  "+clientValues[0]);
                    System.out.println("les valeurs 1 sont \n");
                    System.out.println(""+values[1]+"  :  "+clientValues[1]);
                    System.out.println("les valeurs 2 sont \n");
                    System.out.println(""+values[2]+"  :  "+clientValues[2]);
                    System.out.println("les valeurs 3 sont \n");
                    System.out.println(""+values[3]+"  :  "+clientValues[3]);
                    System.out.println("les valeurs 4 sont \n");
                    System.out.println(""+values[4]+"  :  "+clientValues[4]);
                    System.out.println("les valeurs 5 sont \n");
                    System.out.println(""+values[5]+"  :  "+clientValues[5]);

                     */
                    if (values[0].equals(clientValues[0])&& values[5].equals(clientValues[5])) {
                        System.out.println("the domain and the password match\n");
                        // now let's see the ips
                        if (values[4].equals(clientValues[4])) {
                            System.out.println("ip didn't change");
                            scanner.close();
                            return false;


                        } else if (values[4].equals(clientValues[3])) {
                            System.out.println("compteur is :"+ compteur);
                            System.out.println("we should save a new record and delete the previous one\n");
                            //deleteRecord(compteur,fileName);
                            saveRecord(values[0],Integer.parseInt(clientValues[1]),Integer.parseInt(clientValues[2]),InetAddress.getByName(clientValues[3]),InetAddress.getByName(clientValues[4]),clientValues[5],fileName);
                            deleteRecord(compteur,fileName);
                            scanner.close();
                            return true;
                        }

                        // continue

                    }

                }
                scanner.close();

            } catch (IOException e){
                e.printStackTrace();

            }
            return false;


        }


        private static void saveRecord(String domainName, int port, int lastport, InetAddress ip, InetAddress lastIp, String password, String fileName){

            try {
                // the second parameter is the boolean append
                FileWriter fileWriter=new FileWriter(fileName,true);
                BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);

                bufferedWriter.write("\n"+domainName+","+lastport+","+port+","+lastIp.getHostAddress()+","+ip.getHostAddress()+","+password+"\n");
                // make sure that all the data are written to the file
                bufferedWriter.flush();
                // close the stream
                bufferedWriter.close();
                System.out.println("record saved");


            }catch (IOException e){
                e.printStackTrace();
            }



        }
      private static void deleteRecord(int row,String fileName) {
          File inputFile = new File(fileName);
          File tempFile = new File("src/myTempFile.csv");
          try {


              BufferedReader reader = new BufferedReader(new FileReader(inputFile));
              BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
              int compteur=-1;
                String currentLine;
              while ((currentLine = reader.readLine()) != null) {
                  compteur++;
                  if (compteur== row) continue;
                  writer.write(currentLine+System.getProperty("line.separator"));
              }
              writer.close();
              reader.close();
              // delete the old file and then rename the new file
              FileUtil.copyFile("src/myTempFile.csv",fileName);


          }catch (FileNotFoundException e){
              e.printStackTrace();
          }catch (IOException e){
              e.printStackTrace();
          }
      }

        // application
       public static void main(String[] args) throws UnknownHostException {
            String[] clientValues={"domain1","5555","5555","127.0.0.1","127.0.0.1","admin"};
            //saveRecord("domain1",5555,5555,InetAddress.getByName("facebook.com"),InetAddress.getByName("facebook.com"),"admin","src/data.csv");
            deleteRecord(2,"src/data.csv");
          /*
            Boolean check= readCsv("src/data.csv",clientValues);
           if(check) System.out.println("check is true");
           else{
               System.out.println("check is false");
           }
           System.out.println(InetAddress.getLocalHost().getHostAddress());
           System.out.println(InetAddress.getByName("localhost"));
        */
        }
    }

