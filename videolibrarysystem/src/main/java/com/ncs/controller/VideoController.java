package com.ncs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncs.model.Video;
import com.ncs.service.VideoService;

@Controller
public class VideoController {

	@Autowired
	VideoService videoService;

	@GetMapping("/index.html")
	public String viewHomePage(Model model) {
		model.addAttribute("listVideos", videoService.getAllVideos());
		return "index";
	}

	@GetMapping("/showNewVideoForm")
	public String showNewVideoForm() {
		return "new_video";
	}

	@PostMapping("/addVideo")
	public String addVideo(@Valid @RequestBody Video video) {
		videoService.addVideo(video);
		return "redirect:/index.html";
	}

	@GetMapping("/showUpdateVideoForm/{id}")
	public String showUpdateVideoForm(@PathVariable int id, Model model) {
		model.addAttribute("video", videoService.getVideoById(id));
		return "update_video";
	}

	@PutMapping("/updateVideo")
	public String updateVideo(@Valid @RequestBody Video video) {
		videoService.updateVideo(video);
		return "redirect:/index.html";
	}

	@GetMapping("/delete/{id}")
	public String deleteVideo(@PathVariable int id, Model model) {
		videoService.deleteVideo(id);
		model.addAttribute("listVideos", videoService.getAllVideos());
		return "redirect:/index.html";
	}

	@GetMapping("/sort/title")
	public String sortTitle(Model model) {
		model.addAttribute("listVideos", videoService.sortVideosByTitle());
		return "sort_title";
	}

	@GetMapping("/sort/createddate")
	public String sortCreatedDate(Model model) {
		model.addAttribute("listVideos", videoService.sortVideosByCreatedDate());
		return "sort_createddate";
	}
}
