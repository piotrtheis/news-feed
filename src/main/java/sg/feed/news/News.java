package sg.feed.news;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class News {

    private final UUID uuid = UUID.randomUUID();

    private final String title;
    private final String description;
    private final String author;

    public News(String title, String description, String author) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(author);
        Objects.requireNonNull(description);

        this.title = title;
        this.description = description;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(uuid, news.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
