<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-3">
        <h3>Вложения</h3>
        <div>
            <a href="/admin/report/excel">Список посещений</a>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-filetype-xlsx" viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M14 4.5V11h-1V4.5h-2A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v9H2V2a2 2 0 0 1 2-2h5.5L14 4.5ZM7.86 14.841a1.13 1.13 0 0 0 .401.823c.13.108.29.192.479.252.19.061.411.091.665.091.338 0 .624-.053.858-.158.237-.105.416-.252.54-.44a1.17 1.17 0 0 0 .187-.656c0-.224-.045-.41-.135-.56a1.002 1.002 0 0 0-.375-.357 2.028 2.028 0 0 0-.565-.21l-.621-.144a.97.97 0 0 1-.405-.176.37.37 0 0 1-.143-.299c0-.156.061-.284.184-.384.125-.101.296-.152.513-.152.143 0 .266.023.37.068a.624.624 0 0 1 .245.181.56.56 0 0 1 .12.258h.75a1.093 1.093 0 0 0-.199-.566 1.21 1.21 0 0 0-.5-.41 1.813 1.813 0 0 0-.78-.152c-.293 0-.552.05-.777.15-.224.099-.4.24-.527.421-.127.182-.19.395-.19.639 0 .201.04.376.123.524.082.149.199.27.351.367.153.095.332.167.54.213l.618.144c.207.049.36.113.462.193a.387.387 0 0 1 .153.326.512.512 0 0 1-.085.29.558.558 0 0 1-.255.193c-.111.047-.25.07-.413.07-.117 0-.224-.013-.32-.04a.837.837 0 0 1-.249-.115.578.578 0 0 1-.255-.384h-.764Zm-3.726-2.909h.893l-1.274 2.007 1.254 1.992h-.908l-.85-1.415h-.035l-.853 1.415H1.5l1.24-2.016-1.228-1.983h.931l.832 1.438h.036l.823-1.438Zm1.923 3.325h1.697v.674H5.266v-3.999h.791v3.325Zm7.636-3.325h.893l-1.274 2.007 1.254 1.992h-.908l-.85-1.415h-.035l-.853 1.415h-.861l1.24-2.016-1.228-1.983h.931l.832 1.438h.036l.823-1.438Z"/>
            </svg>
        </div>
    </div>
    <div class="input-group mb-3">
        <select class="form-select" id="scheduleStatus" onchange="changeScheduleStatus(this)">
            <option value="" selected disabled hidden>Статус посещения</option>
            <option value="null">Все</option>
            <#list scheduleStatus as status>
                <option value="${status.name()!"No value"}">${status.getName()!"No value"}</option>
            </#list>
        </select>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Статус</th>
                <th scope="col">Посещающий</th>
                <th scope="col">Преподаватель</th>
            </tr>
            </thead>
            <tbody id="scheduleTBody">
                <#list schedules as schedule>
                    <tr>
                        <td id="date"
                                <#list schedule.status as status>
                            <#if status.getName() == "Просрочено">
                                class="schedule-overdue"
                            </#if>
                                </#list>>
                            <#if schedule.visiting??>
                                ${schedule.visiting.date}
                            <#else>
                                ${schedule.visitingWeek}
                            </#if>
                        </td>
                        <td><#list schedule.status as status>${status.getName()}</#list></td>
                        <td>${schedule.visitorUser.lastname} ${schedule.visitorUser.firstname} ${schedule.visitorUser.middlename!""}</td>
                        <td>${schedule.visitedUser.lastname} ${schedule.visitedUser.firstname} ${schedule.visitedUser.middlename!""}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            let dataDoc = document.querySelectorAll("#date");
            dataDoc.forEach((elem) => {
                let curr;
                let str = elem.innerHTML;
                if (str.split('-W').length === 2) {
                    let splitDate = str.split('-W');
                    let week = splitDate[1]
                    if (week[0] == '0') {
                        week = week.split("0")
                        curr = new Date(getDateOfWeek(splitDate[0], week[1]));
                    } else {
                        curr = new Date(getDateOfWeek(splitDate[0], week));
                    }

                    let inputData = new Date(curr);
                    let month = inputData.getMonth() + 1
                    let lastDayOfWeek = new Date(inputData);
                    lastDayOfWeek.setDate(inputData.getDate() + 6);
                    let minDate = ((inputData.getDate() < 10) ? "0" : "") + inputData.getDate() + ((month < 10) ? ".0" : ".") + month + "." + inputData.getFullYear();
                    let maxDate = ((lastDayOfWeek.getDate() < 10) ? "0" : "") + lastDayOfWeek.getDate() + ((lastDayOfWeek.getMonth() < 10) ? ".0" : ".") + (lastDayOfWeek.getMonth() + 1) + "." + lastDayOfWeek.getFullYear();
                    if (elem.classList.contains('schedule-overdue')) {
                        elem.innerHTML = "Было запланированно с " + minDate + " по " + maxDate;
                    } else {
                        elem.innerHTML = "Запланировано с " + minDate + " по " + maxDate;
                    }
                } else if (str.split('-').length === 3) {
                    let splitDate = str.split('-');
                    elem.innerHTML = "Посещение прошло: " + splitDate[2].trim() + "." + splitDate[1].trim() + "." + splitDate[0].trim();
                }
            })
        })

        function getDateOfWeek(year, week) {
            let simple = new Date(year, 0, 1 + (week - 1) * 7, 5);
            let dow = simple.getDay();
            let ISOweekStart = simple;
            if (dow <= 4)
                ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
            else
                ISOweekStart.setDate(simple.getDate() + 8 - (simple.getDay()));
            return ISOweekStart;
        }

        async function changeScheduleStatus(sel) {
            if (sel.value ==="null"){
                window.location.href = '/admin/report/';
            }else{
                window.location.href = '/admin/report/?scheduleStatus='+sel.value;
            }
        }
    </script>
</@c.page>