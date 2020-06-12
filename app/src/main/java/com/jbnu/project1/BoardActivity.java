package com.jbnu.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BoardActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewItemClickListener.OnItemClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mPostRecyclerView;

    private PostAdapter mAdapter;
    private List<Post> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        mPostRecyclerView = findViewById(R.id.main_recyclerview);

        mDatas = new ArrayList<>();

        mAdapter = new PostAdapter(mDatas);
        mPostRecyclerView.setAdapter((mAdapter));

        findViewById(R.id.main_post_edit).setOnClickListener(this);

        mPostRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this,mPostRecyclerView,this));

        Button profile = (Button)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(PostID.post)
                .orderBy(PostID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@NonNull QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if(queryDocumentSnapshots!=null){
                                mDatas.clear();
                                for(DocumentSnapshot snap:  queryDocumentSnapshots.getDocuments()){
                                    Map<String,Object> shot = snap.getData();
                                    String email = String.valueOf(shot.get(PostID.email));
                                    String documentId = String.valueOf(shot.get(PostID.documentId));
                                    String title = String.valueOf(shot.get(PostID.title));
                                    String contents = String.valueOf(shot.get(PostID.contents));
                                    Post data = new Post(email,documentId,title,contents);
                                    mDatas.add(data);
                                }
                                mAdapter = new PostAdapter(mDatas);
                                mPostRecyclerView.setAdapter(mAdapter);
                            }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,PostActivity.class));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,Post2Activity.class);
        intent.putExtra(PostID.documentId,mDatas.get(position).getDocumentId());
                startActivity(intent);
    }

    @Override
    public void onItemLongClikck(View view, final int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("삭제하시겠습니까");
        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStore.collection(PostID.post).document(mDatas.get(position).getDocumentId()).delete();
                Toast.makeText(BoardActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BoardActivity.this, "취소", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("삭제 알림");
        dialog.show();

    }

}
