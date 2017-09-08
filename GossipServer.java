import java.io.*;
import java.net.*;
import java.util.*;

public class GossipServer{
  public static ArrayList<String> listUsers = new ArrayList<String>();
  public static ArrayList<Socket> listSocks = new ArrayList<Socket>();

  public static void addUserName(Socket X)throws IOException{
    Scanner INPUT = new Scanner(X.getInputStream());
    String user = INPUT.nextLine();
    listUsers.add(user);

    for(int i=0;i<listSocks.size();i++){
      Socket TEMP = (Socket) listSocks.get(i);
      PrintWriter print = new PrintWriter(TEMP.getOutputStream());
      print.println("#?"+listUsers);
      print.flush();
    }
  }

  public static void main(String[] args)throws IOException{

    try{
      ServerSocket server = new ServerSocket(30000);
      System.out.println("Server is ready and listening");

      while(true){
        Socket client = server.accept();
        listSocks.add(client);
        //System.out.println(client.getLocalAddress.getHostName()+"Connected...");
        addUserName(client);

        ClientHandler cl = new ClientHandler(client);
        Thread X = new Thread(cl);
        X.start();
      }

    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
