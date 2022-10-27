<#import "parts/common.ftlh" as c>

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
            <select class="form-select" aria-label="Default select example">
                <option selected disabled hidden>Study group</option>
                <#list studyGroups as studyGroup>
                    <option value="${studyGroup.groupName!"No value"}">${studyGroup.groupName!"No value"}</option>
                </#list>
            </select>
            <form action="/admin/schedule/add/create-study-group" method="get">
                <button class="btn btn-outline-secondary" type="submit">Add new value</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" aria-label="Default select example">
                <option selected disabled hidden>Position</option>
                <#list positions as position>
                    <option value="${position.positionName!"No value"}">${position.positionName!"No value"}</option>
                </#list>
            </select>
            <form action="/admin/schedule/add/create-position" method="get">
                <button class="btn btn-outline-secondary" type="submit">Add new value</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>

        <div class="input-group mb-3">
            <select class="form-select" aria-label="Default select example">
                <option selected disabled hidden>Discipline</option>
                <#list academicDisciplins as academicDiscipline>
                    <option value="${academicDiscipline.disciplineName!"No value"}">${academicDiscipline.disciplineName!"No value"}</option>
                </#list>
            </select>
            <form action="/admin/schedule/add/create-discipline" method="get">
                <button class="btn btn-outline-secondary" type="submit">Add new value</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>

        <div class="mb-3">
            <div class="container text-left">
                <#list criteries as criteria>
                    <div class="row">
                        <div class="col-4">
                            ${criteria}
                        </div>
                        <div class="col-3">
                            <div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="${criteria}" id="criteriaValues1"
                                           value="0">
                                    <label class="form-check-label" for="criteriaValues1">0</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="${criteria}" id="criteriaValues2"
                                           value="1">
                                    <label class="form-check-label" for="criteriaValues2">1</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="${criteria}" id="criteriaValues3"
                                           value="2">
                                    <label class="form-check-label" for="criteriaValues3">2</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="${criteria}" id="criteriaValues4"
                                           value="3">
                                    <label class="form-check-label" for="criteriaValues4">3</label>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                        </div>
                    </div>
                </#list>
            </div>
        </div>


        <form action="/admin/schedule/add" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-outline-primary btn-lg" type="submit">Save</button>
        </form>
    </div>
</@c.page>
