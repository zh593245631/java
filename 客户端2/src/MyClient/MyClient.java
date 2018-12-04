/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyClient;
import java.net.*;
import java.io.*;

/**
 *
 * @author 19797
 */
public class MyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyClient s = new MyClient();
    }
    public MyClient()
    {
        try{
            //连接服务器端        
            Socket s = new Socket("192.168.31.18",9999);
            //接收从控制台输入的信息
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            
            //接收从服务器端发来的信息
            InputStreamReader isr2 = new InputStreamReader(s.getInputStream());
            BufferedReader br2 = new BufferedReader(isr2);

            while(true)
            {
                System.out.println("输入你希望对服务器端说的话：");
                //客户端先从控制台接收
                String info = br.readLine();
                //然后发送给服务器
                 pw.println(info);
                 //接受从服务器发来的话
                String res = br2.readLine();
                System.out.println("服务器："+res);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
                }
    }
    

