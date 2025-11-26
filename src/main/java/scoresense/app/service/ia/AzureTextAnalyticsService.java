package scoresense.app.service.ia;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AzureTextAnalyticsService {

    private final RestTemplate restTemplate;
    private final String endpoint = "https://scoresence-recognizeteam.cognitiveservices.azure.com/";
    private final String apiKey = "9A2x6AHl2BB7qGQbY1Bn1syU6ETrgXspviBuflOMycsnDefIEwlRJQQJ99BKAC4f1cMXJ3w3AAAaACOGwHTM";

    public AzureTextAnalyticsService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<String> extractEntities(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", apiKey);

        Map<String, Object> body = Map.of(
            "documents", List.of(Map.of("id", "1", "language", "es", "text", text))
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(endpoint, request, Map.class);

        // Parsear entidades
        List<Map<String, Object>> entities = (List<Map<String, Object>>)
            ((Map<String, Object>) ((List<?>) response.getBody().get("documents")).get(0)).get("entities");

        return entities.stream()
            .map(e -> (String) e.get("text"))
            .collect(Collectors.toList());
    }
}