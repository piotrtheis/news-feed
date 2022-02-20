package sg.feed.news

import spock.lang.Specification

class LineFormatterSpec extends Specification {

    def formatter = new LineFormatter(){}

    def "should format news to single line separated by the colon"(){
        given:
            def news = new News("title", "some \n content", "Piotr");

        when:
            def row = formatter.format(news);

        then:
            row.lines().count() == 1
            row == "title:some   content:Piotr"
    }
}
