package com.example.rupareldemo;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class DB {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void addNotice(Notice n){
        Map<String, Object> data = n.toMap();
        data.put("ts", FieldValue.serverTimestamp());
        db
                .collection("notices")
                .add(data);
    }

    public void setUpListener(final onNewNotice onNewNotice){
        db
                .collection("notices")
                .orderBy("ts", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (!value.isEmpty()){
                            DocumentSnapshot docSnap = value.getDocuments().get(0);
                            Notice notice = docSnap.toObject(Notice.class);
                            onNewNotice.newNotice(notice);
                        }

                    }
                });
    }

    public interface onNewNotice {
         void newNotice(Notice notice);
    }

}
