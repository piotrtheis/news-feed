package sg.feed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import sg.feed.news.FileSystemNewsStorage;
import sg.feed.news.LineFormatter;
import sg.feed.news.NewsFeed;
import sg.feed.news.NewsModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@SpringBootApplication
@Import(NewsModule.class)
public class NewsFeedApplication implements CommandLineRunner {

    private final NewsFeed newsFeed;

    public NewsFeedApplication(NewsFeed newsFeed) {
        this.newsFeed = newsFeed;
    }

    public static void main(String[] args) {
        SpringApplication.run(NewsFeedApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Category and country arguments are missing.");
        }

        var path = Path.of("news.txt");
        var storage = new FileSystemNewsStorage(path);
        var formatter = new LineFormatter() {
        };

        newsFeed.download(args[0], args[1], formatter, storage);

        try {
            log.info(Files.readString(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
