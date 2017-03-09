package com.epam.javase.t03;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aivanov on 3/8/2017.
 */
public class ArticleParser {

    private final InputStream stream = getClass().getResourceAsStream(
            "/com/epam/javase/t03/article.html");

    private String getContent() {

        try(ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("Windows-1251");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    /**
     * Return a list of the given article sentences containing references to pictures.
     *
     * @return a list of strings
     */
    public List<String> getSentencesWithRef() {
        List<String> data = new ArrayList<>();

        String article = getContent();

        String pattern = "[А-Я](((э\\.д\\.с[.]?)|[^.!?]|)*?([Рр]ис\\.[^)]+?\\)))+((э\\.д\\.с[.]?)|[^.!?])*?[.!?]";

        Pattern p = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(article);

        while(m.find()) {
            data.add(m.group());
        }

        System.out.println("Size: " + data.size());
        return data;
    }

    public static void main(String[] args) {
        ArticleParser temp = new ArticleParser();
        temp.searchForSentencesWithPictures().stream().forEach( s -> {
//            System.out.println("-----------");
            System.out.println(s);
        });
    }

}
