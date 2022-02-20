package sg.feed.news;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({NewsApiConfig.class})
public class NewsModule {

    @Bean
    public ContentProvider contentProvider(NewsApiConfig config) {
        return new NewsApiContentProvider(config);
    }

    @Bean
    public NewsFeed feed(NewsApiConfig config) {
        return new NewsFeed(contentProvider(config));
    }
}
