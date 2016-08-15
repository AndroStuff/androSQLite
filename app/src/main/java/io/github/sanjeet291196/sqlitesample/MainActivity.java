package io.github.sanjeet291196.sqlitesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Refrence to UI objects
    EditText dataText;
    ListView dataList;

    // DBHelper for handling databases
    DBHelper mydb;

    // Adapter to link dataList View and data items from database
    ArrayAdapter<String> adapter;
    // ArrayList to store all data from database
    private ArrayList<String> dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        dataText = (EditText) findViewById(R.id.data_text_view);
        dataList = (ListView) findViewById(R.id.data_list);

        dataItem = mydb.getAllData();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataItem);
        dataList.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    public void addToDatabase(View view) {
        if (!dataText.getText().toString().equals("")) {

            mydb.insertData(dataText.getText().toString());

            dataItem.clear();
            dataItem.addAll(mydb.getAllData());

            adapter.notifyDataSetChanged();
            dataText.setText("");
        }
    }

}
