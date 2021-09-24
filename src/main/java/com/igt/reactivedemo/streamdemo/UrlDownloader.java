package com.igt.reactivedemo.streamdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public abstract class UrlDownloader {

    public static String downloadUrl(String url) {
        try {
            StringBuilder result = new StringBuilder();

            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);

            String line;
            try (InputStream is = urlConnection.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
