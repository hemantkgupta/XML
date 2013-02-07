package javaxml3;

public class BookBean {

    private String pubDate;

    private String title;

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return super.toString() + "[title=" + title + ", pubDate=" + pubDate
                + "]";
    }

}
