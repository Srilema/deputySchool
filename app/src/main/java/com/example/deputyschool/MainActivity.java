package com.example.deputyschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.deputyschool.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchObserver, AdapterView.OnItemClickListener {
    private SearchView searchView;
    private ListView listView;
    private DeputyAdapter deputyAdapter;
    private ArrayList<Deputy> deputies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchViewMain);
        searchView.setOnQueryTextListener(this);

        listView = findViewById(R.id.listViewMain);
        deputies = new ArrayList<Deputy>();
        deputyAdapter = new DeputyAdapter(deputies, this);
        listView.setAdapter(deputyAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        deputies = new ArrayList<Deputy>();
        ApiServices.searchRequest(this, query, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onReceiveDeputyInfo(Deputy deputy) {
        if(!deputies.contains(deputy)){
            deputies.add(deputy);
            deputyAdapter.setDeputies(deputies);
            deputyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent(MainActivity.this, DeputyActivity.class);
        intent.putExtra("deputy", deputies.get(position));
        startActivity(intent);
    }
}