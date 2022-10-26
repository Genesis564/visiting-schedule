<#import "parts/common.ftlh" as c>

<@c.page>

    <div><label>Date : <input type="text" name="scheduleDate"></label></div>

    <div>
        <select>
            <option value="" selected disabled hidden>Status</option>
            <#list statuses as status>
                <option value="${status}">${status}</option>
            </#list>
        </select>

    </div>

    <div>
        <select>
            <option value="" selected disabled hidden>Study group</option>
            <#list studyGroups as studyGroup>
                <option value="${studyGroup.groupName!"No value"}">${studyGroup.groupName!"No value"}</option>
            </#list>
        </select>
        <form action="/admin/schedule/add/create-study-group" method="get">
            <input type="submit" value="Add new value">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <div>
        <select>
            <option value="" selected disabled hidden>Position</option>
            <#list positions as position>
                <option value="${position.positionName!"No value"}">${position.positionName!"No value"}</option>
            </#list>
        </select>
        <form action="/admin/schedule/add/create-position" method="get">
            <input type="submit" value="Add new value">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <div>
        <select>
            <option value="" selected disabled hidden>Discipline</option>
            <#list academicDisciplins as academicDiscipline>
                <option value="${academicDiscipline.disciplineName!"No value"}">${academicDiscipline.disciplineName!"No value"}</option>
            </#list>
        </select>
        <form action="/admin/schedule/add/create-discipline" method="get">
            <input type="submit" value="Add new value">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <div>
        <#list criteries as criteria>
            <div style="display: block">
                <label>${criteria}</label>
                <input type="radio" name="score" value="0" checked>
                <input type="radio" name="score" value="1" checked>
                <input type="radio" name="score" value="2" checked>
                <input type="radio" name="score" value="3" checked>
            </div>
        </#list>
    </div>

    <form action="/admin/schedule/add" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Save</button>
    </form>
</@c.page>
