<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4">
            <#assign
            i = 0
            >
            <#list schedules as schedule>
                <div class="col">
                    <div class="card text-center my-3 h-100">
                        <div class="card-header bg-transparent">Неделя: ${schedule.visitingWeek}</div>
                        <div class="card-body">
                            <h5 class="card-title">Посещающий занятие: ${schedule.visitorUser.username}</h5>
                            <h5 class="card-title">Преподаватель: ${schedule.visitedUser.username}</h5>
                        </div>
                        <div class="card-footer text-muted">
                            <#if schedule.isOverdue()>
                                <label class="col-form-label">Посещение просроченно</label>
                            <#else>
                                <#if schedule.isVisited()>
                                    <#if access[i] == true>
                                        <form action="/schedule/view/${schedule.id}" method="get">
                                            <button type="submit"
                                                    class="btn btn-outline-secondary btn-xs"
                                                    style="width: 100%; height: 100%">
                                                Подтвердить
                                                посещение
                                            </button>
                                            <input type="hidden" name="_csrf"
                                                   value="${_csrf.token}">
                                        </form>
                                    <#else>
                                        <#if schedule.isWaitingToConfirm()>
                                            <form action="/schedule/view/${schedule.id}" method="get">
                                                <button type="submit"
                                                        class="btn btn-outline-secondary btn-xs"
                                                        style="width: 100%; height: 100%">
                                                    Редактировать посещение
                                                </button>
                                                <input type="hidden" name="_csrf"
                                                       value="${_csrf.token}">
                                            </form>
                                        <#else>
                                            <form action="/schedule/view/${schedule.id}" method="get">
                                                <button type="submit"
                                                        class="btn btn-outline-secondary btn-xs"
                                                        style="width: 100%; height: 100%">
                                                    Просмотреть посещение
                                                </button>
                                                <input type="hidden" name="_csrf"
                                                       value="${_csrf.token}">
                                            </form>
                                        </#if>
                                    </#if>
                                <#else>
                                    <form class="me-3" action="/schedule/add/${schedule.id}"
                                          method="get">
                                        <button type="submit"
                                                class="btn btn-outline-secondary btn-xs"
                                                style="width: 100%; height: 100%">
                                            Начать
                                            посещение
                                        </button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    </form>
                                </#if>
                            </#if>
                        </div>
                    </div>
                </div>
                <#assign
                i = i+1
                >
            </#list>
        </div>
    </div>
</@c.page>