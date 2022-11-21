<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <form action="/admin/schedule/add" method="post">
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Дата</span>
                <input type="week" class="form-control" name="visitingWeek" id="visitingDate">
            </div>
            <div class="mb-3">
                <select class="form-select" name="visitorUser" aria-label="Default select example">
                    <option selected disabled hidden>Посещающий занятие</option>
                    <#list usersVisitor as visitor>
                        <option value="${visitor.id}">${visitor.username}</option>
                    </#list>
                </select>
            </div>
            <div class="mb-3">
                <select class="form-select" name="visitedUser" aria-label="Default select example">
                    <option selected disabled hidden>Преподаватель</option>
                    <#list usersVisited as visited>
                        <option value="${visited.id}">${visited.username}</option>
                    </#list>
                </select>
            </div>
            <div>
                <button class="btn btn-primary" type="submit">Сохранить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </div>
        </form>
    </div>



</@c.page>