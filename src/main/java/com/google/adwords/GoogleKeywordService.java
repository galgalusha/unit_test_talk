package com.google.adwords;

import java.util.List;

public interface GoogleKeywordService {

    List<GoogleKeyword> getKeywords(GoogleSelector selector);

    void mutate(List<KeywordMutateCommand> commands);
}
