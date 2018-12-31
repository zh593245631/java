/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg24客户端;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZZH
 */
public class Main {
    private static void menu() {
        System.out.println("***24点游戏*****************************");
        System.out.println("*********** 1.PLAY *********************");
        System.out.println("*********** 2.TOP  *********************");
        System.out.println("*********** 0.EXIT *********************");

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException{
        // TODO code application logic here
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);
        //获取socket的输出流对象。
        OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());
        //获取socket的输入流对象
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //获取键盘的输入流对象，读取数据
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int input = 0;
        do{
            menu();
            input = sc.nextInt();
            socketOut.write(input+"\r\n");
            socketOut.flush();
            switch(input){
                case 1: game(socketOut,socketReader,keyReader);break;
                case 2: top10();break;
                case 0: System.out.println("退出游戏。。。");break;
                default: System.out.println("输入错误：");break;
            }
            
        }while(input != 0);
        //关闭资源
        socket.close();
    }

    private static void game(OutputStreamWriter socketOut,BufferedReader socketReader, BufferedReader keyReader){
        try {  
            System.out.println("下列给出四个数字，使用+，-，*，/进行计算使最后计算结果为24（eg：使用括号来标明优先级）");
            String abcd = socketReader.readLine();
            System.out.println(abcd);
            String line = null;
            long beginTime=System.currentTimeMillis();
            //不断的读取键盘录入的数据，然后把数据写出
            while((line = keyReader.readLine())!=null&&System.currentTimeMillis()-beginTime<=1000*60*1){
                
                socketOut.write(line+"\r\n");
                //刷新
                socketOut.flush();
                //读取服务端回送的数据
                line = socketReader.readLine();
               // System.out.println(line);
                if(line.equals("对了")){
                    System.out.println("对了");
                    break;
                }
                if(line.equals("错了")){
                    System.out.println("错了");
                }    
            }
            if(System.currentTimeMillis()-beginTime>1000*60*1)
            {
                System.out.println("超时");
                line = "超时";
                socketOut.write(line+"\r\n");
                socketOut.flush();
            }

        } catch (IOException ex) {  
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            

    private static void top10() {
        
    }
}
