package com.example.designpatterns.facade;

public class Client {
    public static void main(String[] args) {
        HomeTheaterFacade demo=new HomeTheaterFacade();
        demo.ready();

        demo.play();

        demo.pause();

        demo.end();
    }
}
