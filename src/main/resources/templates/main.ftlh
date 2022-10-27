<#import "parts/common.ftlh" as c>

<@c.page>
    <div>Users</div>
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