package com.snowplat.video_downloader.util;

import com.snowplat.video_downloader.exception.NullFieldException;
import com.snowplat.video_downloader.exception.NullObjectException;
import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class UtilitarioValidacao {

    public static void validarDTO(Object dto) throws IllegalAccessException {

        if (dto == null) {
            throw new NullObjectException("O objeto de validação está nulo!");
        }

        try {
            for (Field field : dto.getClass().getFields()) {
                if (field.get(dto) == null && field.isAnnotationPresent(NotNull.class)) {
                    throw new NullFieldException(
                            "O campo " + field.getName() +
                                    " está anotado com @Nonnull, mas o mesmo econtra-se nulo.");
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage() + " \n \r " + e.getCause());
        }
    }

    public static String validarBarrasDeUrl(String path) {
        if (path.contains("\\\\")) {
            log.error("Por favor, use a '/'");
            throw new IllegalArgumentException();
        }
        return path;
    }

}
