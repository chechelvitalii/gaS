package com.gas.station;

import com.google.common.collect.ImmutableMap;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@UtilityClass
public class HtmlValidator {

    private final Map<String, String> REPLACE_RULES = ImmutableMap.<String, String>builder()
            .put("\"\\n", EMPTY)
            .put("\\\"", EMPTY)
            .build();

    public String validate(String html) {
        String invalidHtml = html;
        for (String replace_char : REPLACE_RULES.keySet()) {
            invalidHtml = invalidHtml.replace(replace_char, REPLACE_RULES.get(replace_char));
        }
        //&quot;
        String unescapedXml = StringEscapeUtils.unescapeXml(invalidHtml);
        // replace \" to "
        return StringEscapeUtils.unescapeJava(unescapedXml);
    }
}
