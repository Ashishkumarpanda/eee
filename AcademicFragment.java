package com.example.lotus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.lotus.R;

import java.util.ArrayList;
import java.util.Objects;

public class AcademicFragment extends Fragment {

    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final String MAIL = "mail";
    private static final String NAME = "name";
    private static final String PHNO = "phno";
    private static final String SUBJ = "subj";
    private static final String REGD = "regd";
    private static final String SEM = "sem";

    public AcademicFragment() {
    }

    private Spinner type;
    private EditText name;
    private EditText mail;
    private EditText regd;
    private EditText phno;
    private EditText sem;
    private EditText subj;
    private final ArrayList<String> types = new ArrayList<>();
    private ArrayAdapter<String> typeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_academic, container, false);

        type = view.findViewById(R.id.form_type_spinner);
        name = view.findViewById(R.id.form_name);
        mail = view.findViewById(R.id.form_mail);
        regd = view.findViewById(R.id.form_regd);
        phno = view.findViewById(R.id.form_phno);
        sem = view.findViewById(R.id.form_sem);
        subj = view.findViewById(R.id.form_subj);

        types.add("<Select Registration Type>");
        types.add("Fresh Paper Registration");
        types.add("Back Paper Registration");
        assert getContext()!=null;
        typeAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_selsected, types);
        typeAdapter.setDropDownViewResource(R.layout.spinner_item);

        type.setAdapter(typeAdapter);

        view.findViewById(R.id.form_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), "Select Paper type !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mail.getText().toString().isEmpty() || name.getText().toString().isEmpty() ||
                        phno.getText().toString().isEmpty() || subj.getText().toString().isEmpty() ||
                        sem.getText().toString().isEmpty() || regd.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "One or more Fields Empty !!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth auth = FirebaseAuth.getInstance();
                assert auth.getCurrentUser()!=null;
                String uid = auth.getCurrentUser().getUid();
                DatabaseReference paper = db.getReference(type.getSelectedItemPosition() == 2 ?
                        "back" : "fresh").child(uid);
                paper.child(NAME).setValue(name.getText().toString());
                paper.child(MAIL).setValue(mail.getText().toString());
                paper.child(PHNO).setValue(phno.getText().toString());
                paper.child(SUBJ).setValue(subj.getText().toString());
                paper.child(REGD).setValue(regd.getText().toString());
                paper.child(SEM).setValue(sem.getText().toString());
                Toast.makeText(getContext(), "Submit Successful !", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.form_retrieve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), "Select Paper type !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth auth = FirebaseAuth.getInstance();
                assert auth.getCurrentUser()!=null;
                String uid = auth.getCurrentUser().getUid();
                DatabaseReference paper = db.getReference(type.getSelectedItemPosition() == 2 ?
                        "back" : "fresh").child(uid);
                paper.child(NAME).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            name.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else name.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                paper.child(MAIL).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            mail.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else mail.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                paper.child(PHNO).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            phno.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else mail.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                paper.child(SUBJ).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            subj.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else subj.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                paper.child(REGD).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            regd.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else regd.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                paper.child(SEM).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            sem.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        else {
                            sem.setText("");
                            Toast.makeText(getContext(), "No Record Found !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        view.findViewById(R.id.form_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type.setSelection(0);
                mail.setText("");
                name.setText("");
                phno.setText("");
                regd.setText("");
                sem.setText("");
                subj.setText("");
            }
        });
        return view;
    }
}