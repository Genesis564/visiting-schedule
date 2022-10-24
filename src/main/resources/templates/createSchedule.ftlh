<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/admin/schedule/add" method="post">
        <input type="text" name="date" value="${schedule.date}">

        <div>
            <select>
                <#list statuses as status>
                    <option value="${status}">${status}</option>
                </#list>
            </select>
        </div>
        <div>
            <select>
                <#list studyGroups as studyGroup>
                    <option value="${studyGroup}">${studyGroup}</option>
                </#list>
            </select>
        </div>
        <div>
            <select>
                <#list positions as position>
                    <option value="${position}">${position}</option>
                </#list>
            </select>
        </div>
        <div>
            <select>
                <#list academicDisciplins as academicDiscipline>
                    <option value="${academicDiscipline}">${academicDiscipline}</option>
                </#list>
            </select>
        </div>

        <#list criteries as criteria>
            <div style="display: inline"></div>
        </#list>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Save</button>
    </form>
</@c.page>
