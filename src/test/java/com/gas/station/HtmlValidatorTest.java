package com.gas.station;

import com.google.common.io.Resources;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HtmlValidatorTest {
    private static final String INVALID_HTML = "/invalidHtml.html";
    @Test
    public void sholdValidateHtml() throws Exception {
        //GIVEN
        URL resource = getClass().getResource(INVALID_HTML);
        String html = Resources.toString(resource, UTF_8);
        //WHEN
        String validHtml = HtmlValidator.validate(html);
        //THEN
        System.out.println(validHtml);
        //TODO write test
        Assert.assertTrue(false);

    }

}