<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/admin/schedule/add" method="post">
        <div><label>Date : <input type="text" name="scheduleDate"></label></div>

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
            <form action="/admin/schedule/add/create-study-group" method="get">
                <input type="submit" value="Add new value">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
        <div>
            <select>
                <#list positions as position>
                    <option value="${position}">${position}</option>
                </#list>
            </select>
            <form action="/admin/schedule/add/create-position" method="get">
                <input type="submit" value="Add new value">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
        <div>
            <select>
                <#list academicDisciplins as academicDiscipline>
                    <option value="${academicDiscipline}">${academicDiscipline}</option>
                </#list>
            </select>
            <form action="/admin/schedule/add/create-discipline" method="get">
                <input type="submit" value="Add new value">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>

<#--        <#list criteries as criteria>-->
<#--            <div style="display: inline">-->
<#--                <label>${criteria}</label>-->
<#--            </div>-->
<#--        </#list>-->

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Save</button>
    </form>
</@c.page>
