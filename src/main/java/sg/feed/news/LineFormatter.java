package sg.feed.news;


public interface LineFormatter {

    default String format(News news) {
        return news.getTitle().replaceAll("[\r\n]+", " ") + ":" +
                news.getDescription().replaceAll("[\r\n]+", " ") + ":" +
                news.getAuthor().replaceAll("[\r\n]+", " ");
    }
}
