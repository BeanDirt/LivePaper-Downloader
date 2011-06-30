package com.beandirt.livepaperdownloader;

public class PhotoSet {

	private final String id;
	private final String name;
	private final String description;
	private final int width;
	private final int height;
	
	public PhotoSet(String id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
		
		String[] dimsArray = name.split("x");
		this.width = Integer.valueOf(dimsArray[0]);
		this.height = Integer.valueOf(dimsArray[1]);
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
}
