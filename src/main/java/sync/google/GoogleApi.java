package sync.google;

import java.util.List;

public interface GoogleApi {

    List<GoogleKeyword> getKeywords(GoogleSelector selector);
}
