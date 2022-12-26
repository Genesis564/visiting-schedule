<#import "login.ftlh" as l>
<#include "security.ftlh">

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Расписание посещений</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/home">Домашняя страница</a>
                </li>

                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/user-list">Список пользователей</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Создать
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/admin/registration">Добавить пользователя</a></li>
                            <li><a class="dropdown-item" href="/admin/schedule/add/">Добавить посещение</a></li>
                        </ul>
                    </li>
                </#if>
            </ul>
            <div class="navbar-text me-2">
                ${name}
            </div>
            <#if know>
                <@l.logout />
            </#if>
        </div>
    </div>
</nav>