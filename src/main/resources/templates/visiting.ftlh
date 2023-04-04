<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>
        <div class="input-group mb-3">
            <div class="form-floating">
                    <textarea class="form-control" readonly name="purposeOfTheVisit" id="purposeOfTheVisit"
                              style="height: 15vh">${visiting.purposeOfTheVisit}</textarea>
                <label for="purposeOfTheVisit">Цель посещения занятия</label>
            </div>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Дата</span>
            <input type="date" class="form-control" readonly value="${visiting.date}" name="visitingDate"
                   id="visitingDate">
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Группа</span>
            <input type="text" class="form-control" value="${visiting.studyGroup.groupName}" readonly>
        </div>


        <div class="input-group mb-3">
            <span class="input-group-text">Количество обучающихся</span>
            <input type="number" class="form-control" value="${visiting.numberOfStudents}" readonly>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Должность посетившего занятия</span>
            <input type="text" class="form-control" value="${visiting.position.positionName}" readonly>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Учебная дисциплина</span>
            <input type="text" class="form-control" value="${visiting.academicDiscipline.disciplineName}" readonly>
        </div>

        <div class="input-group mb-3">
            <div class="form-floating">
                <textarea class="form-control" readonly name="lessonTopic" id="lessonTopic"
                          style="height: 15vh">${visiting.lessonTopic}</textarea>
                <label for="lessonTopic">Тема занятия</label>
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="form-floating">
                    <textarea class="form-control" readonly name="purposeOfTheLesson" id="purposeOfTheLesson"
                              style="height: 15vh">${visiting.purposeOfTheLesson}</textarea>
                <label for="purposeOfTheLesson">Цель занятия</label>
            </div>
        </div>
        <div class="mb-3">
            <#assign
            maxScore = 0
            >
            <#list criteries as criteria>
                <#assign
                maxScore = (maxScore!0) + 3
                >
            </#list>
            <div class="container text-left">
                <table class="table table-sm table-borderless">
                    <caption>
                        <label for="recipient-name" class="col-form-label" id="scoreInfo"></label>
                    </caption>
                    <thead>
                    <tr>
                        <th scope="col">Критерии <br>оценивания <br>(баллы)</th>
                        <th scope="col"></th>
                        <th scope="col">Показатели</th>
                        <th scope="col">Баллы</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <table class="table table-bordered table-sm">
                                <tbody>
                                <#list criteries as criteria>
                                    <tr>
                                        <td colspan="3"><b>${criteria.criteriaName}</b></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>${criteria.valueOfOnePoint}</td>
                                        <td>
                                            <input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="1" disabled onchange="selectValueOfCriteria();">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>${criteria.valueOfTwoPoint}</td>
                                        <td>
                                            <input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="2" disabled onchange="selectValueOfCriteria();">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>${criteria.valueOfThreePoint}</td>
                                        <td>
                                            <input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="3" disabled onchange="selectValueOfCriteria();">
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <#if visiting.conclusion??>
            <div class="input-group mb-3">
                <div class="form-floating">
                    <textarea class="form-control" readonly id="virtuesOfOccupation"
                              style="height: 15vh">${conclusion.virtuesOfOccupation}</textarea>
                    <label for="virtuesOfOccupation">Достоинства занятия</label>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="form-floating">
                    <textarea class="form-control" readonly id="problems"
                              style="height: 15vh">${conclusion.problems}</textarea>
                    <label for="problems">Проблемы, замечания</label>
                </div>
            </div>
            <#if events?size != 0>
                <div class="text-center mb-3">
                    <label class="mb-3">План устранения замечаний:</label>
                </div>
            </#if>
            <div id="events">
                <#list events as event>
                    <div class="input-group-sm mb-3">
                        <span class="input-group-text d-flex justify-content-between">
                                Мероприятие
                            <div class="d-flex">
                                <label class="form-check-label me-1" for="completionMark">
                                 Отметка о выполнении
                                </label>
                                <input class="form-check-input align-self-center" type="checkbox"
                                       <#if !subjectToCompletionEvent>
                                           disabled
                                           <#if event.completionMark>
                                               checked
                                           </#if>
                                       <#else>
                                           <#if event.completionMark>
                                               disabled
                                               checked
                                           </#if>
                                       </#if>
                                       id="completionMark">
                            </div>
                        </span>
                        <textarea class="form-control" readonly id="eventName"
                                  style="height: 15vh">${event.eventName}</textarea>
                    </div>
                </#list>
            </div>
        </#if>


        <div class="input-group mb-3">
            <form action="/home" method="get">
                <div class="mb-3 me-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-secondary mb-6" type="submit">Назад</button>
                </div>
            </form>
            <#if subjectToCompletionEvent>
                <div class="mb-3 me-3">
                    <button class="btn btn-primary mb-6" onclick="editCompletionMark()" type="button">Изменить отметку о
                        выполнении мероприятия
                    </button>
                </div>
            </#if>
            <#if access>
                <form action="/schedule/view/${schedule.id}" method="post">
                    <div class="mb-3 me-3">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-primary mb-6" type="submit">Подтвердить</button>
                    </div>
                </form>
            </#if>
            <#if subjectToEditing>
                <form action="/schedule/edit/${schedule.id}" method="get">
                    <div class="mb-3 me-3">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-primary mb-6" id="editBtn" type="submit">Редактировать посещение</button>
                    </div>
                </form>
                <#if summingUpSubjectToEditing>
                    <form action="/schedule/edit/summing-up/${schedule.id}" method="get">
                        <div class="mb-3 me-3">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-primary mb-6" id="editBtn" type="submit">Редактировать итоги</button>
                        </div>
                    </form>
                </#if>
            </#if>
        </div>
    </div>


    <script>

        let currentScore=0;
        let criteriaScore = [];
        <#list criteriaScore as score>
        criteriaScore.push(${score.score});
        </#list>

        document.addEventListener("DOMContentLoaded", () => {
            let i = 0;
            <#list criteries as criteria>
            document.querySelectorAll('input[name="${criteria.criteriaName}"]').forEach((elem) => {
                if (elem.value == criteriaScore[i]) {
                    elem.checked = true;
                }
            })
            i++;
            </#list>
            criteriaScore.forEach((elem)=>{
                currentScore = currentScore + elem;
            })
            document.getElementById("scoreInfo").innerHTML = 'Общий балл: '+currentScore+'(Максимальный балл ${maxScore})';
        })

        async function editCompletionMark() {
            let completionMarks = []
            document.querySelectorAll("#completionMark").forEach((elem) => {
                completionMarks.push(elem.checked)
            })
            await fetch('/schedule/edit/event/completion-mark/${visiting.schedule.id}' + '?_csrf=${_csrf.token}', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(completionMarks)
            }).then((res) => {
                if (res.ok) {
                    window.location.href = "/home";
                }
            })
        }

    </script>
</@c.page>
