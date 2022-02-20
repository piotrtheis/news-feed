package sg.feed.news;

import sg.feed.news.domain.NewsStorage;

public record InMemoryNewsStorage(StringBuffer storage) implements NewsStorage {

    @Override
    public void store(String content) {
        storage.append(content);
    }
}
