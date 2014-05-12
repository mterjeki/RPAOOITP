
package hu.bme.RPAOOITP.domain.util;

import java.security.MessageDigest;

public class SecurityUtil {
	
	public static String doMD5Hash( final String value ) {
		try {
			byte[] bytesOfMessage = value.getBytes( "UTF-8" );
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			byte[] thedigest = md.digest( bytesOfMessage );
			
			StringBuffer sb = new StringBuffer();
			for (byte element : thedigest) {
				sb.append( Integer.toString( (element & 0xff) + 0x100, 16 ).substring( 1 ) );
			}
			
			return sb.toString();
		}
		catch (Exception e) {
			throw new RuntimeException( "Error occured at MD5 hash" );
		}
	}
	
}
