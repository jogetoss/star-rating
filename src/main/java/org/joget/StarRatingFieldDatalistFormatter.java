package org.joget;

import javax.servlet.http.HttpServletRequest;

import org.joget.apps.app.service.AppPluginUtil;
import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListColumn;
import org.joget.apps.datalist.model.DataListColumnFormatDefault;
import org.joget.apps.datalist.service.DataListService;
import org.joget.workflow.util.WorkflowUtil;

public class StarRatingFieldDatalistFormatter extends DataListColumnFormatDefault {
    
    private final static String MESSAGE_PATH = "message/datalist/StarRatingFieldDatalistFormatter";
    
    public String getName() {
        return AppPluginUtil.getMessage("org.joget.StarRatingFieldDatalistFormatter.pluginLabel", getClassName(), MESSAGE_PATH);
    }

    public String getVersion() {
        return "7.0.2";
    }
    
    public String getClassName() {
        return getClass().getName();
    }

    public String getLabel() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.StarRatingFieldDatalistFormatter.pluginLabel", getClassName(), MESSAGE_PATH);
    }
    
    public String getDescription() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.StarRatingFieldDatalistFormatter.pluginDesc", getClassName(), MESSAGE_PATH);
    }

    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClassName(), "/properties/datalist/StarRatingFieldDatalistFormatter.json", null, true, MESSAGE_PATH);
    }

    public String format(DataList dataList, DataListColumn column, Object row, Object value) {
        String result = (String) value;
        if (result != null && !result.isEmpty()) {
            result = "";
            String enableHalfValue = getPropertyString("enableHalfValue");
            String ratingColor = getPropertyString("ratingColor");
    
            String uniqueId = column.getName() + DataListService.evaluateColumnValueFromRow(row, "id").toString();

            HttpServletRequest request = WorkflowUtil.getHttpServletRequest();
            if (request != null && request.getAttribute(getClassName()) == null) {
                //load library
                result = "<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/plugin/"+getClassName()+"/css/starRating.css\" />\n";

                request.setAttribute(getClassName(), true);
            }
          

            String htmlString = "<div class=\"form-cell-value\" id=\"" + uniqueId + "\">\n";

            if(enableHalfValue.equals("true")){
                htmlString += "<div class=\"starrating half\">\n";
            }
            else {
                htmlString += "<div class=\"starrating\">\n";
            }

            for(int i = 5; i > 0; i--){
                htmlString += "<input type=\"radio\" id=\"ticket_rating_" + i + "_" + uniqueId + "\" name=\"ticket_rating_" + uniqueId + "\" value=\"" + i + "\" disabled><label for=\"ticket_rating_" + i + "_" + uniqueId + "\" title=\"" + i + "\">" + i + "</label>\n";
                if(enableHalfValue.equals("true")){
                    htmlString += "<input type=\"radio\" id=\"ticket_rating_" + (i-0.5) + "_" + uniqueId + "\" name=\"ticket_rating_" + uniqueId + "\" value=\"" + (i-0.5) + "\" disabled><label class=\"half\" for=\"ticket_rating_" + (i-0.5) + "_" + uniqueId + "\" title=\"" + (i-0.5) + "\">" + (i-0.5) + "</label>\n";
                }
            }

            htmlString += "</div></div>";
            result += "<style>#" + uniqueId + " .starrating > input:checked ~ label {color: " + ratingColor + ";}</style>";

            result += "<script> $(document).ready(function(){ document.getElementById(\"ticket_rating_" + value + "_" + uniqueId + "\").checked = true; })</script>";
            
            result += htmlString;

        }
        return result;
    }
}
