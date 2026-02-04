package com.github.xjs.service;

import org.jspecify.annotations.Nullable;

public interface TokenExtractorService {
    @Nullable String extractToken(String input);
    interface Nested{
    }

    @Nullable String[] extractTokens(String input);

    Wrapper<@Nullable String> extractTokenWrapped(String input);


}
