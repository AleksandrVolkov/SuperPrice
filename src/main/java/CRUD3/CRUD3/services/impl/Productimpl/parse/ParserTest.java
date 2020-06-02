package CRUD3.CRUD3.services.impl.Productimpl.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ParserTest {

    public Document testGetPage(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 YaBrowser/20.2.0.1043 Yowser/2.5 Safari/537.36") //
                    .referrer("http://www.google.com")
                    .get();
        } catch (Exception e) {
            return new Document(url);
        }
    }

    @Test
    void getPage() {
        String url ="http://www.cronmaker.com";
        Parser parser = new Parser();
        String test = testGetPage(url).text();
        String code = parser.getPage(url).text();
        Assert.assertEquals(test, code);
    }
}
