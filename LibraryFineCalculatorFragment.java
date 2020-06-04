package com.example.lotus;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lotus.R;

import java.util.Calendar;
import java.util.Objects;

public class LibraryFineCalculatorFragment extends Fragment {

    public LibraryFineCalculatorFragment() {
    }

    private int s_y = 0;
    private int s_m = 0;
    private int s_d = 0;
    private int f_y = 0;
    private int f_m = 0;
    private int f_d = 0;

    private TextView borrowDate;
    private TextView returnDate;
    private TextView fine_amt;
    private EditText rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_fine_calculator, container, false);

        borrowDate = view.findViewById(R.id.borrow_choose);
        returnDate = view.findViewById(R.id.return_choose);
        fine_amt = view.findViewById(R.id.fine_amount);
        rate = view.findViewById(R.id.rate_per_day);

        borrowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                                s_y = yyyy;
                                s_m = mm;
                                s_d = dd;
                                borrowDate.setText(s_d + "/" + s_m + "/" + s_y);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                                f_y = yyyy;
                                f_m = mm;
                                f_d = dd;
                                returnDate.setText(f_d + "/" + f_m + "/" + f_y);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        view.findViewById(R.id.fine_calc).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (s_y == 0 || f_d == 0) {
                    fine_amt.setText("Choose Date !!");
                    Toast.makeText(getContext(), "Choose Date !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int diff = getDifference(s_y, s_m, s_d, f_y, f_m, f_d);
                if (rate.getText().toString().isEmpty()) {
                    fine_amt.setText("Empty Rate !!");
                    Toast.makeText(getContext(), "Empty Rate !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (diff >= 0) {
                    double amt = Double.parseDouble(rate.getText().toString()) * diff;
                    amt = Math.round(amt * 100.0) / 100.0;
                    fine_amt.setText("Rs. " + amt);
                } else {
                    fine_amt.setText("");
                    Toast.makeText(getContext(), "Return before Borrow !", Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.findViewById(R.id.fine_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fine_amt.setText("");
                s_y = 0;
                s_m = 0;
                s_d = 0;
                f_d = 0;
                f_m = 0;
                f_y = 0;
                borrowDate.setText("Choose Date");
                returnDate.setText("Choose Date");
            }
        });


        return view;
    }

    private final int[] monthDays = {31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};


    private int countLeapYears(int yy, int mm) {
        int years = yy;
        if (mm <= 2) {
            years--;
        }


        return years / 4 - years / 100 + years / 400;
    }

    //date: 1-2
    private int getDifference(int yy1, int mm1, int dd1, int yy2, int mm2, int dd2) {


        int n1 = yy1 * 365 + dd1;


        for (int i = 0; i < mm1 - 1; i++) {
            n1 += monthDays[i];
        }


        n1 += countLeapYears(yy1, mm1);


        int n2 = yy2 * 365 + dd2;
        for (int i = 0; i < mm2 - 1; i++) {
            n2 += monthDays[i];
        }
        n2 += countLeapYears(yy2, mm2);


        return (n2 - n1);
    }

}