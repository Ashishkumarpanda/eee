package com.example.lotus;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lotus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final ArrayList<HashMap<String, Object>> items = new ArrayList<>();
        final PackageManager pm = getActivity().getPackageManager();
        if (getActivity() != null) {
            List<PackageInfo> packs = pm.getInstalledPackages(0);
            for (PackageInfo pi : packs) {
                if (pi.packageName.toLowerCase().contains("calcul")) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("appName", pi.applicationInfo.loadLabel(pm));
                    map.put("packageName", pi.packageName);
                    items.add(map);
                }
            }
        }
        view.findViewById(R.id.btn_calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    if (items.size() >= 1) {
                        String packageName = (String) items.get(0).get("packageName");
                        Intent i = pm.getLaunchIntentForPackage(packageName);
                        if (i != null)
                            startActivity(i);

                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "Calculator not found !!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.btn_voice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION));
            }
        });

        view.findViewById(R.id.btn_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startMillis = System.currentTimeMillis();
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager = getActivity().getBaseContext().getPackageManager();
                Intent alarmClockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
                String[][] clockImpls = {
                        {"HTC Alarm Clock", "com.htc.android.worldclock", "com.htc.android.worldclock.WorldClockTabControl"},
                        {"Standar Alarm Clock", "com.android.deskclock", "com.android.deskclock.AlarmClock"},
                        {"Froyo Nexus Alarm Clock", "com.google.android.deskclock", "com.android.deskclock.DeskClock"},
                        {"Moto Blur Alarm Clock", "com.motorola.blur.alarmclock", "com.motorola.blur.alarmclock.AlarmClock"},
                        {"Samsung Galaxy Clock", "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage"},
                        {"Sony Ericsson Xperia Z", "com.sonyericsson.organizer", "com.sonyericsson.organizer.Organizer_WorldClock"},
                        {"ASUS Tablets", "com.asus.deskclock", "com.asus.deskclock.DeskClock"}

                };
                boolean foundClockImpl = false;
                for (String[] clockImpl : clockImpls) {
                    String vendor = clockImpl[0];
                    String packageName = clockImpl[1];
                    String className = clockImpl[2];
                    try {
                        ComponentName cn = new ComponentName(packageName, className);
                        ActivityInfo aInfo = packageManager.getActivityInfo(cn, PackageManager.GET_META_DATA);
                        alarmClockIntent.setComponent(cn);

                        Log.d("TAG", "Found " + vendor + " --> " + packageName + "/" + className);
                        foundClockImpl = true;
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.d("TAG", vendor + " does not exists");
                    }
                }
                if (foundClockImpl) {
                    startActivity(alarmClockIntent);
                }
            }
        });


        return view;
    }
}
