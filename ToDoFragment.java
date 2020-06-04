package com.example.lotus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lotus.R;
import com.example.lotus.ToDoFileHandler;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    public ToDoFragment() {
    }

    private EditText editText;
    private ListView listView;

    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);


        editText = view.findViewById(R.id.todo_edittext);
        listView = view.findViewById(R.id.todo_listview);
        items = ToDoFileHandler.readData(getContext());

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater1 =
                        (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                @SuppressLint({"InflateParams", "ViewHolder"}) View view1 = inflater1.inflate(R.layout.todo_list_item, null);
                TextView textView = view1.findViewById(R.id.list_item_textview);
                textView.setText(items.get(position));
                return view1;
            }
        };
        listView.setAdapter(adapter);

        view.findViewById(R.id.todo_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Empty Item !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.add(editText.getText().toString());
                ToDoFileHandler.writeData(items, getContext());
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });

        view.findViewById(R.id.todo_reset_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                items.clear();
                ToDoFileHandler.writeData(items, getContext());
                Toast.makeText(getContext(), "All Records cleared !!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}