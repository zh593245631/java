/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver1;

import java.net.*;
import java.io.*;

/**
 *
 * @author 19797
 */
public class  MyServer1{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyServer1 s1 = new MyServer1();
    }
    public MyServer1()
    {
    try{
        //在1536号端口监听
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("我是服务器，在9999端口监听。。");
        //等待某个客户端来连接，该函数返回一个Scoket连接
        Socket s = ss.accept();
        //System.out.println("111");
        //要读取s中传递的数据
        InputStreamReader isr = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        
        String info = br.readLine();
        
        System.out.println("服务器接收到:"+info);
        
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        pw.println("我是服务器，你也好");

    }catch (Exception e){
        e.printStackTrace();
    
    }
        }
    
}
