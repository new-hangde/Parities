package com.zxy.parities.service;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 汇率服务
 * Created by zxy on 2016/7/20.
 */
public class CurrencyService {
    public void getCurrency() {
    String httpUrl = "http://apis.baidu.com/apistore/currencyservice/type";
    String httpArg = "";
    String jsonResult = request(httpUrl, httpArg);
    System.out.println(jsonResult);
}
    public static String getParties(String from,String form1){
        String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency";
        String httpArg = "fromCurrency="+from+"&toCurrency="+form1+"&amount=2";
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }


    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "4bae42cf558ddfa94f5e262170eb25ce");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static double getCurrencyNumber(String result) {
        Log.i("", result + "");
        double currency = 0;
        if (!TextUtils.isEmpty(result)) {
            int start = result.indexOf("currency");
            int finish = result.indexOf("amount");
            String temp = result.substring(start, finish);
            int s =temp.indexOf(":");
            int f =temp.indexOf(",");
            String t =temp.substring(s+1,f);
            t = t.replace("\"","");
            currency = Double.parseDouble(t);
        }
        return currency;
    }

}
