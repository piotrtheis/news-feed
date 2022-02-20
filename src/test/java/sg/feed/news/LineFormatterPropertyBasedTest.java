package sg.feed.news;

import org.junit.jupiter.api.Test;
import sg.feed.news.domain.LineFormatter;
import sg.feed.news.domain.News;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

class LineFormatterPropertyBasedTest {

    private final LineFormatter formatter = new LineFormatter() {
    };

    @Test
    void should_format_news_to_single_line_separated_by_colon() {
        qt()
                .forAll(
                        strings().allPossible().ofLengthBetween(0, 150),
                        strings().allPossible().ofLengthBetween(0, 1000),
                        strings().allPossible().ofLengthBetween(0, 50)
                ).as(News::new).checkAssert(news -> {
                    var row = formatter.format(news);
                    var lines = row.lines().count();

                    assertEquals(1, lines);
                });
    }
}