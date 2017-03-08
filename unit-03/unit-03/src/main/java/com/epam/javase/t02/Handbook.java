package com.epam.javase.t02;

import java.util.*;

/**
 * Represents a simple handbook that contains questions and answers.
 *
 * Created by aivanov on 3/7/2017.
 */

public class Handbook {

    private ResourceBundle bundle;

    public Handbook() {
        this(Locale.getDefault());
    }

    public Handbook(Locale locale) {
        changeLocale(locale);
    }

    public Set<String> getQuestionsIDs() {
        return bundle.keySet();
    }

    public String getQuestionByID(String id) {
        return bundle.getString(String.valueOf(id)).split(";")[0];
    }

    public String getAnswer(String id) {
        return bundle.getString(String.valueOf(id)).split(";")[1];
    }

    public void changeLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("com.epam.javase.t02.handbook", locale);
    }
}
