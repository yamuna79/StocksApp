package com.example.yamuna.newappstocks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button button;
    ArrayList<String> array;
    String str;
    String[] s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        array = new ArrayList<String>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString().toUpperCase();

                if(array.contains(input)){
                    Toast.makeText(getBaseContext(),"Symbol already present",Toast.LENGTH_SHORT).show();
                }else if(input == null || input.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Field is empty",Toast.LENGTH_SHORT).show();
                }else{
                    array.add(input);
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,array);
                    listView.setAdapter(adapter);
                    ((EditText)findViewById(R.id.editText)).setText(" ");
                }
            }
        });

        s = new String[array.size()];
        s = array.toArray(s);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        str = (String) listView.getItemAtPosition(i);
                        Intent intent = new Intent(MainActivity.this,Display.class);
                        intent.putExtra("symbol",str);
                        startActivity(intent);
            }
        });

    }
}
