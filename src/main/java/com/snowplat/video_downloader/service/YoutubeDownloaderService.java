package com.snowplat.video_downloader.service;


import com.snowplat.video_downloader.dto.DownloadDTO;
import com.snowplat.video_downloader.util.UtilitarioValidacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YoutubeDownloaderService {

    private static final String YT_DOWNLOAD_EXEC_PATH = "C:\\Program Files\\yt-dlp\\yt-dlp.exe";
    private static final String DOWNLOAD_FORMAT = "-f bestvideo[height<=1080]+bestaudio/best --merge-output-format mp4";


    public YoutubeDownloaderService() {
    }


    public void startDownload(DownloadDTO downloadDTO) {
        String pathFormatado = UtilitarioValidacao.validarBarrasDePath(downloadDTO.getPath());
        processarDownload(pathFormatado, downloadDTO.getUrl());
    }


    private void processarDownload(String path, String url) {
        try {
            ProcessBuilder processBuilder = criarProcesso(path, url);
            Process process = processBuilder.start();

            atualizarProgresso(process);

            process.waitFor();

        } catch (IOException | InterruptedException ex) {
            log.error("Erro ao baixar o vídeo", ex);
        }
    }


    private ProcessBuilder criarProcesso(String path, String url) {
        String completeCommand = String.format("%s %s -o \"%s/%%(title)s.%%(ext)s\" %s",
                YT_DOWNLOAD_EXEC_PATH,
                DOWNLOAD_FORMAT,
                path,
                url);

        ProcessBuilder processBuilder = new ProcessBuilder(completeCommand.split(" "));
        processBuilder.redirectErrorStream(true);

        return processBuilder;
    }


    private void atualizarProgresso(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            Pattern pattern = Pattern.compile("(\\d+\\.\\d+)%");
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    int progress = (int) Float.parseFloat(matcher.group(1));
                    log.info(String.valueOf(progress));
                }
            }
        }
    }


}
