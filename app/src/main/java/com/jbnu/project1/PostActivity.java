package com.jbnu.project1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText mTitle, mContents,mEmail;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle = findViewById(R.id.post_title_edit);
        mContents = findViewById(R.id.post_contents_edit);

        findViewById(R.id.post_save_button).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String title = mTitle.getText().toString().trim();
        String content = mContents.getText().toString().trim();

        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mAuth.getCurrentUser() != null){
            String postId=mStore.collection(PostID.post).document().getId();
            Map<String,Object> data = new HashMap<>();
            data.put(PostID.documentId,postId);
            data.put(PostID.email,mAuth.getCurrentUser().getEmail().toString());
            data.put(PostID.title,mTitle.getText().toString());
            data.put(PostID.contents,mContents.getText().toString());
            data.put(PostID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(PostID.post).document(postId).set(data, SetOptions.merge());
            finish();
        }

    }
}
