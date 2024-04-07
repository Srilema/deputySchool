package com.example.deputyschool;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeputyActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName, textViewCirco, textViewGroup;

    private ListView listViewEmails, listViewCollabos, listViewAdresses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputy);
        textViewName = findViewById(R.id.textViewDeputyName);
        textViewCirco= findViewById(R.id.textViewDeputyCirco);
        textViewGroup= findViewById(R.id.textViewDeputyParty);
        listViewEmails = findViewById(R.id.listViewDeputyEmails);
        listViewCollabos = findViewById(R.id.listViewDeputyCollabos);
        listViewAdresses = findViewById(R.id.listViewDeputyAdresses);
        imageView= findViewById(R.id.imageViewDeputy);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Deputy deputy = (Deputy) getIntent().getSerializableExtra("deputy");
        textViewName.setText(deputy.getFirstname()+" "+ deputy.getLastname());
        textViewCirco.setText(deputy.getDepartment()+", "+
                deputy.getNameCirco()+ ", "+ deputy.getNumCirco()+
                (deputy.getNumCirco()==1? "er": "eme")+" circonscription");
        textViewGroup.setText(deputy.getSigle()+" - "+deputy.getParty());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deputy.getEmails());
        listViewEmails.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deputy.getCollabos());
        listViewCollabos.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deputy.getAdresses());
        listViewAdresses.setAdapter(adapter3);
        ApiServices.loadDeputyAvatar(this, deputy.getNameForAvatar(), imageView);
    }
}
