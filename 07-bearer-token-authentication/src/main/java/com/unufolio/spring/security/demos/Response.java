package com.unufolio.spring.security.demos;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/30
 */
public class Response {

    private final static String UTF8 = "UTF-8";

    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new Jackson2ObjectMapperBuilder()
            .findModulesViaServiceLoader(true)
            .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")))
            .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")))
            .serializerByType(LocalDate.class, new LocalDateSerializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .deserializerByType(LocalDate.class, new LocalDateDeserializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .build();
    }

    public static void redirect(HttpServletResponse response, String url)
        throws IOException {
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(url);
    }

    public static void unauthorized(HttpServletResponse response)
        throws IOException {
        status(response, HttpStatus.UNAUTHORIZED);
    }

    public static void forbidden(HttpServletResponse response)
        throws IOException {
        status(response, HttpStatus.FORBIDDEN);
    }

    public static void ok(HttpServletResponse response)
        throws IOException {
        status(response, HttpStatus.OK);
    }

    public static void jsonBody(HttpServletResponse response, @NonNull HttpStatus status, @NonNull Object body)
        throws IOException {
        body(response, status, body);
    }

    private static void body(HttpServletResponse response, @NonNull HttpStatus status, @NonNull Object body)
        throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF8);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(OBJECT_MAPPER.writeValueAsString(body));
        }
    }

    public static void status(HttpServletResponse response, @NonNull HttpStatus status) {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
