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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("알림").child("철학").child("2");
        if(i==1) {
            reference = database.getReference().child("알림").child("철학").child("1");
            i++;
        }
        else if(i==2){
            reference = database.getReference().child("알림").child("역사").child("2");
            i++;
        }
        else if(i==3) {
            reference = database.getReference().child("알림").child("인문학  ").child("1");
            i++;
        }
        else if(i==4){
            reference = database.getReference().child("알림").child("음악").child("1");
            i++;
        }
        else if(i==5){
            reference = database.getReference().child("알림").child("사회").child("1");
            i++;
        }
        else if(i==6){
            reference = database.getReference().child("알림").child("넌센스").child("1");
            i++;
        }
        else if(i==7) {
            reference = database.getReference().child("알림").child("일요일").child("1");
            i=1;
        }

        databaseOpenHelper.weUpdate(i);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Knewledge knewledge = dataSnapshot.getValue(Knewledge.class);

                databaseOpenHelper.knUpdate(knewledge.getcontent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
