package io.palliassist.palliassistmobile.twilio.util;

/**
 * Created by User on 11/13/2016.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpHelper {

    private static byte[] stringFromInputStream(InputStream is) throws IOException
    {
//        char[] buf = new char[1024];
//        StringBuilder out = new StringBuilder();
//
//        Reader in = new InputStreamReader(is, "UTF-8");
//
//        int bin;
//        while ((bin = in.read(buf, 0, buf.length)) >= 0) {
//            out.append(buf, 0, bin);
//        }
//
//        return out.toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = is.read(data,0,data.length)) != -1) {
            baos.write(data, 0, nRead);
        }
        baos.flush();
        return baos.toByteArray();
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append((line + "\n"));
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return sb.toString();
    }

    public static byte[] httpGet(String url) throws Exception {
        boolean i = true;
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setInstanceFollowRedirects(true);
        HttpURLConnection.setFollowRedirects(true);
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(45000);
        conn.setReadTimeout(30000);
        conn.setDoInput(true);

        int responseCode = conn.getResponseCode();
        if(i) {
        //if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream is = conn.getInputStream();
            byte[] capabilityToken = stringFromInputStream(is);
            is.close();
            conn.disconnect();
            return capabilityToken;
        } else {
            conn.disconnect();
            throw new Exception("Error code from server: " + responseCode);
        }
    }
}
