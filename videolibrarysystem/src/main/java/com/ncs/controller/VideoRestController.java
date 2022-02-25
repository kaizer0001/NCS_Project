package com.ncs.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ncs.exception.VideoIDMismatchException;
import com.ncs.model.Video;
import com.ncs.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoRestController {

	@Autowired
	VideoService videoService;

	@GetMapping
	public ResponseEntity<List<Video>> getAllVideos() {
		return new ResponseEntity<List<Video>>(videoService.getAllVideos(), HttpStatus.OK);
	}

	@GetMapping("/ids/{id}")
	public ResponseEntity<Video> getVideoById(@PathVariable int id) {
		return new ResponseEntity<Video>(videoService.getVideoById(id), HttpStatus.OK);
	}

	@GetMapping("/titles/{title}")
	public ResponseEntity<List<Video>> getVideosByTitle(@PathVariable String title) {
		return new ResponseEntity<List<Video>>(videoService.getVideosByTitle(title), HttpStatus.OK);
	}

	@GetMapping("/tags/{tag}")
	public ResponseEntity<List<Video>> getVideosByTagContaining(@PathVariable String tag) {
		return new ResponseEntity<List<Video>>(videoService.getVideosByTagContaining(tag), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Video> addVideo(@Valid @RequestBody Video video) {
		Video result = videoService.addVideo(video);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/ids/{id}").buildAndExpand(result.getId())
				.toUri();
		return ResponseEntity.created(location).body(video);
	}

	@PutMapping("/ids/{id}")
	public ResponseEntity<Video> updateVideo(@Valid @RequestBody Video video, @PathVariable int id) {
		if (id != video.getId())
			throw new VideoIDMismatchException();
		Video result = videoService.updateVideo(video);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/ids/{id}").buildAndExpand(result.getId())
				.toUri();
		return ResponseEntity.created(location).body(video);
	}

	@DeleteMapping("/ids/{id}")
	public ResponseEntity<?> deleteVideo(@PathVariable int id) {
		if (videoService.deleteVideo(id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().body("Something unexpdected");
	}

	@GetMapping("/sort/titles")
	public ResponseEntity<List<Video>> sortVideosByTitle() {
		return new ResponseEntity<List<Video>>(videoService.sortVideosByTitle(), HttpStatus.OK);
	}

	@GetMapping("/sort/createddates")
	public ResponseEntity<List<Video>> sortVideosByCreatedDate() {
		return new ResponseEntity<List<Video>>(videoService.sortVideosByCreatedDate(), HttpStatus.OK);
	}
}
