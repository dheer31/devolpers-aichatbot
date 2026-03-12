package com.example.aichatbot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OllamaService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OllamaService() {
        this.webClient = WebClient.create("http://172.31.18.86:11434");
    }

    public String generateResponse(String prompt){

        String request = """
        {
          "model":"deepseek-coder",
          "prompt":"%s",
          "stream":false
        }
        """.formatted(prompt);

        try {

            String rawResponse = webClient.post()
                    .uri("/api/generate")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode jsonNode = objectMapper.readTree(rawResponse);

            return jsonNode.get("response").asText();

        } catch (Exception e) {
            return "Error communicating with AI model.";
        }
    }
}
