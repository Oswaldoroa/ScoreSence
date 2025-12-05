package scoresense.app.service.ia;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.CategorizedEntity;
import com.azure.ai.textanalytics.models.EntityCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicAnalysisService {

    private final TextAnalyticsClient client;

    public List<String> detectarPersonas(String texto) {
        var response = client.recognizeEntities(texto);

        return response.stream()
                .filter(entity -> entity.getCategory() == EntityCategory.PERSON)
                .map(CategorizedEntity::getText)
                .collect(Collectors.toList());
    }
}
