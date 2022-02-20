package sg.feed.news;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.feed.news.domain.ContentProvider;
import sg.feed.news.domain.LineFormatter;
import sg.feed.news.domain.News;
import sg.feed.news.domain.NewsStorage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewsFeedTest {

    private NewsFeed feed;

    private ContentProvider contentProvider;

    private final LineFormatter formatter = new LineFormatter() {
    };

    @BeforeEach
    void setUp() {
        contentProvider = mock(ContentProvider.class);
        feed = new NewsFeed(contentProvider);
    }

    @Test
    void should_download_format_and_store_content() {
        List<News> news = testData();
        StringBuffer buffer = new StringBuffer();
        NewsStorage storage = new InMemoryNewsStorage(buffer);
        when(contentProvider.query("any", "pl")).thenReturn(news);

        feed.download("any", "pl", formatter, storage);

        then(buffer).hasLineCount(news.size());
    }

    private List<News> testData() {
        return Stream.generate(FakeNews::new).limit(100).collect(Collectors.toList());
    }
}