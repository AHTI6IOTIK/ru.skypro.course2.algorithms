package ru.skypro.course2.algorithms;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> stringCustomArrayList = new CustomArrayList<>();
        stringCustomArrayList.add("1");
        stringCustomArrayList.add("2");
        stringCustomArrayList.add("3");
        stringCustomArrayList.add("4");
        stringCustomArrayList.add("5");
        stringCustomArrayList.add("6");

        stringCustomArrayList.add(0, "100");

        System.out.println(stringCustomArrayList);
    }
}
