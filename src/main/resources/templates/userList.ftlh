<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4">
        <#list users as user>
            <div class="col">
                <div class="card text-center my-3 h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <#if user.lastname??>
                                ${user.lastname}
                            </#if>
                            <#if user.firstname??>
                                ${user.firstname}
                            </#if>
                            <#if user.middlename??>
                                ${user.middlename}
                            </#if>
                        </h5>
                        <p class="card-text">
                            Имя пользователя: ${user.username}
                        </p>
                        <p class="card-text">
                            Роли:
                            <#list user.roles as role>
                                ${role.roleName}
                            </#list>
                        </p>
                    </div>
                    <div class="card-footer text-muted">
                        <form action="/admin/${user.id}" method="get">
                            <button type="button" class="btn btn-outline-secondary ms-3"
                                    data-bs-toggle="modal"
                                    href="#user-${user.id}"
                                    data-bs-whatever="@getbootstrap">Редактировать
                            </button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <#list users as user>
        <div class="modal fade" id="user-${user.id}" tabindex="-1"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование пользователя</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeModal(${user.id})"></button>
                    </div>

                    <form action="/admin" method="post">
                        <div class="modal-body">
                            <div class="mb-3">
                                <div class="form-group row mt-2">
                                    <label for="recipient-name" class="col-sm-4 col-form-label">Имя
                                        пользователя: </label>
                                    <div class="col-sm-7 d-flex">
                                        <input class="form-control flex-fill" required type="text" name="username" value="${user.username}">
                                    </div>
                                </div>
                                <div class="form-group row mt-2">
                                    <label for="recipient-name" class="col-sm-4 col-form-label">Фамилия: </label>
                                    <div class="col-sm-7 d-flex">
                                        <input class="form-control flex-fill" required type="text" name="lastname" value="${user.lastname}">
                                    </div>
                                </div>
                                <div class="form-group row mt-2">
                                    <label for="recipient-name" class="col-sm-4 col-form-label">Имя: </label>
                                    <div class="col-sm-7 d-flex">
                                        <input class="form-control flex-fill" required type="text" name="firstname" value="${user.firstname}">
                                    </div>
                                </div>

                                    <div class="form-group row mt-2">
                                        <label for="recipient-name" class="col-sm-4 col-form-label">Отчество: </label>
                                        <div class="col-sm-7 d-flex">
                                            <input class="form-control flex-fill" type="text" name="middlename"
                                                    <#if user.middlename??>
                                                    value="${user.middlename}"
                                                    </#if>
                                            >
                                        </div>
                                    </div>
                                <div class="form-group row my-2">
                                    <label for="recipient-name" class="col-sm-4 col-form-label">Должность: </label>
                                    <div class="col-sm-7 d-flex">
                                        <input class="form-control flex-fill" required type="text" name="position"
                                               value="${user.position.positionName}">
                                    </div>
                                </div>
                                <div class="form-group row my-2">
                                    <label for="recipient-name" class="col-sm-4 col-form-label">Новый пароль: </label>
                                    <div class="col-sm-7 d-flex align-items-center">
                                        <input type="hidden" name="password" id="password-${user.id}" value=""
                                                   class="form-control flex-fill" data-size="20">
                                        <a href="#" title="" id="passwordLink-${user.id}" onclick="passwordLinkClick(this)">
                                            <span>
                                                <em>Нажмите, чтобы ввести пароль</em>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                                <#list roles as role>
                                    <div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" onclick="deRequireCb(${user.id})" id="roleCheckbox-${user.id}"
                                                   name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ${role.roleName}
                                            </label>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="closeModal(${user.id})">Закрыть</button>
                            <div>
                                <input type="hidden" value="${user.id}" name="userId">
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

    <script>
        function passwordLinkClick(obj){
            obj.style.display = "none";
            obj.parentNode.firstElementChild.setAttribute('type', 'text');
            obj.parentNode.firstElementChild.required = true;
        }

        function closeModal(userId){
            document.getElementById("password-"+userId).setAttribute('type', 'hidden');
            document.getElementById("password-"+userId).setAttribute('value', '');
            document.getElementById("password-"+userId).required = false;
            document.getElementById("passwordLink-"+userId).style.display = "";
        }

        function deRequireCb(id) {
            let el = document.querySelectorAll("#roleCheckbox-"+id);

            let atLeastOneChecked = false; //at least one cb is checked
            for (let i = 0; i < el.length; i++) {
                if (el[i].checked === true) {
                    atLeastOneChecked = true;
                }
            }

            if (atLeastOneChecked === true) {
                for (let i = 0; i < el.length; i++) {
                    el[i].required = false;
                }
            } else {
                for (let i = 0; i < el.length; i++) {
                    el[i].required = true;
                }
            }
        }
    </script>
</@c.page>