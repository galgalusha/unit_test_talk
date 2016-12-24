package sync.google;

import com.google.adwords.GoogleKeywordService;
import sync.google.KenshooKeyword;
import sync.google.KenshooSelector;

import java.util.List;

public class GoogleKeywordDownloader {

    private final GoogleKeywordService googleApi;

    public GoogleKeywordDownloader(GoogleKeywordService googleApi) {
        this.googleApi = googleApi;
    }

    public List<KenshooKeyword> download(KenshooSelector selector) {
        return null;
    }

}
