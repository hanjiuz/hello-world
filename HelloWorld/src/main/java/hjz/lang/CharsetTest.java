package hjz.lang;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class CharsetTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String enName = "han jiu zheng";	
		byte[] enBytes = enName.getBytes("UTF-8");
				
		System.out.println(enName);
		System.out.println(enName.length());
		//System.out.println(enBytes);
		System.out.println(enBytes.length);	
		
		
		String cnName = "韩久正";
		//change charset name to different values: empty (system default), UTF-8, UTF-16, UTF-32, GBK, GB2312
		//result: UTF-8 is 9, UTF-16 is 8, UTF-32 is 12, GBK/GB2312 is 6. empty in my mac is UTF-8.
		byte[] cnBytes = cnName.getBytes("UTF-16");
		
		System.out.println(cnName);
		System.out.println(cnName.length());
		//System.out.println(cnBytes);
		System.out.println(cnBytes.length);
		
		
		char c = '韩';
		//set different buffer size to see whether BufferOverflowException occurs.
		ByteBuffer bb = ByteBuffer.allocate(2);
		byte[] arr = bb.putChar(c).array();
		System.out.println(arr.length);
	}

}
