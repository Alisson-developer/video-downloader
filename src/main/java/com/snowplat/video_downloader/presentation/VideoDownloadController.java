package com.snowplat.video_downloader.presentation;

import com.snowplat.video_downloader.dto.YtDownloadsDTO;
import com.snowplat.video_downloader.service.VideoDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class VideoDownloadController {

    @Autowired
    private VideoDownloadService service;

    @PostMapping
    public ResponseEntity<Void> download(@RequestBody YtDownloadsDTO ytDownloadsDTO) throws IllegalAccessException {
        service.sendMessage(ytDownloadsDTO);
        return ResponseEntity.ok().build();
    }

}
