/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyServer;

import java.net.*;
import java.io.*;

/**
 *
 * @author 19797
 */
public class MyServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         MyServer s = new MyServer();

    }

  
    
    public MyServer()
            {
                try{
                    //在9999端口监听
                    System.out.println("服务器在9999监听...");
                    ServerSocket ss = new ServerSocket(9999);
                    
                    //等待连接
                    Socket s = ss.accept();
                    //先接收客户端发来的消息
                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    
                    PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
                    
                    //接收从控制台输入的信息
                    InputStreamReader isr2 = new InputStreamReader(System.in);
                    BufferedReader br2 = new BufferedReader(isr2);

                    while(true)
                    {
                        String infoFromClient = br.readLine();
                        
                        System.out.println("客户端："+infoFromClient);
                        //接收从控制台输入的信息
                        System.out.println("输入你希望对客户端说的话：");
                        String response = br2.readLine();
                        //把从控制台接收的信息，回送给客户端
                        pw.println(response); 
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
}
    

