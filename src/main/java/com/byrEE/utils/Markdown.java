package com.byrEE.utils;

import com.byrEE.support.web.MarkdownService;
import com.byrEE.support.web.PegDownMarkdownService;

/**
 * A Markdown processing util class
 *
 * @author byrEE
 */
public class Markdown {

    private static final MarkdownService MARKDOWN_SERVICE = new PegDownMarkdownService();

    public static String markdownToHtml(String content){
        return MARKDOWN_SERVICE.renderToHtml(content);
    }
}
