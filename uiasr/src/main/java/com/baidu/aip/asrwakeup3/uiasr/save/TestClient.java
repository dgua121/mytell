package com.baidu.aip.asrwakeup3.uiasr.save;

import android.content.Context;
import android.os.Message;

import com.baidu.aip.asrwakeup3.uiasr.service.callService;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient {
	private String address = "192.168.0.112";
	private int port = 3400;
 
	public TestClient(Context context,String name,String sort,String phone) {
		// Prepare the data need to transmit
		ObjectSeri data = new ObjectSeri();
		data.setName(name);
		data.setsort(sort);
		data.setphone(phone);
		if (sort.equals("A")||sort.equals("B")) {
			data.setvido(FileUtil.getData(context.getFilesDir().toString() + "/baiduASR/outfile.pcm"));
		}
		Socket client = new Socket();
		InetSocketAddress adr = new InetSocketAddress(this.address, this.port);
		try {
			client.connect(adr, 100000);
			ObjectOutputStream out = new ObjectOutputStream(
					client.getOutputStream());
			out.writeObject(data);
			out.flush();
			out.close();
			out = null;
			data = null;
			client.close();
			client = null;
		} catch (java.io.IOException e) {
			System.out.println("IOException7777777777777777777777777777777777777777777 :" + e.toString());
		}
		Message msg1 = new Message();
		msg1.what = 1111;
		callService.handler.sendMessage(msg1);
	}
}
