package com.example.navlist.adapter;


public class MenuModel {
	private String title;
	private int icon;
	private int counter;
	private boolean isGroupTitle;

	public MenuModel(String title, int icon, int counter,
			boolean isGroupTitle) {
		this.title = title;
		this.icon = icon;
		this.counter = counter;
		this.isGroupTitle = isGroupTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isGroupTitle() {
		return isGroupTitle;
	}

	public void setGroupTitle(boolean isGroupTitle) {
		this.isGroupTitle = isGroupTitle;
	}

}
