package com.example.yamuna.newappstocks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class Display extends AppCompatActivity {

    String inputstr;
    TextView Company, companyData, Symbol, symbolData, Change, changeData, High, highData, Low, lowData, LastTrade, lastTradeData;
    String cd,sd,chd,hd,ld,ltd;
    public static final String Server_url1 = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
    public static final String Server_url2 = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Company = (TextView) findViewById(R.id.company);
        companyData = (TextView) findViewById(R.id.companydata);
        Symbol = (TextView) findViewById(R.id.symbol);
        symbolData = (TextView) findViewById(R.id.symboldata);
        Change = (TextView) findViewById(R.id.change);
        changeData = (TextView) findViewById(R.id.changedata);
        High = (TextView) findViewById(R.id.high);
        highData = (TextView) findViewById(R.id.highdata);
        Low = (TextView) findViewById(R.id.low);
        lowData = (TextView) findViewById(R.id.lowdata);
        LastTrade = (TextView) findViewById(R.id.lasttrade);
        lastTradeData = (TextView) findViewById(R.id.lasttradedata);



        inputstr = getIntent().getStringExtra("symbol").trim();

        System.out.println("SYMBOL " +inputstr+"get" );
        Test test = new Test();
        test.execute();

    }

    class Test extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... symbols) {

            try {
                URL url = new URL(Server_url1+inputstr+Server_url2);

                System.out.println("url " +url);

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
                String jsonString = reader.readLine();

                JSONObject jsonobj = new JSONObject(jsonString);
                JSONObject queryObj = jsonobj.getJSONObject("query");
                JSONObject resultObj = queryObj.getJSONObject("results");
                JSONObject quoteObj = resultObj.getJSONObject("quote");


                cd = quoteObj.getString("Name");
                sd = quoteObj.getString("Symbol");
                chd = quoteObj.getString("Change");
                hd = quoteObj.getString("DaysHigh");
                ld = quoteObj.getString("DaysLow");
                ltd = quoteObj.getString("LastTradePriceOnly");



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            companyData.setText(cd);
            symbolData.setText(sd);
            changeData.setText(chd);
            highData.setText(hd);
            lowData.setText(ld);
            lastTradeData.setText(ltd);
        }
    }
}
