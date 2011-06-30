package com.beandirt.livepaperdownloader;

import java.util.List;

public class Collection {

	private final String id;
	private final String name;
	private final List<PhotoSet> photosets;
	
	public Collection(String id, String name, List<PhotoSet> photosets){
		this.id = id;
		this.name = name;
		this.photosets = photosets;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<PhotoSet> getPhotosets() {
		return photosets;
	}
}