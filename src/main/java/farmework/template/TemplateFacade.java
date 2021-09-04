package farmework.template;

import farmework.model.TemplateInfo;
import farmework.template.service.TemplateService;

import java.util.Map;

public final class TemplateFacade {

    private TemplateFacade() {
        //
    }

    public static TemplateInfo getTemplateByKey(String templateKey) {
        return new TemplateService().getTemplateByKey(templateKey);
    }

    public static String replaceTemplate(String templateKey, Map<String, Object> mapping) {
        return new TemplateService().replaceTemplate(templateKey, mapping);
    }
}
