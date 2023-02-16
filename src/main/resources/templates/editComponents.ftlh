<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="col-sm-10 align-self-center">
                        <label class="me-2">Должности</label>
                        <@cr.create "/admin/add/create-position","должность","Должность","positionName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn btn-outline-secondary btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#positionCollapse" aria-expanded="true"
                                aria-controls="positionCollapse" onclick="collapseButtonClick(this)">
                            Скрыть
                        </button>
                    </div>
                </div>
            </div>
            <div class="collapse show multi-collapse" id="positionCollapse">
                <div class="card-body">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-3">
                        <#list positions as position>
                            <div class="col">
                                <div class="card text-center my-3 h-100">
                                    <div class="card-body">
                                        <h5 class="card-title">${position.positionName}</h5>
                                    </div>
                                    <div class="card-footer text-muted">
                                        <button type="button" class="btn btn-outline-secondary ms-3"
                                                data-bs-toggle="modal"
                                                href="#position-${position.id}"
                                                data-bs-whatever="@getbootstrap">Редактировать
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="position-${position.id}" tabindex="-1"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование
                                                должности</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <form action="/admin/edit/position" method="post">
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <div class="form-group row mt-2">
                                                        <label for="recipient-name" class="col-sm-4 col-form-label">Наименование должности: </label>
                                                        <div class="col-sm-7 d-flex">
                                                            <input class="form-control flex-fill" type="text"
                                                                   name="positionName" value="${position.positionName}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Закрыть
                                                </button>
                                                <div>
                                                    <input type="hidden" value="${position.id}" name="positionId">
                                                    <button class="btn btn-primary" type="submit">Сохранить</button>
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="col-sm-10 align-self-center">
                        <label class="me-2">Дисциплины</label>
                        <@cr.create "/admin/add/create-discipline","учебную дисциплину","Учебная дисциплина","disciplineName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn btn-outline-secondary btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#academicDisciplineCollapse" aria-expanded="true"
                                aria-controls="academicDisciplineCollapse" onclick="collapseButtonClick(this)">
                            Скрыть
                        </button>
                    </div>
                </div>
            </div>
            <div class="collapse show multi-collapse" id="academicDisciplineCollapse">
                <div class="card-body">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-3">
                        <#list academicDisciplines as academicDiscipline>
                            <div class="col">
                                <div class="card text-center my-3 h-100">
                                    <div class="card-body">
                                        <h5 class="card-title">${academicDiscipline.disciplineName}</h5>
                                    </div>
                                    <div class="card-footer text-muted">
                                        <button type="submit" class="btn btn-outline-secondary btn-xs"
                                                style="width: 100%; height: 100%">Редактировать
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="col-sm-10 align-self-center">
                        <label class="me-2">Группы</label>
                        <@cr.create "/admin/add/create-study-group","учебную группу","Учебная группа","groupName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn btn-outline-secondary btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#studyGroupCollapse" aria-expanded="true"
                                onclick="collapseButtonClick(this)">
                            Скрыть
                        </button>
                    </div>
                </div>
            </div>
            <div class="collapse show multi-collapse" id="studyGroupCollapse">
                <div class="card-body">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-3">
                        <#list studyGroups as studyGroup>
                            <div class="col">
                                <div class="card text-center my-3 h-100">
                                    <div class="card-body">
                                        <h5 class="card-title">${studyGroup.groupName}</h5>
                                    </div>
                                    <div class="card-footer text-muted">
                                        <button type="submit" class="btn btn-outline-secondary btn-xs"
                                                style="width: 100%; height: 100%">Редактировать
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function collapseButtonClick(elem) {
            if (elem.ariaExpanded == 'true') {
                elem.innerHTML = "Скрыть"
            } else {
                elem.innerHTML = "Открыть"
            }
        }
    </script>
</@c.page>
