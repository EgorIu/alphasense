package com.example.demo.config;

import com.example.demo.clients.SearchClient;
import com.example.demo.pageobjects.DocPage;
import com.example.demo.pageservices.DocPageService;
import com.example.demo.services.SearchService;
import com.example.demo.services.SearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


@Configuration
@ComponentScan("com.example.demo")
@AllArgsConstructor
public class AppConfig {

    ConfigProperties configProperties;

    @Bean
    public Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(configProperties.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Bean
    public SearchClient userClient(Retrofit retro) {
        return retro.create(SearchClient.class);
    }

    @Bean
    public SearchService userService(SearchClient searchClient) {
        return new SearchServiceImpl(searchClient);
    }

    @Bean
    public DocPage docPage() {
        return new DocPage();
    }

    @Bean
    public DocPageService dockPageService(DocPage docPage){
        return new DocPageService(docPage);
    }
}
