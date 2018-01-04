package com.linkapp.hlc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.linkapp.hlc.entity.Config;

public class SerializeUtil {

	public static void main(String[] args) {
		List<Config> con=new ArrayList<Config>();
		Config c1=new Config("2", "中文Rsad1#");
		con.add(c1);
		 byte[] b=serialize(con);
		 
		 List<Config> con2= (List<Config>) unserialize(b);
		 System.out.println(con2.get(0).getName());
	}
	
	public static byte[] serialize(Object object) {

		ObjectOutputStream oos = null;

		ByteArrayOutputStream baos = null;

		try {

			// 序列化

			baos = new ByteArrayOutputStream();

			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			byte[] bytes = baos.toByteArray();

			return bytes;

		} catch (Exception e) {

		}

		return null;

	}

	public static Object unserialize(byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {

			// 反序列化

			bais = new ByteArrayInputStream(bytes);

			ObjectInputStream ois = new ObjectInputStream(bais);

			return ois.readObject();

		} catch (Exception e) {

		}

		return null;

	}

}
