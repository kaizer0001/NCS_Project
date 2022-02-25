package com.ncs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ncs.exception.VideoIDNotFoundException;
import com.ncs.exception.VideoTagNotFoundException;
import com.ncs.exception.VideoTitleNotFoundException;
import com.ncs.model.Video;
import com.ncs.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	public Video getVideoById(int id) {
		return videoRepository.findById(id).orElseThrow(VideoIDNotFoundException::new);
	}

	public List<Video> getVideosByTitle(String title) {
		List<Video> videos = videoRepository.findAll();
		videos = videos.stream().filter(e -> e.getTag().contains(title)).collect(Collectors.toList());

		if (videos.size()==0) {
			throw new VideoTitleNotFoundException();
		}
		return videos;
	}

	public List<Video> getVideosByTagContaining(String tags) {
		List<Video> videos = videoRepository.findAll();

		for (String tag : tags.split(", ")) {
			videos = videos.stream().filter(e -> e.getTag().contains(tag)).collect(Collectors.toList());
		}

		if (videos.size()==0) {
			throw new VideoTagNotFoundException();
		}
		return videos;
	}

	public Video addVideo(Video video) {
		return videoRepository.saveAndFlush(video);
	}

	public Video updateVideo(Video video) {
		return videoRepository.saveAndFlush(video);
	}

	public boolean deleteVideo(int id) {
		videoRepository.delete(getVideoById(id));
		return true;
	}

	public List<Video> sortVideosByTitle() {
		return videoRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
	}

	public List<Video> sortVideosByCreatedDate() {
		return videoRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));
	}
}
