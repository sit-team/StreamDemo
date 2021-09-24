package com.igt.reactivedemo.streamdemo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommonEnglishWordsProvider {

    public static Set<String> commonEnglishWords() {
        try {
            var fileText = Files.readString(
                    Paths.get(Objects.requireNonNull(CommonEnglishWordsProvider.class.getResource("/common-english-words.csv")).toURI()), Charset.defaultCharset());
            return Arrays.stream(fileText.split(",")).collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
