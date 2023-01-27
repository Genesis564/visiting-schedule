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
                        <#if schedule.isVisited()>
                            <div class="card-header bg-transparent">${schedule.visiting.date}</div>
                        <#else>
                            <div class="card-header bg-transparent">${schedule.visitingWeek}</div>
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">Посещающий занятие: ${schedule.visitorUser.username}</h5>
                            <h5 class="card-title">Преподаватель: ${schedule.visitedUser.username}</h5>
                        </div>
                        <div class="card-footer text-muted">
                            <#if schedule.isOverdue()>
                                <label class="col-form-label">Посещение просроченно</label>
                            <#else>
                                <#if schedule.isVisited()>
                                    <#if accessToConfirmVisiting[i] == true>
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
                                    <#if accessToCreateVisiting[i] == true>
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
                                    <#else>
                                        <label class="col-form-label">Посещение запланировано</label>
                                    </#if>
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
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            let dataDoc = document.querySelectorAll(".card-header");
            dataDoc.forEach((elem) => {
                let curr;
                let str = elem.innerHTML;
                if (str.split('-W').length === 2) {
                    let splitDate = str.split('-W');
                    let week = splitDate[1].split("0")
                    if (week.length === 2) {
                        curr = new Date(getDateOfWeek(splitDate[0], week[1]));
                    } else {
                        curr = new Date(getDateOfWeek(splitDate[0], week[0]));
                    }
                    let inputData = new Date(curr);
                    let month = inputData.getMonth() + 1
                    let lastDayOfWeek = new Date(inputData);
                    lastDayOfWeek.setDate(inputData.getDate() + 6);
                    let minDate = ((inputData.getDate() < 10) ? "0" : "") + inputData.getDate() + ((month < 10) ? ".0" : ".") + month + "." + inputData.getFullYear();
                    let maxDate = ((lastDayOfWeek.getDate() < 10) ? "0" : "") + lastDayOfWeek.getDate() + ((lastDayOfWeek.getMonth() < 10) ? ".0" : ".") + (lastDayOfWeek.getMonth() + 1) + "." + lastDayOfWeek.getFullYear();
                    elem.innerHTML = "Посещение запланировано на неделю с " + minDate + " по " + maxDate;
                }else if (str.split('-').length === 3){
                    let splitDate = str.split('-');
                    elem.innerHTML = "Посещение прошло: " + splitDate[2] + "." + splitDate[1] + "." + splitDate[0];
                }
            })
        })


        function getDateOfWeek(year, week) {
            let simple = new Date(year, 0, 1 + (week - 1) * 7, 5);
            let dow = simple.getDay();
            let ISOweekStart = simple;
            if (dow <= 4)
                ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
            else
                ISOweekStart.setDate(simple.getDate() + 8 - (simple.getDay()));
            return ISOweekStart;
        }
    </script>
</@c.page>