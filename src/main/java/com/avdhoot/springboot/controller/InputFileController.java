package com.avdhoot.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avdhoot.springboot.entity.InputFile;
import com.avdhoot.springboot.model.ResponseFile;
import com.avdhoot.springboot.service.InputFileService;

@RestController
@CrossOrigin(origins = "*")
public class InputFileController {
	
	@Autowired
	private InputFileService inputFileService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		String message = "";
		try {
			inputFileService.store(file);
			
			message = "\"Uploaded file successfully\"";
			
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}catch(Exception e) {
			message = "\"Could not upload file\"";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListOfFiles(){
		List<ResponseFile> files = inputFileService.getAllFiles().map(file -> {
			String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/")
					.path(file.getId())
					.toUriString();
			
			return new ResponseFile(file.getName(), fileUri, file.getType(), file.getData().length*1L);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    InputFile file = inputFileService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
	        .body(file.getData());
	  }
	
}
