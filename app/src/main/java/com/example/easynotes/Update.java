package com.example.easynotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easynotes.databinding.ActivityMainBinding;
import com.example.easynotes.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Update extends AppCompatActivity {

    EditText title1,details1;
    MyAdapter myAdapter;
    ArrayList<Note> userArrayList;
    FirebaseFirestore db;
    Note user;
    ActivityUpdateBinding activityUpdateBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateBinding= DataBindingUtil.setContentView(this,R.layout.activity_update);

        UpdateViewModel updateViewModel = new UpdateViewModel();

        activityUpdateBinding.setUpdateViewModel(updateViewModel);



        user = (Note) getIntent().getSerializableExtra("user");
        userArrayList = new ArrayList<Note>();

        title1 = findViewById(R.id.title1);
        details1 = findViewById(R.id.details1);
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter(Update.this,userArrayList);

        title1.setText(user.getTitle());
        details1.setText(user.getDetails());


    }


    public void UpdateNotes() {

        String title = title1.getText().toString().trim().toUpperCase();
        String details = details1.getText().toString().trim();
            if (title.isEmpty() && details.isEmpty()){
                Note n = new Note(title,details);

                db.collection("Notes").document(user.getId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Update.this,"Successful",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Update.this,"Fail",Toast.LENGTH_SHORT).show();
                    }

                });

            }else if(title.isEmpty() || details.isEmpty()) {
                if (title.isEmpty()) {
                    Toast.makeText(Update.this, "You can't leave one field empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (details.isEmpty()) {
                    Toast.makeText(Update.this, "You can't leave one field empty", Toast.LENGTH_LONG).show();
                    details1.requestFocus();
                    return;
                }

            }
            else{
                if(title.isEmpty()){
                    title1.setError("Can't let this field empty");
                    title1.requestFocus();
                    return;
                }
                if (details.isEmpty()){
                    details1.setError("Can't let this field empty");
                    details1.requestFocus();
                    return;
                }
                Note n = new Note(title,details);

                db.collection("Notes").document(user.getId())
                        .update(
                                "title",n.getTitle(),
                                "details", n.getDetails()
                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Update.this,"Fail",Toast.LENGTH_SHORT).show();
                    }

                });

            }

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UpdateNotes();
        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}




