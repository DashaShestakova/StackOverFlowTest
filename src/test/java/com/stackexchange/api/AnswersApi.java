package com.stackexchange.api;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AnswersApi extends BaseApi {

    protected final String RESOURCE = "answers";

    public String buildEndpoint() {
        return BASE_API +
                "/" +
                RESOURCE +
                "?" +
                baseParameters();
    }
}
