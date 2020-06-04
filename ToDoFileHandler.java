package com.example.lotus;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ToDoFileHandler {

    private static final String FILENAME = "todolist.dat";

    public static void writeData(ArrayList<String> items, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.close();
        }catch (Exception e){
            Toast.makeText(context, "Writing to file error !", Toast.LENGTH_SHORT).show();
        }
    }

    public static ArrayList<String> readData(Context context){
        ArrayList<String> items =null;
        try{
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            items = (ArrayList<String>) objectInputStream.readObject();
        }catch (Exception e){
            items = new ArrayList<>();
//            Toast.makeText(context, "Reading from file error!", Toast.LENGTH_SHORT).show();
        }
        return  items;
    }

}