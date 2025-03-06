package com.snowplat.video_downloader.service;

import com.snowplat.video_downloader.dto.YtDownloadsDTO;
import com.snowplat.video_downloader.kafka.VideoProducer;
import com.snowplat.video_downloader.util.UtilitarioValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoDownloadService {

    @Autowired
    private VideoProducer producer;

    public void sendMessage(YtDownloadsDTO ytDownloadsDTO) throws IllegalAccessException {
        UtilitarioValidacao.validarDTO(ytDownloadsDTO);
        producer.sendMessage(ytDownloadsDTO);
    }

}
