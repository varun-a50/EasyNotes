package com.example.easynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easynotes.databinding.ActivityMainBinding;
import com.example.easynotes.databinding.ActivityMainNoteBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainNote extends AppCompatActivity {

    EditText txt1,txt2;

    MyAdapter myAdapter;
    ArrayList<Note> userArrayList;
    FirebaseFirestore db;
    ActivityMainNoteBinding activityMainNoteBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_note);

        MainNoteViewModel mainNoteViewModel = new MainNoteViewModel();

        activityMainNoteBinding.setMainNoteViewModel(mainNoteViewModel);


        txt1 = findViewById(R.id.title);
        userArrayList = new ArrayList<Note>();
        txt2 = findViewById(R.id.details);
        myAdapter = new MyAdapter(MainNote.this, userArrayList);
        db = FirebaseFirestore.getInstance();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String title = txt1.getText().toString().trim().toUpperCase();
        String details = txt2.getText().toString().trim();
        if (title.isEmpty() || details.isEmpty()){

        }else{
            Map<String,Object> User = new HashMap<>();
            User.put("title",title);
            User.put("details", details);

            db.collection("Notes")
                    .add(User)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(MainNote.this,"Successful",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainNote.this,"Fail",Toast.LENGTH_SHORT).show();
                }
            });
        }
        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}
