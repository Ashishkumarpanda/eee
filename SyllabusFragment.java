package com.example.lotus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lotus.R;
import com.example.lotus.SyllabusData;

import java.util.ArrayList;

public class SyllabusFragment extends Fragment {

    public SyllabusFragment() {
    }

    private Spinner branch;
    private Spinner sem;
    private Spinner subj;
    private Spinner option;
    private ArrayList<String> branches;
    private ArrayList<String> sems;
    private ArrayList<String> subjs;
    private ArrayList<String> options;
    private ArrayAdapter<String> branchAdapter;
    private ArrayAdapter<String> semAdapter;
    private ArrayAdapter<String> subjAdapter;
    private ArrayAdapter<String> optionAdapter;

    private TextView syllabusTxt;
    private TextView subjTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);
        branch = view.findViewById(R.id.spinner_branch);
        sem = view.findViewById(R.id.spinner_sem);
        subj = view.findViewById(R.id.spinner_subject);
        option = view.findViewById(R.id.spinner_option);
        subjTitle = view.findViewById(R.id.subj_title);
        syllabusTxt = view.findViewById(R.id.syllabus_txt);

        options = new ArrayList<>();
        options.add("<Select>");
        options.add("Syllabus");
        options.add("Preferred Books");
        optionAdapter = new ArrayAdapter<>(getContext(),
                R.layout.spinner_item_selsected, options);
        optionAdapter.setDropDownViewResource(R.layout.spinner_item);
        option.setAdapter(optionAdapter);

        branches = new ArrayList<>();
        branches.add("CSE");
        branchAdapter = new ArrayAdapter<>(getContext(),
                R.layout.spinner_item_selsected, branches);
        branchAdapter.setDropDownViewResource(R.layout.spinner_item);
        branch.setAdapter(branchAdapter);

        sems = new ArrayList<>();
        sems.add("<select>");
        sems.add("1");
        sems.add("2");
        sems.add("3");
        sems.add("4");
        sems.add("5");
        sems.add("6");
        sems.add("7");
        sems.add("8");
        semAdapter = new ArrayAdapter<>(getContext(),
                R.layout.spinner_item_selsected, sems);
        semAdapter.setDropDownViewResource(R.layout.spinner_item);
        sem.setAdapter(semAdapter);
        option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    switch (sem.getSelectedItemPosition()) {
                        case 1:
                        case 2:
                            setAdapter("PPS");
                            syllabusTxt.setText(SyllabusData.SEM_12_PPS_SYLLABUS);
                            break;
                        case 3:
                            setAdapter("DS");
                            syllabusTxt.setText(SyllabusData.SEM_3_DS_SYLLABUS);
                            break;
                        case 4:
                            setAdapter("FLAT");
                            syllabusTxt.setText(SyllabusData.SEM_4_FLAT_SYLLABUS);
                            break;
                        case 5:
                            setAdapter("DBMS");
                            syllabusTxt.setText(SyllabusData.SEM_5_DBMS_SYLLABUS);
                            break;
                        case 6:
                            setAdapter("CD");
                            syllabusTxt.setText(SyllabusData.SEM_6_CD_SYLLABUS);
                            break;
                        case 7:
                            setAdapter("CNT");
                            syllabusTxt.setText(SyllabusData.SEM_7_CNT_SYLLABUS);
                            break;
                        case 8:
                            setAdapter("VLSI");
                            syllabusTxt.setText(SyllabusData.SEM_8_VLSI_SYLLABUS);
                            break;
                        default:
                            setAdapter(null);
                    }
                } else if (i == 2) {
                    switch (sem.getSelectedItemPosition()) {
                        case 1:
                        case 2:
                            setAdapter("PPS");
                            syllabusTxt.setText(SyllabusData.SEM_12_PPS_BOOKS);
                            break;
                        case 3:
                            setAdapter("DS");
                            syllabusTxt.setText(SyllabusData.SEM_3_DS_BOOKS);
                            break;
                        case 4:
                            setAdapter("FLAT");
                            syllabusTxt.setText(SyllabusData.SEM_4_FLAT_BOOKS);
                            break;
                        case 5:
                            setAdapter("DBMS");
                            syllabusTxt.setText(SyllabusData.SEM_5_DBMS_BOOKS);
                            break;
                        case 6:
                            setAdapter("CD");
                            syllabusTxt.setText(SyllabusData.SEM_6_CD_BOOKS);
                            break;
                        case 7:
                            setAdapter("CNT");
                            syllabusTxt.setText(SyllabusData.SEM_7_CNT_BOOKS);
                            break;
                        case 8:
                            setAdapter("VLSI");
                            syllabusTxt.setText(SyllabusData.SEM_8_VLSI_BOOKS);
                            break;
                        default:
                            setAdapter(null);
                    }
                } else {
                    setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setAdapter(null);
            }
        });

        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sem.getSelectedItemPosition()) {
                    case 1:
                    case 2:
                        setAdapter("PPS");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_12_PPS_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_12_PPS_BOOKS);
                        else setAdapter(null);
                        break;
                    case 3:
                        setAdapter("DS");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_3_DS_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_3_DS_BOOKS);
                        else setAdapter(null);
                        break;
                    case 4:
                        setAdapter("FLAT");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_4_FLAT_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_4_FLAT_BOOKS);
                        else setAdapter(null);
                        break;
                    case 5:
                        setAdapter("DMBS");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_5_DBMS_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_5_DBMS_BOOKS);
                        else setAdapter(null);
                        break;
                    case 6:
                        setAdapter("CD");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_6_CD_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_6_CD_BOOKS);
                        else setAdapter(null);
                        break;
                    case 7:
                        setAdapter("CNT");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_7_CNT_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_7_CNT_BOOKS);
                        else setAdapter(null);
                        break;
                    case 8:
                        setAdapter("VLSI");
                        if (option.getSelectedItemPosition() == 1)
                            syllabusTxt.setText(SyllabusData.SEM_8_VLSI_SYLLABUS);
                        else if (option.getSelectedItemPosition() == 2)
                            syllabusTxt.setText(SyllabusData.SEM_8_VLSI_BOOKS);
                        else setAdapter(null);
                        break;
                    default:
                        setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void setAdapter(String subject) {
        if (subject == null) {
            syllabusTxt.setText("");
            subj.setEnabled(false);
            subjs = new ArrayList<>();
            subjs.add("<No Subject>");
            subjAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_selsected, subjs);
            subjAdapter.setDropDownViewResource(R.layout.spinner_item);
            subj.setAdapter(subjAdapter);
            return;
        }
        subj.setEnabled(true);
        subjs = new ArrayList<>();
        subjs.add(subject);
        subjAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_selsected, subjs);
        subjAdapter.setDropDownViewResource(R.layout.spinner_item);
        subj.setAdapter(subjAdapter);
        subj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(subjs.get(i).equals("<No Subject>")) subjTitle.setText("No Content Available !");
                else subjTitle.setText(subjs.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}