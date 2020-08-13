package Model;

import com.example.volley.MainActivity;

public class TechCrunch  extends MainActivity {
    public String author;
    public String title;
    public String url;
    public String urlToImage;
    public String publishedAt;

    public TechCrunch(){

    }

    public TechCrunch(String author, String title, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "TechCrunchNews{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}





