/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg24服务器;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ZZH
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void game(OutputStreamWriter socketOut,BufferedReader socketReader){
        try {
                    int a,b,c,d;
                    List<String> answerStris;
                    do{        
                    a=1+(int)(Math.random()*9);
                    b=1+(int)(Math.random()*9);
                    c=1+(int)(Math.random()*9);
                    d=1+(int)(Math.random()*9);
                    System.out.println(a+"\n"+b+"\n"+c+"\n"+d);
                    answerStris=Count24.easyCount(new int[]{a,b,c,d});
                    }while(answerStris.isEmpty());
                    for (String string : answerStris) {
                            System.out.println(string);
                        }
                String abcd = a+" "+b+" "+c+" "+d;
                socketOut.write(abcd+"\r\n");
                socketOut.flush();
                //读取客户端的数据
                String line = null;
                int ret = 0;
                while((line = socketReader.readLine())!=null){
                    System.out.println("服务端接收到的数据："+ line);    
                        for (String string : answerStris) {
                            //System.out.println(string);
                            if(line.equals(string))
                                ret = 1;
                        }
                    if(ret == 1){
                        line = "对了";
                        socketOut.write(line+"\r\n");
                        socketOut.flush();
                        String name = socketReader.readLine();
                        System.out.println(name);
                        String time = socketReader.readLine();
                        System.out.println(time);
                        int time_ = Integer.parseInt(time);
                        System.out.println(time_);
                        Rank.addPlayer(name,time_);
                        //Rank.addPlayer(name,time_);
                        break;
                    }
                    else{
                         if(line.equals("超时"))
                            break;
                        line = "错了";
                        socketOut.write(line+"\r\n");
                        socketOut.flush();
                    }
                }

  
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

   public static void main(String[] args) throws IOException {
        // TODO code application logic here
                 //建立tcp的服务端
                ServerSocket serverSocket = new ServerSocket(9090);
                //接受客户端的连接，产生一个SOcket
                Socket socket = serverSocket.accept();  

                //获取到Socket的输入流对象
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  

                //获取到Socket输出流对象
                OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());  

                //获取键盘的输入流对象
                //BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
                //获取到Socket的输出流（字符串数组）
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
                String input = null;
        do{
                input = socketReader.readLine();
                if(input.equals("1")){
                    game(socketOut,socketReader);
                }
                if(input.equals("2")){
                    top10(out);
                }   
        }while(!(input.equals("0")));
        //关闭资源
        serverSocket.close();
}

    private static void top10(ObjectOutputStream out) throws IOException {
        String[] rank = Rank.getList();
        
//        socketOut.write(rank.length);
//        socketOut.flush();
//        for(String string:rank){
//            System.out.println(string);
//            socketOut.write(rank[i]);
//            socketOut.flush();
//        }
         out.writeObject(rank);
    }



}
