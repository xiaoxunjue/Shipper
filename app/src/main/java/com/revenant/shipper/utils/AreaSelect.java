package com.revenant.shipper.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.revenant.shipper.bean.AreaBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AreaSelect {

    public static AreaBeans getArea(Context context) {
        AreaBeans listBean;
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("area.json");
            String teachersData = convertStraemToString(inputStream);
            Gson gson = new Gson();
            listBean = gson.fromJson(teachersData, AreaBeans.class);
            return listBean;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String convertStraemToString(InputStream inputStream) {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}
