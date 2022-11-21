<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Дата</span>
            <input type="date" class="form-control" name="visitingDate" id="visitingDate">
        </div>

        <div class="mb-3">
            <select class="form-select" aria-label="Default select example" onchange="selectedStatus(this);">
                <option selected disabled hidden>Статус</option>
                <#list statuses as status>
                    <option value="${status}">${status}</option>
                </#list>
            </select>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="studyGroupsSelect" aria-label="Default select example"
                    onchange="selectedGroup(this);">
                <option value="" selected disabled hidden>Группа</option>
                <#list studyGroups as studyGroup>
                    <option value="${studyGroup.id!"No value"}">${studyGroup.groupName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-study-group","учебную группу","groupName"/>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="positionsSelect" aria-label="Default select example"
                    onchange="selectedPosition(this);">
                <option value="" selected disabled hidden>Должность посетившего занятия</option>
                <#list positions as position>
                    <option value="${position.id!"No value"}">${position.positionName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-position","должность","positionName"/>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="academicDisciplinsSelect" aria-label="Default select example"
                    onchange="selectedDiscipline(this);">
                <option value="" selected disabled hidden>Учебная дисциплина</option>
                <#list academicDisciplins as academicDiscipline>
                    <option value="${academicDiscipline.id!"No value"}">${academicDiscipline.disciplineName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-discipline","учебную дисциплину","disciplineName"/>
        </div>
        <div class="mb-3">
            <button class="btn btn-primary mb-3" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseCriteria" aria-expanded="false" aria-controls="collapseCriteria">
                Добавить критерий оценивания
            </button>
            <div class="collapse mb-2" id="collapseCriteria">
                <form action="/admin/schedule/add/create-visiting-criteria" method="post">
                    <label for="recipient-name" class="col-form-label">Название критерия: </label>
                    <input type="text" class="form-control mb-1" name="critariaName">
                    <label for="recipient-name" class="col-form-label">Значение для 1 балла: </label>
                    <input type="text" class="form-control mb-1" name="valueOfOnePoint">
                    <label for="recipient-name" class="col-form-label">Значение для 2 балла: </label>
                    <input type="text" class="form-control mb-1" name="valueOfTwoPoint">
                    <label for="recipient-name" class="col-form-label">Значение для 3 балла: </label>
                    <input type="text" class="form-control mb-2" name="valueOfThreePoint">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-primary btn-lg mb-2 me-2" type="submit">Сохранить</button>
                    <button class="btn btn-secondary mb-2" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseCriteria" aria-expanded="false"
                            aria-controls="collapseCriteria">
                        Отмена
                    </button>
                </form>
            </div>
        </div>

        <div class="mb-3">
            <#assign
            currentScore = 0
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
                                                   value="1" onchange="selectValueOfCriteria();">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>${criteria.valueOfTwoPoint}</td>
                                        <td>
                                            <input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="2" onchange="selectValueOfCriteria();">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>${criteria.valueOfThreePoint}</td>
                                        <td>
                                            <input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="3" onchange="selectValueOfCriteria();">
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

        <form id="submitForm">
            <div class="mb-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-outline-primary btn-lg mb-6" type="submit">Сохранить</button>
            </div>
        </form>

        <div>
            <label id="labelFirst"></label>
            <label id="labelSecond"></label>
        </div>
    </div>
    <script>

        let currentScore;
        let groupId;
        let positionId;
        let disciplineId;
        let status;
        let criteriaScore = [];
        let criteria = [];
        let str = "${schedule.visitingWeek}";
        let splitDate = str.split('-W');
        let curr = new Date(getDateOfWeek(splitDate[0],splitDate[1]));
        let last = curr + 6;
        let firstDay = new Date(curr.setDate(first)).toUTCString();
        let lastDay = new Date(curr.setDate(last)).toUTCString();


        document.addEventListener("DOMContentLoaded",() =>{

            document.getElementById("scoreInfo").innerHTML = 'Общий балл: 0 (Максимальный балл ${maxScore})';
            document.getElementById("visitingDate").valueAsDate = new Date(curr);
            document.getElementById("visitingDate").setAttribute("min",curr.toLocaleDateString("en-US"));
            document.getElementById("visitingDate").setAttribute("max",lastDay);
        })

        function selectValueOfCriteria(){
            currentScore = 0
            <#list criteries as criteria>
            if(document.querySelector('input[name="${criteria.criteriaName}"]:checked')!=null){
                currentScore = (+currentScore) + (+document.querySelector('input[name="${criteria.criteriaName}"]:checked').value);
            }
            </#list>
            document.getElementById("scoreInfo").innerHTML = "Общий балл: "+ currentScore +"   (Максимальный балл ${maxScore})";
        }

        function getDateOfWeek(year,week){
            let simple = new Date(year, 0, 1 + (week - 1) *7);
            let dow = simple.getDay();
            let ISOweekStart = simple;
            if (dow <= 4)
                ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
            else
                ISOweekStart.setDate(simple.getDate() + 8 - (simple.getDay()));
            return ISOweekStart;
        }

        function selectedStatus(sel) {
            status = sel.value;
        }

        function selectedGroup(sel) {
            groupId = sel.value;
        }

        function selectedPosition(sel) {
            positionId = sel.value;
        }

        function selectedDiscipline(sel) {
            disciplineId = sel.value;
        }

        submitForm.onsubmit = async (e) => {
            e.preventDefault();
            let visitingRequestDTO = new FormData(submitForm);
            <#list criteries as criteria>
            criteriaScore.push(document.querySelector('input[name="${criteria.criteriaName}"]:checked').value);
            criteria.push(${criteria.id});
            </#list>
            visitingRequestDTO.append("studyGroupId", groupId)
            visitingRequestDTO.append("positionId", positionId)
            visitingRequestDTO.append("academicDisciplineId", disciplineId)
            visitingRequestDTO.append("criteriaScoreIds", criteriaScore)
            visitingRequestDTO.append("status", status)
            visitingRequestDTO.append("date", document.getElementById('visitingDate').value)
            visitingRequestDTO.append("criterionIds", criteria)

            let response = await fetch('/admin/schedule/add/visiting', {
                method: 'POST',
                body: visitingRequestDTO
            });
        }

    </script>
</@c.page>
