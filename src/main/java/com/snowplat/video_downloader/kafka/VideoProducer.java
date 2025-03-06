package com.snowplat.video_downloader.kafka;

import com.snowplat.video_downloader.dto.DownloadDTO;
import com.snowplat.video_downloader.dto.YtDownloadsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoProducer {

    private static final String IDENTIFY_KEY = "identificador";

    private final KafkaTemplate<String, DownloadDTO> kafkaTemplate;

    private static final String TOPIC_NAME = "youtube_download";

    public void sendMessage(YtDownloadsDTO ytDownload) {
        for (String url : ytDownload.getUrls()) {
            kafkaTemplate.send(TOPIC_NAME, IDENTIFY_KEY,new DownloadDTO(ytDownload.getPath(), url));
        }
    }

}
