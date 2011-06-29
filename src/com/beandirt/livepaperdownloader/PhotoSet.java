package com.beandirt.livepaperdownloader;

public class PhotoSet {

	private final String id;
	private final String name;
	
	public PhotoSet(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
