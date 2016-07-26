package com.zxy.parities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zxy.parities.service.CurrencyService;

public class Activity01 extends AppCompatActivity {
    Spinner sp01;
    Spinner sp02;
    EditText ed;
    EditText ed1;
    TextView tv1;
    TextView tv2;
    String ename01,ename02;
    String name01,name02;
    double currency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ed = (EditText) findViewById(R.id.edit);
        ed1 =(EditText) findViewById(R.id.edit1);
        ed.setTextSize(20);
        ed.setHint("请输入金额");
        ed.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(ed.getText().toString()!=null&&!ed.getText().toString().equals("")) {
                    int amount = Integer.parseInt(ed.getText().toString());
                    double temp = currency * amount;
                    ed1.setText(String.valueOf(temp));
                } else {
                    ed1.setText("0");
                }
            }
        });
        /*ed.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int KeyCode, KeyEvent event) {
                ed.setText(ed.getText().toString());
                return false;
            }
        });*/
       ed1.setText("0");
        tv1 = (TextView) this.findViewById(R.id.tv01);
        tv2 = (TextView) this.findViewById(R.id.tv02);
        String string = "数据仅供参考，交易时以银行柜台成交价为准";
        tv2.setTextColor(Color.GRAY);
        tv2.setTextSize(15);
        tv1.setTextSize(18);
        tv1.setTextColor(Color.BLACK);
        tv2.setText(string);
            String content = "1" + name01 + "=" + currency + name02;
            tv1.setText(content);

        sp01 = (Spinner) findViewById(R.id.spinner01);
        sp02 = (Spinner) findViewById(R.id.spinner02);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getListData(), R.layout.pic_item,
                new String[]{"n_pic", "name_pic"}, new int[]{R.id.image_view, R.id.text_view});
        sp01.setAdapter(simpleAdapter);
        sp01.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //parent为一个Map结构的和数据
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                 ename01 =map.get("n_ename").toString();
                name01 =map.get("name_pic").toString();
                Toast.makeText(Activity01.this,
                        map.get("name_pic").toString(), Toast.LENGTH_SHORT).show();
                getPartiesAsync();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        sp02.setAdapter(simpleAdapter);
        sp02.setOnItemSelectedListener(new OnItemSelectedListener() {
            {
                // TODO Auto-generated method stub
            }

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                ename02 = map.get("n_ename").toString();
                name02 = map.get("name_pic").toString();
                Toast.makeText(Activity01.this,
                        map.get("name_pic").toString(), Toast.LENGTH_SHORT).show();
                getPartiesAsync();

            }



            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        sp01.setSelection(0);
        ename01 = (String) ((Map<String,Object>)getListData().get(0)).get("n_ename");
        sp02.setSelection(1);
        ename02 = (String) ((Map<String,Object>)getListData().get(1)).get("n_ename");

    }

    public List<Map<String, Object>> getListData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        //每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("n_pic", R.drawable.china);
        map.put("name_pic", "人民币");
        map.put("n_ename","CNY");
        list.add(map);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("n_pic", R.drawable.canada);
        map2.put("name_pic", "加元");
        map2.put("n_ename","CAD");
        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("n_pic", R.drawable.japan);
        map3.put("name_pic", "日元");
        map3.put("n_ename", "JPY");
        list.add(map3);

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("n_pic", R.drawable.eu);
        map4.put("name_pic", "欧元");
        map4.put("n_ename", "EUR");
        list.add(map4);

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("n_pic", R.drawable.australia);
        map5.put("name_pic", "澳元");
        map5.put("n_ename", "AUD");
        list.add(map5);

        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("n_pic", R.drawable.usa);
        map6.put("name_pic", "美元");
        map6.put("n_ename", "USD");
        list.add(map6);

        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("n_pic", R.drawable.uk);
        map7.put("name_pic", "英镑");
        map7.put("n_ename", "GBP");
        list.add(map7);

        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("n_pic", R.drawable.hongkong);
        map8.put("name_pic", "港元");
        map8.put("n_ename", "HKD");
        list.add(map8);


        return list;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = "1" + name01 + "=" + currency + name02;
            tv1.setText(content);
        }
    };
    private void getPartiesAsync() {
        new Thread(){
                @Override
                public void run(){
                if(ename01 != null && !"".equals(ename01) && ename02 != null && !ename02.equals("")){
                    String jsonResult = CurrencyService.getParties(ename01, ename02);
                    currency = CurrencyService.getCurrencyNumber(jsonResult);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("currency", currency);
                    msg.setData(bundle);

                    handler.sendMessage(msg);
                    super.run();
                }
            }

        }.start();
    }




}


