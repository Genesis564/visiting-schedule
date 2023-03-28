<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Цель посещения занятия</span>
            <input type="text" class="form-control" value="${visiting.purposeOfTheVisit}" readonly
                   name="purposeOfTheVisit" id="purposeOfTheVisit">
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
            <span class="input-group-text" >Количество обучающихся</span>
            <input type="text" class="form-control" value="${visiting.numberOfStudents}" readonly>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" >Должность посетившего занятия</span>
            <input type="text" class="form-control" value="${visiting.position.positionName}" readonly>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" >Учебная дисциплина</span>
            <input type="text" class="form-control" value="${visiting.academicDiscipline.disciplineName}" readonly>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" >Тема занятия</span>
            <input type="text" class="form-control" value="${visiting.lessonTopic}" readonly name="lessonTopic"
                   id="lessonTopic">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Цель занятия</span>
            <input type="text" class="form-control" value="${visiting.purposeOfTheLesson}" readonly name="purposeOfTheLesson"
                   id="purposeOfTheLesson">
        </div>


        <div class="mb-3">
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
                    <textarea class="form-control" readonly id="virtuesOfOccupation" style="height: 15vh">${conclusion.virtuesOfOccupation}</textarea>
                    <label for="virtuesOfOccupation">Достоинства занятия</label>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="form-floating">
                    <textarea class="form-control" readonly id="problems" style="height: 15vh">${conclusion.problems}</textarea>
                    <label for="problems">Проблемы, замечания</label>
                </div>
            </div>
            <div class="text-center mb-3">
                <label class="mb-3">План устранения замечаний:</label>
            </div>
            <div id="events">
                <#list events as event>
                    <div class="input-group-sm mb-3">
                        <span class="input-group-text d-flex justify-content-between">
                                Мероприятие
                            <div class="d-flex">
                                <label class="form-check-label me-1" for="completionMark">
                                 Отметка о выполнении
                                </label>
                                <input class="form-check-input align-self-center" type="checkbox" disabled id="completionMark">
                            </div>
                        </span>
                        <textarea class="form-control" readonly id="eventName" style="height: 15vh">${event.eventName}</textarea>
                    </div>
                </#list>
            </div>
        </#if>


        <div class="input-group mb-3">
            <form action="/home" method="get">
                <div class="mb-3 me-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-secondary btn-lg mb-6" type="submit">Назад</button>
                </div>
            </form>
            <#if access>
                <form action="/schedule/view/${schedule.id}" method="post">
                    <div class="mb-3 me-3">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-primary btn-lg mb-6" type="submit">Подтвердить</button>
                    </div>
                </form>
            </#if>
            <#if subjectToEditing>
            <form action="/schedule/edit/${schedule.id}" method="get">
                <div class="mb-3 me-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-primary btn-lg mb-6" id="editBtn" type="submit">Редактировать посещение</button>
                </div>
            </form>
                <#if summingUpSubjectToEditing>
                    <form action="/schedule/edit/summing-up/${schedule.id}" method="get">
                        <div class="mb-3 me-3">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-primary btn-lg mb-6" id="editBtn" type="submit">Редактировать итоги</button>
                        </div>
                    </form>
                </#if>
            </#if>
        </div>
    </div>


    <script>

        let currentScore;
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
        })
    </script>
</@c.page>
