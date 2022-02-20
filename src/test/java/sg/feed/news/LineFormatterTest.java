package sg.feed.news;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sg.feed.news.domain.LineFormatter;
import sg.feed.news.domain.News;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineFormatterTest {

    private final static Faker faker = new Faker();
    private final LineFormatter formatter = new LineFormatter() {
    };

    @ParameterizedTest
    @MethodSource("source")
    void should_format_news_to_single_line_separated_by_colon(String title, String description, String author) {
        // given
        var news = new News(title, description, author);

        // when
        var row = formatter.format(news);

        // then
        assertEquals(1, row.lines().count());
    }


    private static String[][] source() {
        return new String[][]{
                {faker.book().title(), faker.lorem().paragraph(25), faker.internet().domainName()},
                {faker.book().title(), faker.lorem().paragraph(50), faker.internet().domainName()},
                {faker.book().title(), faker.lorem().paragraph(75), faker.internet().domainName()},
                {"Title\ntitle\r\ntitle", "Content\n\rcontent\ncontent\r\n", faker.internet().domainName()}
        };
    }
}