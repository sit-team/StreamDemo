package com.igt.reactivedemo.streamdemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.igt.reactivedemo.streamdemo.UrlDownloader.downloadUrl;

public class Main {

    public static void main(String[] args) {
        String hitchUrl = "http://www.clearwhitelight.org/hitch/hhgttg.txt";

        var commonEnglishWords = CommonEnglishWordsProvider.commonEnglishWords();
        var frequencies = Arrays.stream(downloadUrl(hitchUrl)
                        .split("\\W+"))
                .map(String::toLowerCase)
                .filter(w -> !commonEnglishWords.contains(w))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                //.sorted(Map.Entry.comparingByValue())
                //.reduce(Map.entry("", 0L), (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2)
                //.max(Map.Entry.comparingByValue())
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(100)
                .collect(Collectors.toList());

        System.out.println(frequencies);
    }
}
