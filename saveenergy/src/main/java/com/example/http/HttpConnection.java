package com.example.http;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * Created by mc on 2016/3/21.
 */
public class HttpConnection {

    private static String TAG="HttpConnection";
    public  static String ALLurl="http://api.cd6969.com/api/gw/domb/?did=80026&key=adfsss83&ctrl=L1ON&t=2";
    public  static String url="http://api.cd6969.com/api/gw/domb/?";

    public static void postMes(final Map<String,String> params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL cUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) cUrl.openConnection();
                    StringBuffer sBuffer=new StringBuffer();

                    if(params!=null){
                        connection.setDoOutput(true);
                        connection.setRequestMethod("POST");
                        OutputStream out = connection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                        for(Map.Entry s:params.entrySet()) {
                            sBuffer.append(s.getKey()).append("=").append(s.getValue()).append("&");
                        }
                        String s = sBuffer.deleteCharAt(sBuffer.toString().length() - 1).toString();
                        Log.d("HttpPostData", s);
                        writer.write(s);
                        writer.close();
                        sBuffer=null;

                    }
                    Log.d(TAG,"begin Connect");
                    connection.connect();
                    InputStream is = connection.getInputStream();//这句才是真正连接



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


public static  void getMes() {
    postMes(null);
}
}
