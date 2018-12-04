/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient1;

import java.net.*;
import java.io.*;
/**
 *
 * @author 19797
 */
public class MyClient1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyClient1 s = new MyClient1();
    }
    
    public MyClient1()
    {
        try{
            //Socket()就是去连接某个服务器端
            //ip 和 端口
            Socket s = new Socket("192.168.31.18",9999);
            //如果s连接成功，就可以发送数据给服务器
            //通过pw向s写数据，true表示即时刷新
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            
            pw.println("你好，我是客户端");
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            
            String response = br.readLine();
            System.out.println("我是客户端，收到服务器回送的消息："+response);
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    }
    

