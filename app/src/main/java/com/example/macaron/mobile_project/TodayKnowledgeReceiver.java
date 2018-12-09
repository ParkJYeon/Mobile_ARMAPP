package com.example.macaron.mobile_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.macaron.mobile_project.Class.Knewledge;
import com.example.macaron.mobile_project.Method.DatabaseOpenHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TodayKnowledgeReceiver extends BroadcastReceiver {

    DatabaseOpenHelper databaseOpenHelper;
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        databaseOpenHelper = new DatabaseOpenHelper(context);

        int i=databaseOpenHelper.getWeek();
        String j=Integer.toString(databaseOpenHelper.getLook());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = null;
        switch (i)
        {
            case 1:
                reference = database.getReference().child("알림").child("철학").child(j);
                databaseOpenHelper.themaUpdate("철학");
                i++;
                break;
            case 2:
                reference = database.getReference().child("알림").child("역사").child(j);
                databaseOpenHelper.themaUpdate("역사");
                i++;
                break;
            case 3:
                reference = database.getReference().child("알림").child("인문학  ").child(j);
                databaseOpenHelper.themaUpdate("인문학  ");
                i++;
                break;
            case 4:
                reference = database.getReference().child("알림").child("음악").child(j);
                databaseOpenHelper.themaUpdate("음악");
                i++;
                break;
            case 5:
                reference = database.getReference().child("알림").child("사회").child(j);
                databaseOpenHelper.themaUpdate("사회");
                i++;
                break;
            case 6:
                reference = database.getReference().child("알림").child("넌센스").child(j);
                databaseOpenHelper.themaUpdate("넌센스");
                i++;
                break;
            case 7:
                reference = database.getReference().child("알림").child("넌센스").child(j);
                databaseOpenHelper.themaUpdate("넌센스");
                i=1;
                break;
        }

        databaseOpenHelper.weUpdate(i);
        databaseOpenHelper.loUpdate(databaseOpenHelper.getLook()+1);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Knewledge knewledge = dataSnapshot.getValue(Knewledge.class);
                databaseOpenHelper.titleUpdate(knewledge.gettitle());
                databaseOpenHelper.knUpdate(knewledge.getcontent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
