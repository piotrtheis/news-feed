package sg.feed.news;

public record InMemoryNewsStorage(StringBuffer storage) implements NewsStorage {

    @Override
    public void store(String content) {
        storage.append(content);
    }
}
