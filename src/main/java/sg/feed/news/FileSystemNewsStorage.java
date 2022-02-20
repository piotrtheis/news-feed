package sg.feed.news;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record FileSystemNewsStorage(Path path) implements NewsStorage {

    @Override
    public void store(String content) {
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
