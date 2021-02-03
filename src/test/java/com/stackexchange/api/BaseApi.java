package com.stackexchange.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class BaseApi {

    protected ObjectMapper objectMapper;
    protected final String BASE_API = "https://api.stackexchange.com/2.2";

    public String site;
    public int page;
    public int pageSize;
    public String order;
    public String sort;
    public String filter;

    public abstract String buildEndpoint();

    public String baseParameters() {
        return "site=" + site +
                "&page=" + page +
                "&pageSize=" + pageSize +
                "&order=" + order +
                "&sort=" + sort +
                "&filter=" + filter;
    };
}
