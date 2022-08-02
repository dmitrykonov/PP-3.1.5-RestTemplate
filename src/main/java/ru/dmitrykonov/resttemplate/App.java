package ru.dmitrykonov.resttemplate;

public class App {
    public static void main(String[] args) {
        Communication communication = new Communication();
        System.out.println(communication.deleteUser());
    }
}
