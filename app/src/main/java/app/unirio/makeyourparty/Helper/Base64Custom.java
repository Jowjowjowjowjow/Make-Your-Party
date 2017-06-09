package app.unirio.makeyourparty.Helper;

import android.util.Base64;

/**
 * Created by Jow on 09/06/2017.
 */

/**
 * Classe para criptografar em base64
 */
public class Base64Custom {

    public static String encodeBase64(String text){
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodeBase64(String encodedText){
        return new String(Base64.decode(encodedText,Base64.DEFAULT));
    }
}
