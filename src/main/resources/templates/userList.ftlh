<#import "parts/common.ftlh" as c>

<@c.page>
    Список пользователей
    <div>
        <table class="table table-hover table-bordered table-sm">
            <thead>
            <tr>
                <th>Имя пользователя</th>
                <th>Роли</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td>
                        <div class="container">
                            <div class="row">
                                <div class="col text-start">
                                    <#list user.roles as role>
                                        ${role.roleName!"No Role"}<#sep>,
                                    </#list>
                                </div>
                                <div class="col text-start">
                                    <form action="/admin/${user.id}" method="get">
                                        <button type="button" class="btn btn-outline-secondary ms-3" data-bs-toggle="modal"
                                                href="#user-${user.id}"
                                                data-bs-whatever="@getbootstrap">Редактировать
                                        </button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <#list users as user>
            <div class="modal fade" id="user-${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование пользователя</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="/admin" method="post">
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label">Имя пользователя : </label>
                                    <input type="text" name="username" value="${user.username}">
                                    <#list roles as role>
                                        <div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="flexCheckDefault"
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
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
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
</@c.page>