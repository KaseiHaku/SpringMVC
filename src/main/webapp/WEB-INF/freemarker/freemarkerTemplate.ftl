<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>User</title>
</head>
<body>
<div>
    <span>${String!"Default Value"}</span><br/>
    <span>${Number}</span><br/>

    <#--?string() 表示调用 freemarker 内建的方法 string -->
    <span>${Boolean?string('yes', 'no')}</span><br/>
    <span>${Date?datetime}</span><br/>
    <span>${Date?string("yyyy-MM-dd HH:mm:")}</span><br/>
    <#--<span>${List}</span><br/>-->
    <#--<span>${Map.key!defaultValue}</span><br/>-->
    <span>${JavaBean}</span><br/>




    <#-- FTL tags & directives -->
    <#if String??><#--  ?? 表示判断 String 是否存在 -->
        String 为空
    <#elseif String == "java.lang.String">
        String 为 java.lang.String
    <#else>
        默认值
    </#if>


    <#list List as item>
        ${item}
    </#list>

    <#--以下格式，当 List 为空时 ul 标签不会出现-->
    <#list List>
        <ul>
            <#items as item>
                <li>${item}</li>
            </#items>
        </ul>
    </#list>

    <#list List as item>${item}<#sep>, <#else>None</#list>


    <#--插入另一个 ftl 模板到当前模板中 -->
    <#-- <#include "./Foot.ftl"> -->

    ${"<hr/>"?no_esc}<#-- ?no_esc 表示对值里面的 HTML 代码不进行转义，原样输出 -->
</div>
</body>
</html>
