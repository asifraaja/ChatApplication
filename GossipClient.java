import java.io.*;
import java.net.*;
import java.util.*;

public class GossipClient implements Runnable{

  private static  Socket client;

  public GossipClient(Socket X){
    client = X;
  }

  public void run(){
    try{
      Scanner read = new Scanner(client.getInputStream());

      String inMsg;
      while((inMsg = read.nextLine())!=null){
        System.out.println("RECV:"+inMsg);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public static void main(String args[]){
    Scanner sn = new Scanner(System.in);
    PrintWriter pw;

    try{
      Socket X = new Socket("127.0.0.1",30000);

      System.out.println("Enter your name:");
      String name = sn.next();

      pw = new PrintWriter(X.getOutputStream());

      // Sending name
      pw.println(name);
      pw.flush();

      GossipClient cl = new GossipClient(X);
      Thread Y = new Thread(cl);
      Y.start();

      while(true){
        String outMsg;
        System.out.println("1.BroadCast 2.Personal 3.Exit");
        int ch = sn.nextInt();

        if(ch == 1){
            pw.println("1");pw.flush();
        }else if(ch == 2){
          System.out.println("Enter recipient name:");
          String recp = sn.next();
          pw.println("2");pw.flush();
          pw.println(recp); pw.flush();
        }
        System.out.println("YOU:");
            outMsg = sn.next();
            pw.println(outMsg);
            pw.flush();
      }
     
    }catch(Exception e){
      e.printStackTrace();
    }
  }

}

