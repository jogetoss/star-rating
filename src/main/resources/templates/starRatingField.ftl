<div class="form-cell" ${elementMetaData!}>
    <#if !(request.getAttribute("org.joget.StarRatingField")??) >
        <link rel="stylesheet" href="${request.contextPath}/plugin/org.joget.StarRatingField/css/starRating.css" />
    </#if>   
    <label class="label">${element.properties.label} <span class="form-cell-validator">${decoration}</span><#if error??> <span class="form-error-message">${error}</span></#if></label>
    <style>
        #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > input:checked ~ label {color: ${element.properties.hoverColor!};}
        <#if element.properties.readonly! != 'true'>
            #${elementParamName!}${element.properties.elementUniqueKey!} .starrating:not(:checked) > label:hover, #${elementParamName!}${element.properties.elementUniqueKey!} .starrating:not(:checked) > label:hover ~ label {color: ${element.properties.selectedColor!};}
            #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > input:checked + label:hover, #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > input:checked + label:hover ~ label, #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > input:checked ~ label:hover, #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > input:checked ~ label:hover ~ label, #${elementParamName!}${element.properties.elementUniqueKey!} .starrating > label:hover ~ input:checked ~ label {color: ${element.properties.hoverSelectedColor!};}
        </#if> 
    </style>    
    <div class="form-cell-value" id="${elementParamName!}${element.properties.elementUniqueKey!}">
        <div class="starrating <#if element.properties.enableHalfValue! == 'true'>half</#if>">
        <#list [5, 4, 3, 2, 1] as i>
            <input type="radio" id="${elementParamName!}_${i}" name="${elementParamName!}" value="${i}" <#if error??>class="form-error-cell"</#if> <#if element.properties.readonly! == 'true'> disabled</#if> <#if value?? && value == i?string>checked</#if> /><label for="${elementParamName!}_${i}" title="${i}">${i}</label>
        
            <#if element.properties.enableHalfValue! == 'true'>
                <input type="radio" id="${elementParamName!}_${(i - 1)?string["0"]}5" name="${elementParamName!}" value="${(i - 0.5)?string["0.#"]}" <#if error??>class="form-error-cell"</#if> <#if element.properties.readonly! == 'true'> disabled</#if> <#if value?? && value == (i - 0.5)?string["0.#"]>checked</#if> /><label class="half" for="${elementParamName!}_${(i - 1)?string["0"]}5" title="${(i - 0.5)?string["0.#"]}">${(i - 0.5)?string["0.#"]}</label>
            </#if> 
        </#list>
        </div>
    </div>
    <div style="clear:both;"></div>
</div>