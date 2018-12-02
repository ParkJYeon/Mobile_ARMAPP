package com.example.macaron.mobile_project.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.macaron.mobile_project.Adapter.ListViewAdapter;
import com.example.macaron.mobile_project.Class.Bookmark;
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

public class BookMarkActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{

    boolean is;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_bookmark_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);
        is = dbModule.executeRawQuery_Bookmark(1);

        ListView listView = (ListView)findViewById(R.id.listView_bookmark);
        TextView txtNodata = (TextView)findViewById(R.id.textNOdata);

        if(is){
            listView.setVisibility(View.VISIBLE);
            txtNodata.setVisibility(View.INVISIBLE);

            final ListViewAdapter adapter = new ListViewAdapter();
            final ArrayList<Knewledge> knewledges = new ArrayList<>();


            final ArrayList<Bookmark> bookmarks = dbModule.executeRawQuery_Bookmarklist();
            listView.setAdapter(adapter);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference;

            for(int i = 0 ; i < bookmarks.size() ; i++){
                final String title = bookmarks.get(i).getTitle();
                reference = database.getReference().child(bookmarks.get(i).getClassi()).child(bookmarks.get(i).getThema());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Knewledge knewledge = snapshot.getValue(Knewledge.class);
                            if(knewledge.gettitle().equals(title)) {
                                knewledges.add(knewledge);
                                adapter.addItem(knewledge.gettitle(), true);
                            }
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
                        ChangeModule changeModule = new ChangeModule();
                        changeModule.chActi(BookMarkActivity.this, KnewledgeBookmarkActivity.class, bookmarks.get(position).getClassi(), bookmarks.get(position).getThema(), knewledges.get(position).gettitle(), knewledges.get(position).getcontent());
                    }
                });

            }
        }else{
            listView.setVisibility(View.INVISIBLE);
            txtNodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ChangeModule ca = new ChangeModule();
        int id = item.getItemId();
        Activity preActivity = BookMarkActivity.this;
        Class curActivity = BookMarkActivity.class;
        Class nextActivity = null;
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_bookmark_layout);

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

    public void clickMenuBookmark(View view){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_bookmark_layout);
        drawer.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_bookmark_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}
