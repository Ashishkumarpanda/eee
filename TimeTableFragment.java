package com.example.lotus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lotus.R;
import com.example.lotus.TimetableDatabaseHandler;

public class TimeTableFragment extends Fragment {

    public TimeTableFragment() {
    }

    private final EditText[][] subs = new EditText[6][8];

    @Override
    public void onStart() {
        super.onStart();
        String[][] temp = new TimetableDatabaseHandler(getContext()).getAllData();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 8; j++)
                subs[i][j].setText(temp[i][j]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        {
            subs[0][0] = view.findViewById(R.id.mon1);
            subs[0][1] = view.findViewById(R.id.mon2);
            subs[0][2] = view.findViewById(R.id.mon3);
            subs[0][3] = view.findViewById(R.id.mon4);
            subs[0][4] = view.findViewById(R.id.mon5);
            subs[0][5] = view.findViewById(R.id.mon6);
            subs[0][6] = view.findViewById(R.id.mon7);
            subs[0][7] = view.findViewById(R.id.mon8);

            subs[1][0] = view.findViewById(R.id.tue1);
            subs[1][1] = view.findViewById(R.id.tue2);
            subs[1][2] = view.findViewById(R.id.tue3);
            subs[1][3] = view.findViewById(R.id.tue4);
            subs[1][4] = view.findViewById(R.id.tue5);
            subs[1][5] = view.findViewById(R.id.tue6);
            subs[1][6] = view.findViewById(R.id.tue7);
            subs[1][7] = view.findViewById(R.id.tue8);

            subs[2][0] = view.findViewById(R.id.wed1);
            subs[2][1] = view.findViewById(R.id.wed2);
            subs[2][2] = view.findViewById(R.id.wed3);
            subs[2][3] = view.findViewById(R.id.wed4);
            subs[2][4] = view.findViewById(R.id.wed5);
            subs[2][5] = view.findViewById(R.id.wed6);
            subs[2][6] = view.findViewById(R.id.wed7);
            subs[2][7] = view.findViewById(R.id.wed8);

            subs[3][0] = view.findViewById(R.id.thu1);
            subs[3][1] = view.findViewById(R.id.thu2);
            subs[3][2] = view.findViewById(R.id.thu3);
            subs[3][3] = view.findViewById(R.id.thu4);
            subs[3][4] = view.findViewById(R.id.thu5);
            subs[3][5] = view.findViewById(R.id.thu6);
            subs[3][6] = view.findViewById(R.id.thu7);
            subs[3][7] = view.findViewById(R.id.thu8);

            subs[4][0] = view.findViewById(R.id.fri1);
            subs[4][1] = view.findViewById(R.id.fri2);
            subs[4][2] = view.findViewById(R.id.fri3);
            subs[4][3] = view.findViewById(R.id.fri4);
            subs[4][4] = view.findViewById(R.id.fri5);
            subs[4][5] = view.findViewById(R.id.fri6);
            subs[4][6] = view.findViewById(R.id.fri7);
            subs[4][7] = view.findViewById(R.id.fri8);

            subs[5][0] = view.findViewById(R.id.sat1);
            subs[5][1] = view.findViewById(R.id.sat2);
            subs[5][2] = view.findViewById(R.id.sat3);
            subs[5][3] = view.findViewById(R.id.sat4);
            subs[5][4] = view.findViewById(R.id.sat5);
            subs[5][5] = view.findViewById(R.id.sat6);
            subs[5][6] = view.findViewById(R.id.sat7);
            subs[5][7] = view.findViewById(R.id.sat8);
        }

        view.findViewById(R.id.timetable_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimetableDatabaseHandler handler = new TimetableDatabaseHandler(getContext());
                String[][] subStrings = new String[6][8];
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 8; j++) {
                        subStrings[i][j] = subs[i][j].getText().toString();
                    }
                }
                boolean res = handler.updateData(subStrings);
                if (res) {
                    Toast.makeText(getContext(), "Update Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.timetable_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 6; i++)
                    for (int j = 0; j < 8; j++)
                        subs[i][j].setText("");
                TimetableDatabaseHandler handler = new TimetableDatabaseHandler(getContext());
                String[][] subStrings = new String[6][8];
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 8; j++) {
                        subStrings[i][j] = subs[i][j].getText().toString();
                    }
                }
                boolean res = handler.updateData(subStrings);
                if (res) {
                    Toast.makeText(getContext(), "Reset Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Reset Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}