package com.gas.station;

import com.google.common.collect.ImmutableMap;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@UtilityClass
public class HtmlValidator {

    private final String QUOTE = "\"";
    private final Map<String, String> REPLACE_RULES = ImmutableMap.<String, String>builder()
            .put("\\\"", QUOTE)
            .build();

    public String validate(String html) {
        //&quot;
        String invalidHtml = StringEscapeUtils.unescapeXml(html);
        for (String replace_char : REPLACE_RULES.keySet()) {
            invalidHtml = invalidHtml.replace(replace_char, REPLACE_RULES.get(replace_char));
        }
        // replace \" to "
        invalidHtml = StringEscapeUtils.unescapeJava(invalidHtml);
        return trimStartAndEndQuote(invalidHtml);
    }

    private String trimStartAndEndQuote(String html) {
        return html.startsWith(QUOTE) && html.endsWith(QUOTE)
                ? html.substring(1, html.length() - 1)
                : html;
    }
}
