package RedditParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseRedditURL {

    private String subreddit = "r/TODO"; //this is a TODO, making sure that URL belongs to 0xBUTT subreddit, place it as a config in microframework!!

    public SubmitRedditData getDataFromURL(String URL) throws IOException {
        if(!confirmSubReddit(URL)) return null;

        SubmitRedditData ret = new SubmitRedditData();

        String html = Jsoup.connect(URL).get().html();

        Document doc = Jsoup.parse(html);
        Elements score = doc.select("div.score.unvoted");
        Elements author = doc.select("p.tagline").get(0).select("a");

        ret.setScore(Integer.parseInt(score.text()));
        ret.setUsername(author.text());
        return ret;
    }

    private boolean confirmSubReddit(String url) {
        return url.contains(subreddit);
    }


}
