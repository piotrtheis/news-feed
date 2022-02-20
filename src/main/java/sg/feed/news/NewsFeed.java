package sg.feed.news;

import sg.feed.news.domain.ContentProvider;
import sg.feed.news.domain.LineFormatter;
import sg.feed.news.domain.News;
import sg.feed.news.domain.NewsStorage;

import java.util.List;
import java.util.stream.Collectors;


public record NewsFeed(ContentProvider contentProvider) {

    public void download(String category, String country, LineFormatter formatter, NewsStorage storage) {
        var formattedContent = getAll(category, country)
                .stream()
                .map(formatter::format)
                .collect(Collectors.joining(System.lineSeparator()));

        storage.store(formattedContent);
    }

    private List<News> getAll(String category, String country) {
        return contentProvider.query(category, country);
    }
}
