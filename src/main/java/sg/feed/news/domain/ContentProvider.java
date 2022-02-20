package sg.feed.news.domain;

import java.util.List;

public interface ContentProvider {

    List<News> query(String category, String country);
}
