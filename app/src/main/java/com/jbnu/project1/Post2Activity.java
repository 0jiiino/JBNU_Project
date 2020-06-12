package com.jbnu.project1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Post2Activity extends AppCompatActivity {

    private FirebaseFirestore mstore = FirebaseFirestore.getInstance();
    private TextView mtitletext,mcontenttext,mdate;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        mtitletext = findViewById(R.id.post2_title);
        mcontenttext = findViewById(R.id.post2_content);
        mdate = findViewById(R.id.post2_date);

        Intent getIntent = getIntent();
        id = getIntent.getStringExtra(PostID.documentId);
        Log.e("ITEM DOCUMENT ID:",id);

        mstore.collection(PostID.post)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().isEmpty()){
                            //비어있을경우
                        }else{
                            //있을경우
                        }
                    }
                });



        mstore.collection(PostID.post).document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            if (task.getResult().exists()){
                                if (task.getResult() != null) {
                                    Map<String, Object> snap = task.getResult().getData();
                                    String title = String.valueOf(snap.get(PostID.title));
                                    String content = String.valueOf(snap.get(PostID.contents));

                                    mtitletext.setText(title);
                                    mcontenttext.setText(content);

                                }
                             }else {
                                Toast.makeText(Post2Activity.this,"삭제된 게시글입니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}