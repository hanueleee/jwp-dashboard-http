package org.apache.coyote.http11.common.request;

import java.util.HashMap;
import java.util.Map;
import org.apache.coyote.http11.util.Parser;

public class QueryParams {

    public static final String START_MARK_OF_QUERY_PARAMS = "?";
    public static final String EMPTY_STRING = "";
    public static final int HAS_NO_QUERY_PARAMS = -1;

    private final Map<String, String> params;

    public QueryParams(final Map<String, String> params) {
        this.params = params;
    }

    public static QueryParams create(String line) {
        int questionMarkIdx = line.indexOf(START_MARK_OF_QUERY_PARAMS);
        if (questionMarkIdx == HAS_NO_QUERY_PARAMS) {
            return QueryParams.empty();
        }
        String paramLine = line.substring(questionMarkIdx + 1);
        return Parser.parseToQueryParams(paramLine);
    }

    public static QueryParams empty() {
        return new QueryParams(new HashMap<>());
    }

    public String getParam(String name) {
        return params.getOrDefault(name, EMPTY_STRING);
    }
}
