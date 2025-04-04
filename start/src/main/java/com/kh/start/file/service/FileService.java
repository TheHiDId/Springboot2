package com.kh.start.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	private final Path fileLocation;
	
	public FileService() {
		
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file) {
		
		// 중복되지 않는 파일 이름으로 바꾸는 코드 작성 공간
		
		String originalFileName = file.getOriginalFilename();
		
		Path targetLocation = this.fileLocation.resolve(originalFileName);
		
		log.info("저장 경로: {}", targetLocation);
		
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost/uploads/" + originalFileName;
			
		} catch (IOException e) {
			throw new RuntimeException("잘못된 파일 입니다.");
		}
	}
}
