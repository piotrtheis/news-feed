package sg.feed.news;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.sound.sampled.Line;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

class FileSystemNewsStoragePropertyBasedTest {

    @TempDir
    private Path tempDir;
    private Path path;

    private NewsStorage storage;

    @BeforeEach
    public void setUp() {
        path = tempDir.resolve("test_storage2.txt");
        storage = new FileSystemNewsStorage(path);
    }

    @Test
    void should_store_news_feed_content_in_file() {
        qt()
                .forAll(
                        strings().ascii().ofLengthBetween(0, 150),
                        strings().ascii().ofLengthBetween(0, 15000),
                        strings().ascii().ofLengthBetween(0, 50)
                ).as(News::new).checkAssert(news -> {
                    var formatter = new LineFormatter(){};
                    var content = formatter.format(news);
                    storage.store(content);

                    then(path).exists().content().isEqualTo(content);
                });
    }
}