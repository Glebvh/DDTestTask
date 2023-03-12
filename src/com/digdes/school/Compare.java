package com.digdes.school;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compare {

    public boolean longCompare(Long value1, String comp, Long value2) {
        return switch (comp) {
            case ">" -> value1 > value2;
            case ">=" -> value1 >= value2;
            case "<" -> value1 < value2;
            case "<=" -> value1 <= value2;
            case "!=" -> !Objects.equals(value1, value2);
            case "=" -> Objects.equals(value1, value2);
            default -> throw new InputFormatException(comp + " is not a comparator");
        };
    }

    public boolean doubleCompare(Double value1, String comp, Double value2) {
        return switch (comp) {
            case ">" -> value1 > value2;
            case ">=" -> value1 >= value2;
            case "<" -> value1 < value2;
            case "<=" -> value1 <= value2;
            case "!=" -> !Objects.equals(value1, value2);
            case "=" -> Objects.equals(value1, value2);
            default -> throw new InputFormatException(comp + " is not a comparator");
        };
    }

    public boolean stringsCompare(String value1, String comp, String value2) {
        String value2clean = value2.replaceAll("%", "");
        switch (comp.toLowerCase()) {
            case "=":
                return value1.equals(value2);
            case "!=":
                return !value1.equals(value2);
            case "like":
                if (value2.matches("[а-яА-Я\\w]+")) {
                    return value1.equals(value2);
                } else if (value2.matches("[а-яА-Я\\w]+%")) {
                    Pattern pattern = Pattern.compile("^" + value2clean);
                    Matcher matcher = pattern.matcher(value1);
                    return matcher.find();
                } else if (value2.matches("%[а-яА-Я\\w]+")) {
                    Pattern pattern = Pattern.compile(value2clean + "$");
                    Matcher matcher = pattern.matcher(value1);
                    return matcher.find();
                } else if (value2.matches("%[а-яА-Я\\w]+%")) {
                    Pattern pattern = Pattern.compile(value2clean);
                    Matcher matcher = pattern.matcher(value1);
                    return matcher.find();
                }
            case "ilike":
                if (value2.matches("[а-яА-Я\\w]+")) {
                    return value1.equalsIgnoreCase(value2);
                } else if (value2.matches("[а-яА-Я\\w]+%")) {
                    Pattern pattern = Pattern.compile("^" + value2clean.toLowerCase());
                    Matcher matcher = pattern.matcher(value1.toLowerCase());
                    return matcher.find();
                } else if (value2.matches("%[а-яА-Я\\w]+")) {
                    Pattern pattern = Pattern.compile(value2clean.toLowerCase() + "$");
                    Matcher matcher = pattern.matcher(value1.toLowerCase());
                    return matcher.find();
                } else if (value2.matches("%[а-яА-Я\\w]+%")) {
                    Pattern pattern = Pattern.compile(value2clean.toLowerCase());
                    Matcher matcher = pattern.matcher(value1.toLowerCase());
                    return matcher.find();
                }
        }
        throw new InputFormatException(comp + " is not a comparator for string");
    }

    public boolean booleanCompare(Boolean value1, String comp, Boolean value2) {
        return switch (comp.toLowerCase()) {
            case "=" -> value1 == value2;
            case "!=" -> value1 != value2;
            default -> throw new InputFormatException(comp + " is not a comparator for boolean");
        };
    }

}
