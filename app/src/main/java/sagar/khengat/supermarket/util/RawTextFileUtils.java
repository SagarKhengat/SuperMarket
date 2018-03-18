package sagar.khengat.supermarket.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class can read in text files from the raw directory
 */

public class RawTextFileUtils {

    public static String readRawTextFile(Context context, int id) {

        //Searching for the file in the raw folder
        InputStream inputStream = context.getResources().openRawResource(id);

        //Reading the file
        InputStreamReader in = new InputStreamReader(inputStream);
        BufferedReader buf = new BufferedReader(in);

        String line;

        StringBuilder text = new StringBuilder();
        try {

            while ((line = buf.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }

            inputStream.close();
        } catch (IOException e) {
            return null;

        }

        return text.toString();

    }


}
