<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="card mb-3">
            <div class="card-header">
                Расписание
            </div>
            <div class="card-body">
                <div>
                    <table class="table table-hover table-responsive">
                        <thead>
                        <tr>
                            <th>Неделя</th>
                            <th>Посещающий занятие</th>
                            <th>Преподаватель</th>
                            <th></th>
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
                                    ${schedule.visitedUser.username}
                                </td>
                                <td>
                                    <div class="d-grid gap-2">
                                        <#if schedule.isOverdue()>
                                            <label class="col-form-label">Посещение просроченно</label>
                                        <#else>
                                            <#if schedule.isVisited()>
                                                <#if access[i] == true>
                                                    <form action="/schedule/view/${schedule.id}" method="get">
                                                        <button type="submit"
                                                                class="btn btn-outline-secondary btn-xs ms-3"
                                                                style="width: 100%; height: 100%">
                                                            Подтвердить
                                                            посещение
                                                        </button>
                                                        <input type="hidden" name="_csrf"
                                                               value="${_csrf.token}">
                                                    </form>
                                                <#else>
                                                    <form action="/schedule/view/${schedule.id}" method="get">
                                                        <button type="submit"
                                                                class="btn btn-outline-secondary btn-xs ms-3"
                                                                style="width: 100%; height: 100%">
                                                            Посещение
                                                        </button>
                                                        <input type="hidden" name="_csrf"
                                                               value="${_csrf.token}">
                                                    </form>
                                                </#if>
                                            <#else>
                                                <form class="me-3" action="/schedule/add/${schedule.id}"
                                                      method="get">
                                                    <button type="submit"
                                                            class="btn btn-outline-secondary btn-xs ms-3"
                                                            style="width: 100%; height: 100%">
                                                        Начать
                                                        посещение
                                                    </button>
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                </form>
                                            </#if>
                                        </#if>
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