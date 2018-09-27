package sim.util.criptografia;

import org.jcommon.encryption.SimpleMD5;

public class Criptografia {
	
	static String palavra = "roger.bernar9@gmail.com1234567='1'='1'#!1!!";
	
	public static String criptografia(String texto)	{
		SimpleMD5 md5 = new SimpleMD5(texto, palavra);
		return md5.toHexString();
	}

}
