<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <table class="table table-hover table-bordered table-sm">
            <thead>
            <tr>
                <th>Неделя</th>
                <th>Посещающий занятие</th>
                <th>Преподаватель</th>
            </tr>
            </thead>
            <tbody>
            <#list schedules as schedule>
                <tr>
                    <td>${schedule.visitingWeek}</td>
                    <td>${schedule.visitorUser.username}</td>
                    <td>${schedule.visitedUser.username}</td>
                        <#list visitings as visiting>
                            <#if visiting.schedule.id = schedule.id>
                                <#assign currentVisiting = visiting>
                                <#else>
                                <#assign currentVisiting = 'none'>
                            </#if>
                        </#list>
                    <td>
                        <#if currentVisiting == 'none'>
                        <form class="me-3" action="/admin/schedule/add/${schedule.id}" method="get">
                            <button type="submit" class="btn btn-outline-secondary ms-3">Создать посещение
                            </button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                        <#else>
                        <form action="/admin/schedule/add/${schedule.id}" method="get">
                            <button type="submit" class="btn btn-outline-secondary ms-3">Посещение
                            </button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>