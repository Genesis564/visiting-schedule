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
                    <td>
                        <form action="/admin/schedule/add/${schedule.id}" method="get">
                            <button type="submit" class="btn btn-outline-secondary ms-3">Редактировать
                            </button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>