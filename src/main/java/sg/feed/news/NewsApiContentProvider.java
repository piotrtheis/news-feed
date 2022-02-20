package sg.feed.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class NewsApiContentProvider implements ContentProvider {

    public final RestTemplate template = new RestTemplate();

    private final NewsApiConfig config;

    public NewsApiContentProvider(NewsApiConfig config) {
        this.config = config;
    }

    @Override
    public List<News> query(String category, String country) {
        var url = "%s?country=%s&category=%s&apiKey=%S".formatted(config.getUrl(), country, category, config.getKey());
        Wrapper result;

        try {
            result = template.getForObject(url, Wrapper.class);

            if (result != null) {
                return result.articles.stream()
                        .map(vo -> {
                            var title = "";
                            var author = "";
                            var description = "";

                            if (vo.title != null) {
                                title = vo.title;
                            }

                            if (vo.author != null) {
                                author = vo.author;
                            }

                            if (vo.description != null) {
                                description = vo.description;
                            }

                            return new News(title, description, author);
                        })
                        .collect(Collectors.toList());
            }

            return new ArrayList<>();
        } catch (RestClientException e) {
            log.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    private static record Wrapper(List<NewsVO> articles) {
    }

    private static record NewsVO(String title, String author, String description) {
    }
}
