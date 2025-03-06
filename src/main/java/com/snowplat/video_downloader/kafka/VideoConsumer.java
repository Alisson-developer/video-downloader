package com.snowplat.video_downloader.kafka;

import com.snowplat.video_downloader.dto.DownloadDTO;
import com.snowplat.video_downloader.service.YoutubeDownloaderService;
import com.snowplat.video_downloader.util.UtilitarioValidacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class VideoConsumer {

    private static final String TOPIC_NAME = "youtube_download";
    private static final String GROUP_ID = "yd-group";

    private final YoutubeDownloaderService videoDownloadService;

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void listen(DownloadDTO ytDownloadsDTO) throws IllegalAccessException {
        UtilitarioValidacao.validarDTO(ytDownloadsDTO);
        videoDownloadService.startDownload(ytDownloadsDTO);
    }
}