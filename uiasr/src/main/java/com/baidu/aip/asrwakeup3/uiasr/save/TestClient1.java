package com.baidu.aip.asrwakeup3.uiasr.save;

import android.content.Context;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient1 {
	//private String address = "192.168.0.110";
	private int port = 52321;
 
	public TestClient1(Context context,String ip,String name) {


		Socket client = new Socket();
		InetSocketAddress adr = new InetSocketAddress(ip, this.port);
		try {
			client.connect(adr, 10000);
			ObjectOutputStream out = new ObjectOutputStream(
					client.getOutputStream());
			out.writeObject(name);
            out.writeObject(null);
			out.flush();
			out.close();
			client.close();
		} catch (java.io.IOException e) {

			System.out.println("IOE7777777777 :" + e.toString());
		}
	}
}
