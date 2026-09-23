package com.example.designpatterns.facade;

public class Popcorn {
	
	private static Popcorn instance = new Popcorn();
	
	public static Popcorn getInstance() {
		return instance;
	}
	private Popcorn(){

	}
	public void on() {
		System.out.println(" popcorn on ");
	}
	
	public void off() {
		System.out.println(" popcorn ff ");
	}
	
	public void pop() {
		System.out.println(" popcorn is poping  ");
	}
}
