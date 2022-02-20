package sg.feed.news;

import com.github.javafaker.Faker;

public class FakeNews extends News {

    private static final Faker faker = new Faker();

    public FakeNews() {
        super(faker.book().title(), faker.lorem().paragraph(25), faker.internet().domainName());
    }
}
