package com.caixa.invest.util;

import com.caixa.invest.domain.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientFileUtil {
    private static final String BASE_DIR = "clientes";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveClientToFile(Client client) throws IOException {
        String clientDir = BASE_DIR + File.separator + client.getId();
        Path dirPath = Paths.get(clientDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        Path filePath = dirPath.resolve("dados.json");
        objectMapper.writeValue(filePath.toFile(), client);
    }
}
