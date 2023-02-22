<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>

<@c.page>
    <div>
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="col-sm-10 d-flex align-self-center">
                        <label class="me-2">Должности</label>
                        <@cr.create "/admin/add/create-position","должность","Должность","positionName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn d-flex btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#positionCollapse" aria-expanded="true"
                                aria-controls="positionCollapse" onclick="collapseButtonClick(this)">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi flex-fill bi-chevron-up" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
                            </svg>
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
                                    <div class="card-body d-flex align-items-center align-self-center">
                                        <h5 class="card-title">${position.positionName}</h5>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between flex-row text-muted">
                                        <button type="button" class="btn btn-sm btn-outline-secondary me-3"
                                                data-bs-toggle="modal"
                                                href="#position-${position.id}"
                                                data-bs-whatever="@getbootstrap">Редактировать
                                        </button>
                                        <button type="button" class="btn d-flex align-self-center btn-sm" onclick="deletePosition(${position.id})">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                            </svg>
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
                                        <form id="positionForm">
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
                    <div class="col-sm-10 d-flex align-self-center">
                        <label class="me-2">Дисциплины</label>
                        <@cr.create "/admin/add/create-discipline","учебную дисциплину","Учебная дисциплина","disciplineName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn d-flex btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#academicDisciplineCollapse" aria-expanded="true"
                                aria-controls="academicDisciplineCollapse" onclick="collapseButtonClick(this)">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi flex-fill bi-chevron-up" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
                            </svg>
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
                                    <div class="card-body d-flex align-items-center align-self-center">
                                        <h5 class="card-title">${academicDiscipline.disciplineName}</h5>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between flex-row text-muted">
                                        <button type="button" class="btn btn-sm btn-outline-secondary me-3"
                                                data-bs-toggle="modal"
                                                href="#academicDiscipline-${academicDiscipline.id}"
                                                data-bs-whatever="@getbootstrap">Редактировать
                                        </button>
                                        <button type="button" class="btn d-flex align-self-center btn-sm" onclick="deleteAcademicDiscipline(${academicDiscipline.id})">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="academicDiscipline-${academicDiscipline.id}" tabindex="-1"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование
                                                дисциплины</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <form id="academicDisciplineForm">
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <div class="form-group row mt-2">
                                                        <label for="recipient-name" class="col-sm-4 col-form-label">Наименование дисциплины: </label>
                                                        <div class="col-sm-7 d-flex">
                                                            <input class="form-control flex-fill" type="text"
                                                                   name="disciplineName" value="${academicDiscipline.disciplineName}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Закрыть
                                                </button>
                                                <div>
                                                    <input type="hidden" value="${academicDiscipline.id}" name="academicDisciplineId">
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
                    <div class="col-sm-10 d-flex align-self-center">
                        <label class="me-2">Группы</label>
                        <@cr.create "/admin/add/create-study-group","учебную группу","Учебная группа","groupName"/>
                    </div>
                    <div class="col-auto align-self-center">
                        <button class="btn d-flex btn-sm" type="button" data-bs-toggle="collapse"
                                data-bs-target="#studyGroupCollapse" aria-expanded="true"
                                onclick="collapseButtonClick(this)">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi flex-fill bi-chevron-up" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
                            </svg>
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
                                    <div class="card-body d-flex align-items-center align-self-center">
                                        <h5 class="card-title">${studyGroup.groupName}</h5>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between flex-row text-muted">
                                        <button type="button" class="btn btn-sm btn-outline-secondary me-3"
                                                data-bs-toggle="modal"
                                                href="#studyGroup-${studyGroup.id}"
                                                data-bs-whatever="@getbootstrap">Редактировать
                                        </button>
                                        <button type="button" class="btn d-flex align-self-center btn-sm" onclick="deleteStudyGroup(${studyGroup.id})">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="studyGroup-${studyGroup.id}" tabindex="-1"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование
                                                группы</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <form id="studyGroupForm">
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <div class="form-group row mt-2">
                                                        <label for="recipient-name" class="col-sm-4 col-form-label">Наименование группы: </label>
                                                        <div class="col-sm-7 d-flex">
                                                            <input class="form-control flex-fill" type="text"
                                                                   name="groupName" value="${studyGroup.groupName}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Закрыть
                                                </button>
                                                <div>
                                                    <input type="hidden" value="${studyGroup.id}" name="studyGroupId">
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
        <div class="modal fade" id="errorMessage" tabindex="-1"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5">Произошла ошибка</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <div class="form-group row mt-2">
                                    <label for="recipient-name" class="col-form-label" id="errorMessageLabel"></label>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                Закрыть
                            </button>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        function collapseButtonClick(elem) {
            if (elem.ariaExpanded == 'true') {
                elem.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/> </svg>'
            } else {
                elem.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/> </svg>';
            }
        }
        let errorModal = document.getElementById("errorMessage");
        let errorLabel = document.getElementById("errorMessageLabel");

        async function deletePosition(positionId){
            let requestDTO = new FormData();

            requestDTO.append('_csrf',"${_csrf.token}");

            await fetch('/admin/delete/position/'+positionId, {
                method: 'DELETE',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }else{
                    errorLabel.innerHTML = "Удаление должности невозможно, существуют пользователи занимающие эту долность";
                    const modal = new bootstrap.Modal(errorModal)
                    modal.show();
                }
            })
        }

        async function deleteStudyGroup(studyGroupId){
            let requestDTO = new FormData();

            requestDTO.append('_csrf',"${_csrf.token}");

            await fetch('/admin/delete/study-group/'+studyGroupId, {
                method: 'DELETE',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }else{
                    errorLabel.innerHTML = "Удаление группы невозможно, существуют посещения с данной группой";
                    const modal = new bootstrap.Modal(errorModal)
                    modal.show();
                }
            })
        }

        async function deleteAcademicDiscipline(academicDisciplineId){
            let requestDTO = new FormData();

            requestDTO.append('_csrf',"${_csrf.token}");

            await fetch('/admin/delete/academic-discipline/'+academicDisciplineId, {
                method: 'DELETE',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }else{
                    errorLabel.innerHTML = "Удаление дисциплины невозможно, существуют посещения с данной дисциплинной";
                    const modal = new bootstrap.Modal(errorModal)
                    modal.show();
                }
            })
        }

        let positionForms = document.querySelectorAll('#positionForm')
        positionForms.forEach((elem)=>{
            elem.addEventListener('submit',(ev) => positionSubmit(ev,elem))
        })

        async function positionSubmit(e,elem) {
            e.preventDefault();
            let requestDTO = new FormData(elem);

            await fetch('/admin/edit/position/', {
                method: 'POST',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }
            });
        }

        let academicDisciplineForms = document.querySelectorAll('#academicDisciplineForm')
        academicDisciplineForms.forEach((elem)=>{
            elem.addEventListener('submit',(ev) => academicDisciplineSubmit(ev,elem))
        })

        async function academicDisciplineSubmit(e,elem){
            e.preventDefault();
            let requestDTO = new FormData(elem);

            await fetch('/admin/edit/academic-discipline/', {
                method: 'POST',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }
            });
        }

        let studyGroupForms = document.querySelectorAll('#studyGroupForm')
        studyGroupForms.forEach((elem)=>{
            elem.addEventListener('submit',(ev) => groupFormSubmit(ev,elem))
        })

        async function groupFormSubmit(e,elem){
            e.preventDefault();
            let requestDTO = new FormData(elem);

            await fetch('/admin/edit/study-group/', {
                method: 'POST',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }
            });
        }
    </script>
</@c.page>
