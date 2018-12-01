package com.example.macaron.mobile_project.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.macaron.mobile_project.Adapter.ListViewAdapter;
import com.example.macaron.mobile_project.Class.Knewledge;
import com.example.macaron.mobile_project.Method.ChangeModule;
import com.example.macaron.mobile_project.Method.DBModule;
import com.example.macaron.mobile_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EconomyActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_economy_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final ListView listView;
        final ListViewAdapter adapter;
        final ArrayList<Knewledge> knewledges = new ArrayList<>();

        adapter = new ListViewAdapter();

        listView = (ListView)findViewById(R.id.listView_economy);
        listView.setAdapter(adapter);

        final DBModule dbModule = new DBModule();
        dbModule.openReadDB(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("지식").child("사회");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Knewledge knewledge = snapshot.getValue(Knewledge.class);
                    knewledges.add(knewledge);
                    boolean is_read = dbModule.executeRawQuery_Read("사회", knewledge.gettitle());
                    adapter.addItem(knewledge.gettitle(), is_read);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeItem(knewledges.get(position).gettitle(), true);
                adapter.notifyDataSetChanged();
                if(!dbModule.executeRawQuery_Read("사회", knewledges.get(position).gettitle())) {
                    dbModule.insert_Read("사회", knewledges.get(position).gettitle());
                }

                ChangeModule changeModule = new ChangeModule();
                changeModule.chActi(EconomyActivity.this, KnewledgeActivity.class, "지식", "사회", knewledges.get(position).gettitle(), knewledges.get(position).getcontent());
            }
        });

    }

    public void clickMenuEco(View view){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_economy_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ChangeModule ca = new ChangeModule();
        int id = item.getItemId();
        Activity preActivity = EconomyActivity.this;
        Class curActivity = EconomyActivity.class;
        Class nextActivity = null;
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_economy_layout);

        if(id == R.id.today_knew){
            nextActivity = MainActivity.class;
        }else if(id == R.id.philosophy){
            nextActivity = PhilosophyActivity.class;
        }else if(id == R.id.humanities){
            nextActivity = HumanitiesActivity.class;
        }else if(id == R.id.history){
            nextActivity = HistoryActivity.class;
        }else if(id == R.id.economy){
            nextActivity = EconomyActivity.class;
        }else if(id == R.id.music){
            nextActivity = MusicActivity.class;
        }else if(id == R.id.bookmark){
            nextActivity = BookMarkActivity.class;
        }else if(id == R.id.settings){
            nextActivity = SettingActivity.class;
        }else if(id == R.id.info){
            nextActivity = InformActivity.class;
        }
        ca.chActi(preActivity, nextActivity, curActivity, drawer);
        finish();
        return false;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_economy_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
