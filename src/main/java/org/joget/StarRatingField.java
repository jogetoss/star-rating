package org.joget;

import java.util.Map;
import org.joget.apps.app.service.AppPluginUtil;
import org.joget.apps.app.service.AppUtil;
import org.joget.apps.form.lib.Radio;
import org.joget.apps.form.model.FormBuilderPalette;
import org.joget.apps.form.model.FormData;
import org.joget.apps.form.service.FormUtil;

public class StarRatingField extends Radio {
    private final static String MESSAGE_PATH = "message/form/StarRatingField";
    
    @Override
    public String getName() {
        return "Star Rating";
    }

    @Override
    public String getVersion() {
        return "7.0.2";
    }
    
    @Override
    public String getClassName() {
        return getClass().getName();
    }
    
    @Override
    public String getFormBuilderCategory() {
        return FormBuilderPalette.CATEGORY_CUSTOM;
    }
    
    @Override
    public String getLabel() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.StarRatingField.pluginLabel", getClassName(), MESSAGE_PATH);
    }

    @Override
    public String getDescription() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.StarRatingField.pluginDesc", getClassName(), MESSAGE_PATH);
    }
    
    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/form/starRatingField.json", null, true, MESSAGE_PATH);
    }
    
    @Override
    public String renderTemplate(FormData formData, Map dataModel) {
        String template = "starRatingField.ftl";
        
        // set value
        String value = FormUtil.getElementPropertyValue(this, formData);
        dataModel.put("value", value);

        String html = FormUtil.generateElementHtml(this, formData, template, dataModel);
        return html;
    }
    
    @Override
    public String getFormBuilderTemplate() {
        return "<label class='label'>"+getLabel()+"</label>";
    }
}
