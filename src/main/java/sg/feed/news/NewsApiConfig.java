package sg.feed.news;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("news.api")
public class NewsApiConfig {

    private String key;

    private String url;
}