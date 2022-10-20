<#import "parts/common.ftl" as c>

<@c.page>
<div>Users list</div>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
<#list users as user>
    <div>
        <span>${user.username}</span>
        <span>${user.roles}</span>
        <a href="/admin/${user.id}">edit</a>
    </div>
</#list>
</table>
</@c.page>