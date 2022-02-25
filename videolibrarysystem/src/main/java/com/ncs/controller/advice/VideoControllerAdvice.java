package com.ncs.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ncs.exception.ErrorResponse;
import com.ncs.exception.VideoIDMismatchException;
import com.ncs.exception.VideoIDNotFoundException;
import com.ncs.exception.VideoTagNotFoundException;
import com.ncs.exception.VideoTitleNotFoundException;

@RestControllerAdvice
public class VideoControllerAdvice {
	 @ExceptionHandler(VideoIDMismatchException.class)
	 public ResponseEntity<ErrorResponse> handleTodoIDMismatch(VideoIDMismatchException e){
		 ErrorResponse er= new ErrorResponse("TODO-400", "Video ID specified doesn't match!", new Date());
		  return new ResponseEntity<ErrorResponse>(er,HttpStatus.BAD_REQUEST);
	 }

	@ExceptionHandler(VideoIDNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleVideoIDNotFound(VideoIDNotFoundException e) {
		ErrorResponse er = new ErrorResponse("VIDEO-404", "Video with the specified ID Not found!", new Date());
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(VideoTitleNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleVideoTitleNotFound(VideoTitleNotFoundException e) {
		ErrorResponse er = new ErrorResponse("VIDEO-404", "Video with the specified Title Not found!", new Date());
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(VideoTagNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleVideoTagNotFound(VideoTagNotFoundException e) {
		ErrorResponse er = new ErrorResponse("VIDEO-404", "Video with the specified Tag Not found!", new Date());
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}
}
