package com.damaru.imagelabeller;

public class ImageLabel {
	private String description;
	private float score;

	public ImageLabel(String description, float score) {
		this.description = description;
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
