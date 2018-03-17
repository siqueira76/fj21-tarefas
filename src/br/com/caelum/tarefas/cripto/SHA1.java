package br.com.caelum.tarefas.cripto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
	
	public static String hash(byte[] input) throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
	}
	
	public static class TesteHash {

		public static void main(String[] args) throws NoSuchAlgorithmException {
			String test = "vaicorinthians";
			System.out.println(SHA1.hash(test.getBytes()));

		}

	}

}

