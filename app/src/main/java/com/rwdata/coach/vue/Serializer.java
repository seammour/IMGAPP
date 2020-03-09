package com.rwdata.coach.vue;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Serializer {

    /**
     *
     * @param fileName
     * @param object
     * @param context
     */
    public static void serialize(String fileName, Object object, Context context){

        try {
            FileOutputStream file = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(object);
            oos.flush();
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerialize(String fileName, Context context) {

        try {
            FileInputStream file = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(file);
            Object object = ois.readObject();
            ois.close();
            return object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
