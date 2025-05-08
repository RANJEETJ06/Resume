package com.analyzer.resume.controller;

import com.analyzer.resume.dto.ResumeTextResponse;
import com.analyzer.resume.service.ResumeParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeParserService resumeParserService;

    @PostMapping("/upload")
    public ResponseEntity<ResumeTextResponse> uploadResume(@RequestParam("file") MultipartFile file) {
        String extractedText = resumeParserService.extractText(file);
        return ResponseEntity.ok(new ResumeTextResponse(extractedText));
    }


}

