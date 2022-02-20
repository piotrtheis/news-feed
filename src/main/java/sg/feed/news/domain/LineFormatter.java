package sg.feed.news.domain;

public interface LineFormatter {

    default String format(News news) {
        String subject = news.getTitle() + ":" + news.getDescription() + ":" + news.getAuthor();

        return subject.replaceAll("[\r\n]+", " ");
    }
}
