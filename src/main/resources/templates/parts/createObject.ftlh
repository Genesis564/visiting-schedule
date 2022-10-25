<#macro create path,name,variableName>

    <form action="${path}" method="post">
        <div><label>${name} : <input type="text" name="${variableName}"></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Create"></div>
    </form>
</#macro>
