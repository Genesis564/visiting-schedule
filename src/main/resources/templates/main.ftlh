<#import "parts/common.ftlh" as c>

<@c.page>
    <div>Пользователи (Позже эта страница будет удалена, а пока она полезна)</div>
    <#list users as user>
        <div>
            <b>${user.id}</b>
            <span>${user.username}</span>
            <span>${user.password}</span>
        </div>
    <#else>
        No Users
    </#list>
</@c.page>