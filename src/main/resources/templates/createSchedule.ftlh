<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Date</span>
            <input type="text" class="form-control" name="scheduleDate">
        </div>

        <div class="mb-3">
            <select class="form-select" aria-label="Default select example">
                <option selected disabled hidden>Status</option>
                <#list statuses as status>
                    <option value="${status}">${status}</option>
                </#list>
            </select>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="studyGroupsSelect" aria-label="Default select example"
                    onchange="selectedGroup(this);">
                <option value="" selected disabled hidden>Study group</option>
                <#list studyGroups as studyGroup>
                    <option value="${studyGroup.id!"No value"}">${studyGroup.groupName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-study-group","study group","groupName"/>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="positionsSelect" aria-label="Default select example"
                    onchange="selectedPosition(this);">
                <option value="" selected disabled hidden>Position</option>
                <#list positions as position>
                    <option value="${position.id!"No value"}">${position.positionName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-position","position","positionName"/>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" id="academicDisciplinsSelect" aria-label="Default select example"
                    onchange="selectedDiscipline(this);">
                <option value="" selected disabled hidden>Discipline</option>
                <#list academicDisciplins as academicDiscipline>
                    <option value="${academicDiscipline.id!"No value"}">${academicDiscipline.disciplineName!"No value"}</option>
                </#list>
            </select>
            <@cr.create "/admin/schedule/add/create-discipline","discipline","disciplineName"/>
        </div>

        <div class="mb-3">
            <button class="btn btn-primary mb-3" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseCriteria" aria-expanded="false" aria-controls="collapseCriteria">
                Добавить критерий оценивания
            </button>
            <div class="collapse mb-3" id="collapseCriteria">
                <form action="/admin/schedule/add/create-visiting-criteria" method="post">
                    <label for="recipient-name" class="col-form-label">Название критерия: </label>
                    <input type="text" class="form-control mb-2" name="critariaName">
                    <label for="recipient-name" class="col-form-label">Значение для 1 балла: </label>
                    <input type="text" class="form-control mb-2" name="valueOfOnePoint">
                    <label for="recipient-name" class="col-form-label">Значение для 2 балла: </label>
                    <input type="text" class="form-control mb-2" name="valueOfTwoPoint">
                    <label for="recipient-name" class="col-form-label">Значение для 3 балла: </label>
                    <input type="text" class="form-control mb-4" name="valueOfThreePoint">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-primary btn-lg mb-3 me-2" type="submit">Сохранить</button>
                    <button class="btn btn-secondary mb-3" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseCriteria" aria-expanded="false"
                            aria-controls="collapseCriteria">
                        Отмена
                    </button>
                </form>
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
                        <label for="recipient-name" class="col-form-label">Общий балл: (Максимальный
                            балл ${maxScore})</label>
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
                                                   value="1">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>${criteria.valueOfTwoPoint}</td>
                                        <td><input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="2"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>${criteria.valueOfThreePoint}</td>
                                        <td><input class="form-check-input" type="radio"
                                                   name="${criteria.criteriaName}"
                                                   id="${criteria.criteriaName}"
                                                   value="3"></td>
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
                <button class="btn btn-outline-primary btn-lg mb-6" type="submit">Save</button>
            </div>
        </form>
    </div>
    <script>

        let groupId;
        let positionId;
        let disciplineId;
        let criteriaScore = [];



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
            </#list>
            visitingRequestDTO.append("studyGroupId", groupId)
            visitingRequestDTO.append("positionId", positionId)
            visitingRequestDTO.append("academicDisciplineId", disciplineId)
            visitingRequestDTO.append("criteriaScoreIds", criteriaScore)

            let response = await fetch('/admin/schedule/add/visiting', {
                method: 'POST',
                body: visitingRequestDTO
            });
        }

    </script>
</@c.page>
