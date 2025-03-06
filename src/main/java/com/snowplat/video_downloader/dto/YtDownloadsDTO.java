package com.snowplat.video_downloader.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class YtDownloadsDTO {

    @NotNull
    private String path;

    @NotNull
    private String[] urls;
}
