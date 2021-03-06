package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
	List<Video> findByTitle(String title);
}
