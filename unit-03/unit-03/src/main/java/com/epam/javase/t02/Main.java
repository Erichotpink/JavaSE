package com.epam.javase.t02;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by aivanov on 3/8/2017.
 */
public class Main {

    public static void main(String[] args) {
        Handbook handbook = new Handbook();
        Set<String> keys = handbook.getQuestionsIDs();
        Scanner sc = new Scanner(System.in);
        ResourceBundle bundle = ResourceBundle.getBundle("com.epam.javase.t02.main", Locale.getDefault());
        while(true) {
            System.out.println("1. " + bundle.getString("list_questions"));
            System.out.println("2. " + bundle.getString("change_locale"));
            System.out.println("3. " + bundle.getString("exit"));
            String i = sc.next();
            switch (i) {
                case "1":
                    while(true) {
                        int index = 1;
                        for (String s : keys) {
                            System.out.println(index + ". " + handbook.getQuestionByID(s));
                            index++;
                        }
                        System.out.println(index + ". " + bundle.getString("back_to_main"));
                        String selection = sc.next();
                        if (keys.contains(selection)) {
                            System.out.println(handbook.getAnswer(selection));
                            System.out.println(bundle.getString("any_key"));
                            sc.next();
                        } else if (selection.equals(String.valueOf(index))) {
                            break;
                        }
                    }
                    break;
                case "2":
                    System.out.println("1. " + bundle.getString("russian"));
                    System.out.println("2. " + bundle.getString("english"));
                    System.out.println("3. " + bundle.getString("exit"));
                    String j = sc.next();
                    switch (j) {
                        case "1":
                            handbook.changeLocale(new Locale("ru"));
                            bundle = ResourceBundle.getBundle("com.epam.javase.t02.main", new Locale("ru"));
                            break;
                        case "2":
                            handbook.changeLocale(Locale.ENGLISH);
                            bundle = ResourceBundle.getBundle("com.epam.javase.t02.main", Locale.ENGLISH);
                            break;
                        default:
                            break;
                    }
                    break;
                case "3":
                    return;
            }

        }
    }

}
