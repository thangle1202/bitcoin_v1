package com.example.lvtha.bitcoin;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    ListView listViewShow;
    ArrayList<TienAo> listTienAo;
    adapter_listview adapterListview;
    SwipeRefreshLayout refreshLayout;
    String url="https://api.coinmarketcap.com/v2/ticker/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

//        listTienAo.add(new TienAo("BTC","BitCoin","9000","123456789",
//                "987654321","0.1","-0.2","0.34"));
        //readData(url);
        readData(url);
    }

    private void readData(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listTienAo.clear();
                            JSONObject jsonData=response.getJSONObject("data");
                            Iterator<String> listKey=jsonData.keys();
                            while (listKey.hasNext())
                            {
                                String nextKey=listKey.next();
                                JSONObject json1=jsonData.getJSONObject(nextKey);
                                String viettat= json1.getString("symbol");
                                String tenrieng=json1.getString("name");
                                JSONObject jsonQuotes=json1.getJSONObject("quotes");
                                JSONObject jsonUSD=jsonQuotes.getJSONObject("USD");
                                String price=jsonUSD.getString("price");
                                String market=jsonUSD.getString("market_cap");
                                String volume=jsonUSD.getString("volume_24h");
                                String one=jsonUSD.getString("percent_change_1h");
                                String day=jsonUSD.getString("percent_change_24h");
                                String week=jsonUSD.getString("percent_change_7d");
                                TienAo t=new TienAo(viettat,tenrieng,price,market,volume,one,day,week);
                                listTienAo.add(t);
                                setaDapter();
                            }
                            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Loi roi !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.toString();
                    }
                });
        requestQueue.add(objectRequest);
    }
    private void init()
    {
        listViewShow=findViewById(R.id.lv_bitcoin);
        listTienAo=new ArrayList<>();
        refreshLayout=findViewById(R.id.swipelayout);
        setaDapter();
    }

    private void setaDapter() {
        if(adapterListview==null)
        {
            adapterListview=new adapter_listview(MainActivity.this,R.layout.adapter_listview,listTienAo);
            listViewShow.setAdapter(adapterListview);
        }else {
            adapterListview=new adapter_listview(MainActivity.this,R.layout.adapter_listview,listTienAo);
            listViewShow.setAdapter(adapterListview);
        }
    }

    @Override
    public void onRefresh() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               readData(url);
               refreshLayout.setRefreshing(false);
           }
       },4000);
    }

    class readDataSyn extends AsyncTask<String,Void,String>{
        StringBuilder stringBuilder=new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object=new JSONObject(s);
                JSONObject jsonData=object.getJSONObject("data");
                Iterator<String> listKey=jsonData.keys();
                while (listKey.hasNext())
                {
                    String nextKey=listKey.next();
                    JSONObject json1=jsonData.getJSONObject(nextKey);
                    String viettat= json1.getString("symbol");
                    String tenrieng=json1.getString("name");
                    JSONObject jsonQuotes=json1.getJSONObject("quotes");
                    JSONObject jsonUSD=jsonQuotes.getJSONObject("USD");
                    String price=jsonUSD.getString("price");
                    String market=jsonUSD.getString("market_cap");
                    String volume=jsonUSD.getString("volume_24h");
                    String one=jsonUSD.getString("percent_change_1h");
                    String day=jsonUSD.getString("percent_change_24h");
                    String week=jsonUSD.getString("percent_change_7d");
                    TienAo t=new TienAo(viettat,tenrieng,price,market,volume,one,day,week);
                    listTienAo.add(t);
                    setaDapter();
                }
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
