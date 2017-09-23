package com.a_hamoud.listview_sqlserver;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView TV_Header;
    Typeface font;
    Button btn_Get;
    ListView LV_Country;
    SimpleAdapter ADAhere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TV_Header=(TextView) findViewById(R.id.TV_Header);
        LV_Country=(ListView)findViewById(R.id.LV_Country);
        btn_Get=(Button)findViewById(R.id.btn_Get);

        btn_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String,String>> MyData = null;
                GetData mydata =new GetData();
                MyData= mydata.doInBackground();
                String[] fromwhere = { "ID","Country","Capital" };

                int[] viewswhere = {R.id.lblID , R.id.lblcountryname,R.id.lblCapitalCity};

                ADAhere = new SimpleAdapter(MainActivity.this, MyData,R.layout.listtemplate, fromwhere, viewswhere);

                LV_Country.setAdapter(ADAhere);

            }
        });


        LV_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> obj=(HashMap<String,Object>)ADAhere.getItem(position);
                String ID=(String)obj.get("A");
                Toast.makeText(MainActivity.this, ID, Toast.LENGTH_SHORT).show();

            }
        });






    }


}
