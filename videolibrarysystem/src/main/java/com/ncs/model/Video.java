package com.ncs.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Video {

	@Id
	@GeneratedValue
	private int id;
	
	private String title;
	
	@Pattern(regexp = "^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$")
	private String link;

	@Size(min = 5, max = 250, message = "Description must be between 5-250 characters")
	private String description;
	private String tag;

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate createdDate;

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate updatedDate;

	public Video() {
	}

	public Video(int id, String title, String link, String description, String tag, LocalDate createdDate, LocalDate updatedDate) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.tag = tag;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getTag() {
		return tag;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
}
