package app.unirio.makeyourparty.Extras;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Gabriel on 04/12/2016.
 */
public class Connection {
    private static Connection conn = null;

    public static Connection getInstance(){
        if(conn == null)
        {
            conn= new Connection();
        }
        return  conn;
    }



    public Connection(){}

    public String Response(String service, String login, String password) {
        try {
            String link = "http://aquacraft.com.br/MakeYourParty/main.php";
            URL url = new URL(link);
            String data  = URLEncoder.encode("service", "UTF-8") + "=" +
                    URLEncoder.encode(service, "UTF-8");
            data += "&" + URLEncoder.encode("login", "UTF-8") + "=" +
                    URLEncoder.encode(login, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");
            URLConnection conn = url.openConnection();
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return reader.toString();
        } catch (Exception e) {
            Log.i("Connection", e.toString());
        }

        return null;
    }
}
