package sg.feed;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {"business", "pl"})
//@TestPropertySource(properties = {"news.api.key=123"})
class NewsFeedApplicationTests {

    @Test()
    void loadContextAndRunCommands() {
    }

}
