package com.igt.reactivedemo.streamdemo;

import java.util.*;

import reactor.core.publisher.*;
import reactor.util.function.*;

import static com.igt.reactivedemo.streamdemo.UrlDownloader.*;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.*;
import static java.util.function.Function.*;

public class ReactiveMain {

    public static void main(String[] args) {
        String hitchUrl = "http://www.clearwhitelight.org/hitch/hhgttg.txt";
        var commonEnglishWords = CommonEnglishWordsProvider.commonEnglishWords();
        Flux.just(downloadUrl(hitchUrl).split("\\W+"))
            .map(String::toLowerCase)
            .filter(w -> !commonEnglishWords.contains(w))
            .groupBy(identity())
            .flatMap(gf -> Mono.zip(Mono.just(gf.key()), gf.count()), Integer.MAX_VALUE)
            .sort(reverseOrder(comparing(Tuple2::getT2)))
            .take(100)
            .collectList()
            .subscribe(System.out::println);
    }
}
