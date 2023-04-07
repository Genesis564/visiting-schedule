<#macro login path, buttonName,isRegistration>
    <form id="loginForm">

        <div class="form-group row">
            <div class="col-sm-7">
                <div id="alertPlaceholder"></div>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Имя пользователя: </label>
            <div class="col-sm-5">
                <input type="text" autocomplete="off" name="username" class="form-control" required
                       placeholder="Имя пользователя">
            </div>
        </div>
        <div class="form-group row mt-2">
            <label class="col-sm-2 col-form-label">Пароль: </label>
            <div class="col-sm-5">
                <input type="password" autocomplete="off" name="password" required class="form-control"
                       placeholder="Пароль">
            </div>
        </div>
        <#if isRegistration>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Фамилия : </label>
                <div class="col-sm-5">
                    <input type="text" autocomplete="off" name="lastname" required class="form-control"
                           placeholder="Фамилия">
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Имя : </label>
                <div class="col-sm-5">
                    <input type="text" autocomplete="off" name="firstname" required class="form-control"
                           placeholder="Имя">
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Отчество : </label>
                <div class="col-sm-5">
                    <input type="text" autocomplete="off" name="middlename" required class="form-control"
                           placeholder="Отчество" id="middlenameId">
                    <div class="form-check form-switch mt-2">
                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault"
                               onchange="hideMiddlename(this)">
                        <label class="form-check-label" for="flexSwitchCheckDefault">Нет отчества</label>
                    </div>
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Роль : </label>
                <div class="col-sm-5">
                    <select class="form-select" required id="roleSelect"
                            onchange="selectedRole(this);">
                        <option selected disabled hidden value="">Роль</option>
                        <#list roles as role>
                            <option value="${role.name()!"No value"}">${role.roleName!"No value"}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-2" id="posDiv" hidden>
                <label class="col-sm-2 col-form-label">Должность : </label>
                <div class="col-sm-5">
                    <input type="text" autocomplete="off" class="form-control" placeholder="Должность"
                           id="positionName">
                </div>
            </div>
            <script>
                let roleName;

                function selectedRole(sel) {
                    roleName = sel.value;
                    posDiv = document.getElementById("posDiv");
                    positionName = document.getElementById("positionName");
                    if (sel.value == "USER_VISITED") {
                        posDiv.hidden = true
                        positionName.required = false
                        positionName.value = "Преподаватель";
                    } else {
                        posDiv.hidden = false
                        positionName.required = true
                    }
                    document.getElementById("roleName").value = roleName;
                }

                function hideMiddlename(elem) {
                    let inputMiddlename = document.getElementById("middlenameId");
                    if (elem.checked) {
                        inputMiddlename.value = null;
                        inputMiddlename.disabled = elem.checked;
                        inputMiddlename.required = !elem.checked;
                    } else {
                        inputMiddlename.disabled = elem.checked;
                        inputMiddlename.required = !elem.checked;
                    }
                }
            </script>
        </#if>
        <div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <#if isRegistration>
                <input type="hidden" name="roleName" id="roleName">
                <input type="hidden" name="positionId" id="positionId">
            </#if>
            <button class="btn btn-primary mt-2" type="submit">${buttonName}</button>
        </div>
    </form>
    <script>

        loginForm.onsubmit = async (e) => {
            e.preventDefault();
            <#if isRegistration>
            let positionRequestDto = new FormData(loginForm);
            positionRequestDto.append("positionName", document.getElementById("positionName").value);
            let response = await fetch('/admin/add/create-position', {
                method: 'POST',
                body: positionRequestDto,
            }).then((res) => {
                return res.json();
            });
            document.getElementById("positionId").value = response.id;
            </#if>

            let userRequestDTO = new FormData(loginForm);
            await fetch('${path}', {
                method: 'POST',
                body: userRequestDTO
            }).then((res) => {
                if (res.ok) {
                    if (res.url.includes('failed=true')){
                        const alertPlaceholder = document.getElementById('alertPlaceholder')

                        alertPlaceholder.innerHTML = [
                            '<div class="alert alert-danger alert-dismissible" role="alert">',
                            '   <div>Неверный логин или пароль, попробуйте заново.</div>',
                            '</div>'
                        ].join('')
                    }else{
                        <#if isRegistration>
                        window.location.href = '/admin/registration';
                        <#else>
                        window.location.href = '/home';
                        </#if>
                    }
                }
            });
        }
    </script>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-sm btn-primary" type="submit">Выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>