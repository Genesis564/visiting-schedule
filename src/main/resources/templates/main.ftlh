<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <form action="/admin/registration" method="get">
            <input type="submit" value="Add new User">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
        <form action="/admin/user-list" method="get">
            <input type="submit" value="View all User">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <div>Users list</div>
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