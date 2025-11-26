package scoresense.app.config.ia;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Bean
    public TextAnalyticsClient textAnalyticsClient(
            @Value("${azure.ai.key}") String key,
            @Value("${azure.ai.endpoint}") String endpoint) {

        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }
}
