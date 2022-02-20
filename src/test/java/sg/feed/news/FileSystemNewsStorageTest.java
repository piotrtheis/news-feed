package sg.feed.news;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class FileSystemNewsStorageTest {

    @TempDir
    private Path tempDir;
    private Path path;

    private NewsStorage storage;

    @BeforeEach
    public void setUp() {
        path = tempDir.resolve("test_storage.txt");
        storage = new FileSystemNewsStorage(path);
    }

    @Test
    void should_store_news_feed_content_in_file() {
        // given
        var lines = 100;
        var content = Stream.of(new String[lines])
                .map(s -> String.format("%s:%s:%s", "part1", "part2", "part3")).collect(Collectors.joining(System.lineSeparator()));

        // when
        storage.store(content);

        // then
        then(path).exists().content().isEqualTo(content);
    }
}