package hjz.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerializable2 {
	
	public static class Person implements Serializable {

		/**
		 * generated.
		 */
		private static final long serialVersionUID = 8735472455867135905L;
		
		private String name;
		private String age;
		//transient fields will not be searialzied/deserialized automatically, but can manually handle
		private transient String password;
		
		public Person(String name, String age, String password) {
			this.name = name;
			this.age = age;
			this.password = password;
		}
		
		public String toString() { 
			return "name:" + name + " age:" + age + " password:" + password; 
		} 
		
		//this method must be private
		private void writeObject(ObjectOutputStream out) throws IOException { 
			out.defaultWriteObject();  //序列化所有非transient字段,必须是该方法的第一个操作 
			out.writeObject(password); //序列化transient字段 
		} 
		
		//this method must be private
		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException { 
			in.defaultReadObject();             //反序列化所有非transient字段,必须是该方法的第一个操作 
			password = (String)in.readObject(); //反序列化transient字段 
		} 
		
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		//序列化一个对象(存储到一个文件) 
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.file")); 
		oos.writeObject("Save a object:\n"); 
		oos.writeObject(new Person("Bruce", "24", "123456")); 
		oos.close(); 

		//反序列化,将该对象恢复(存储到一个文件) 
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.file")); 
		String s = (String)ois.readObject(); 
		Person p = (Person)ois.readObject(); 
		System.out.println(s + p); 
		ois.close();

		//序列化一个对象(存储到字节数组) 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		ObjectOutputStream oos2 = new ObjectOutputStream(baos); 
		oos2.writeObject("Save another object:\n"); 
		oos2.writeObject(new Person("Phil","24", "654321")); 
		oos2.close(); 
		
		byte[] bytes = baos.toByteArray();
		System.out.println("bytes:"+bytes);

		//反序列化,将该对象恢复(存储到字节数组) 
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(bytes)); 
		s = (String)ois2.readObject(); 
		p = (Person)ois2.readObject(); 
		System.out.println(s + p); 
		ois2.close();
		
	}

}
