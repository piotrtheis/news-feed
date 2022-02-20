package sg.feed.news;

public interface LineFormatter {

    default String format(News news) {
        return news.getTitle() + ":" + news.getDescription().replace("\n", "") + ":" + news.getAuthor();
    }
}
