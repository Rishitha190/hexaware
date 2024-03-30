package com.entity;

public class Gallery {
	private int galleryId;
	private String name;
	private String description;
	private String location;
	private int curator;
	private String openingHours;
	public Gallery(int i, String string, String string2, String string3, int j, String string4) {
		this.galleryId = i;
		this.name = string;
		this.description = string2;
		this.location = string3;
		this.curator = j;
		this.openingHours = string4;
	}
	public int getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public int getCurator() {
		return curator;
	}
	public void setCurator(int curator) {
		this.curator = curator;
	}
}
