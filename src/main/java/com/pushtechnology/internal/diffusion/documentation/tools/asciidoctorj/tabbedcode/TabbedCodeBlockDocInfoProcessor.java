package com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.DocinfoProcessor;

import java.util.Map;

import static com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.FileUtils.LINE_SEPARATOR;
import static com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.FileUtils.readFileContentsFromPath;
import static com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.FileUtils.getURLForResource;

public class TabbedCodeBlockDocInfoProcessor extends DocinfoProcessor {
    public static final String TABBED_CODE_CSS_FILE_PATH_ATTRIBUTE = "tabbed-code-css-path";
    public static final String TABBED_CODE_JS_FILE_PATH_ATTRIBUTE = "tabbed-code-js-path";
    public static final String DEFAULT_CSS_FILE_PATH = "/codeBlockSwitch.css";
    public static final String DEFAULT_JS_FILE_PATH = "/codeBlockSwitch.js";

    @Override
    public String process(Document document) {
        if (document.isBasebackend("html")) {
            Map<String, Object> attributes = document.getAttributes();
            String cssPath = getCssPath(attributes, TABBED_CODE_CSS_FILE_PATH_ATTRIBUTE, getURLForResource(DEFAULT_CSS_FILE_PATH).getPath());
            String jsPath = getCssPath(attributes, TABBED_CODE_JS_FILE_PATH_ATTRIBUTE, getURLForResource(DEFAULT_JS_FILE_PATH).getPath());
            String css = readFileContentsFromPath(cssPath);
            String js = readFileContentsFromPath(jsPath);
            return modifyHeadHtml(css, js);
        }

        return null;
    }

    private String getCssPath(Map<String, Object> attributes, String attributeKey, String defaultPath) {
        if (attributes.containsKey(attributeKey)) {
            return (String) attributes.get(attributeKey);
        }

        return defaultPath;
    }

    private String modifyHeadHtml(String css, String js) {
        StringBuilder htmlHead = new StringBuilder();
        htmlHead.append("<style>").append(LINE_SEPARATOR);
        htmlHead.append(css).append(LINE_SEPARATOR);
        htmlHead.append("</style>").append(LINE_SEPARATOR);
        htmlHead.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js\"></script>").append(LINE_SEPARATOR);
        htmlHead.append("<script type=\"text/javascript\">").append(LINE_SEPARATOR);
        htmlHead.append(js).append(LINE_SEPARATOR);
        htmlHead.append("</script>").append(LINE_SEPARATOR);
        return htmlHead.toString();
    }
}
