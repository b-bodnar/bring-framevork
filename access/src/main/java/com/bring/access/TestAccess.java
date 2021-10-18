package com.bring.access;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Add your full name to Team.txt. Then I'll be known that every one has access.
 */
public class TestAccess {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hoverla team:");
        Files.lines(Path.of("access/Team.txt")).forEach(System.out::println);

    }
}
