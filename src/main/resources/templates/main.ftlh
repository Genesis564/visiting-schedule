<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="card">
        <div class="card-header">
            Расписание
        </div>
        <div class="card-body">
            <div>
                <table class="table table-hover table-sm">
                    <thead>
                    <tr>
                        <th>Неделя</th>
                        <th>Посещающий занятие</th>
                        <th>Преподаватель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list schedules as schedule>
                        <#assign currentVisiting = 'none'>
                        <#list visitings as visiting>
                            <#if visiting.schedule.id = schedule.id>
                                <#assign currentVisiting = visiting>
                            </#if>
                        </#list>
                        <tr>
                            <td>${schedule.visitingWeek}</td>
                            <td>${schedule.visitorUser.username}</td>
                            <td>${schedule.visitedUser.username}</td>
                            <td>
                                <#if currentVisiting == 'none'>
                                    <form class="me-3" action="/schedule/add/${schedule.id}" method="get">
                                        <button type="submit" class="btn btn-outline-secondary ms-3">Создать посещение
                                        </button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    </form>
                                <#else>
                                    <form action="/schedule/view/${schedule.id}" method="get">
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
        </div>
    </div>
</@c.page>