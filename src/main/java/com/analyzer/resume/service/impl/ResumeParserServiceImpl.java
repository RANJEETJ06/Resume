package com.analyzer.resume.service.impl;

import com.analyzer.resume.entity.Resume;
import com.analyzer.resume.repository.ResumeRepository;
import com.analyzer.resume.service.ResumeParserService;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ResumeParserServiceImpl implements ResumeParserService {

    private ResumeRepository resumeRepository;

    @Override
    public String extractText(MultipartFile file) {
        try {
            Tika tika = new Tika();
            String extractedText = tika.parseToString(file.getInputStream());

            Resume resume = new Resume();
            resume.setFilename(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setRawText(extractedText);

            resumeRepository.save(resume);

            return extractedText;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse file", e);
        } catch (TikaException e) {
            throw new RuntimeException(e);
        }
    }
}
