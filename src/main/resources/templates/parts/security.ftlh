<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getFullName()
        isAdmin = user.isAdmin()
    >
    <#else>
        <#assign
        name = "Гость"
        isAdmin = false
        >
</#if>