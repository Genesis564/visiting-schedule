<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="card mb-3">
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
                        <#assign
                        i = 0
                        >
                        <#list schedules as schedule>
                            <tr>
                                <td>
                                    ${schedule.visitingWeek}
                                </td>
                                <td>
                                    ${schedule.visitorUser.username}
                                </td>
                                <td>
                                    <div class="container">
                                        <div class="row">
                                            <div class="col text-start">
                                                ${schedule.visitedUser.username}
                                            </div>
                                            <div class="col-6 text-start">
                                                <#if schedule.isVisited()>
                                                    <#if access[i] == true>
                                                        <form action="/schedule/view/${schedule.id}" method="get">
                                                            <button type="submit"
                                                                    class="btn btn-outline-secondary ms-3">
                                                                Подтвердить
                                                                посещение
                                                            </button>
                                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                        </form>
                                                    <#else>
                                                        <form action="/schedule/view/${schedule.id}" method="get">
                                                            <button type="submit"
                                                                    class="btn btn-outline-secondary ms-3">
                                                                Посещение
                                                            </button>
                                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                        </form>
                                                    </#if>
                                                <#else>
                                                    <form class="me-3" action="/schedule/add/${schedule.id}"
                                                          method="get">
                                                        <button type="submit" class="btn btn-outline-secondary ms-3">
                                                            Создать
                                                            посещение
                                                        </button>
                                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                    </form>
                                                </#if>
                                            </div>

                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <#assign
                            i = i+1
                            >
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@c.page>