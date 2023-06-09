package com.avdhoot.springboot.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avdhoot.springboot.entity.InputFile;
import com.avdhoot.springboot.model.ResponseFile;
import com.avdhoot.springboot.repository.InputFileRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class InputFileService {

	@Autowired
	private InputFileRepository inputFileRepository;
	
	public InputFile store(MultipartFile file) throws IOException {
		String name = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		InputFile inputFile = new InputFile(name, file.getContentType(), file.getBytes());
		
		return inputFileRepository.save(inputFile);
	}

	public Stream<InputFile> getAllFiles() {
		return inputFileRepository.findAll().stream();
	}

	public InputFile getFile(String id) {
		return inputFileRepository.findById(id).get();
	}

}
