<#macro login path, buttonName,isRegistration>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Имя пользователя : </label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Имя пользователя">
            </div>
        </div>
        <div class="form-group row mt-2">
            <label class="col-sm-2 col-form-label">Пароль : </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Пароль">
            </div>
        </div>
        <#if isRegistration>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Должность : </label>
                <div class="col-sm-5">
                    <select class="form-select" id="positionsSelect"
                            aria-label="Default select example" onchange="selectedPosition(this);">
                        <option selected disabled hidden>Должность</option>
                        <#list positions as position>
                            <option value="${position.id!"No value"}">${position.positionName!"No value"}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Фамилия : </label>
                <div class="col-sm-5">
                    <input type="text" name="lastname" class="form-control" placeholder="Фамилия">
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Имя : </label>
                <div class="col-sm-5">
                    <input type="text" name="firstname" class="form-control" placeholder="Имя">
                </div>
            </div>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Отчество : </label>
                <div class="col-sm-5">
                    <input type="text" name="middlename" class="form-control" placeholder="Отчество" id="middlenameId">
                    <div class="form-check form-switch mt-2">
                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault"
                               onchange="hideMiddlename(this)">
                        <label class="form-check-label" for="flexSwitchCheckDefault">Нет отчества</label>
                    </div>
                </div>
            </div>
            <script>
                let positionId;

                function selectedPosition(sel) {
                    positionId = sel.value;
                    document.getElementById("positionId").value = positionId;
                }

                function hideMiddlename(elem) {
                    let inputMiddlename = document.getElementById("middlenameId");
                    if (elem.checked) {
                        inputMiddlename.value = null;
                        inputMiddlename.disabled = elem.checked;
                    } else {
                        inputMiddlename.disabled = elem.checked;
                    }
                }
            </script>
        </#if>
        <div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <#if isRegistration>
                <input type="hidden" name="positionId" id="positionId">
            </#if>
            <button class="btn btn-primary mt-2" type="submit">${buttonName}</button>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>