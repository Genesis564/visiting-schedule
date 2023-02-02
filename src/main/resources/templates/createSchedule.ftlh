<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Дата</span>
            <input type="date" class="form-control" name="visitingWeek" id="visitingDate" onchange="getWeekOfDate()">
            <span class="input-group-text" id="weekInfo"></span>
        </div>
        <form action="/admin/schedule/add" method="post">
            <div class="mb-3">
                <select class="form-select" name="visitorUser" id="visitorUser" aria-label="Default select example" onchange="updateListUsers(this)">
                    <option selected disabled hidden>Посещающий занятие</option>
                    <#list usersVisitor as visitor>
                        <option value="${visitor.id}">${visitor.username}</option>
                    </#list>
                </select>
            </div>
            <div class="mb-3">
                <select class="form-select" name="visitedUser" id="visitedUser" aria-label="Default select example" onchange="updateListUsers(this)">
                    <option selected disabled hidden>Преподаватель</option>
                    <#list usersVisited as visited>
                        <option value="${visited.id}">${visited.username}</option>
                    </#list>
                </select>
            </div>
            <div>
                <button class="btn btn-primary" type="submit">Сохранить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" name="visitingWeek" id="weekValue" value="">
            </div>
        </form>
    </div>
    <script>

        let curr;
        let previousId = 0;

        document.addEventListener("DOMContentLoaded", () => {
            setCurrentWeek();
            let str = document.getElementById('weekInfo').innerHTML;
            let splitDate = str.split("-W");
            let week = splitDate[1].split("0");
            if (week.length === 2){
                curr = new Date(getDateOfWeek(splitDate[0], week[1]));
            }else{
                curr = new Date(getDateOfWeek(splitDate[0], week[0]));
            }

            let inputData = document.getElementById('visitingDate');
            inputData.valueAsDate = new Date();
            let month = curr.getMonth() + 1;
            inputData.min = curr.getFullYear() + ((month<10) ? "-0" : "-") + month + ((curr.getDate()<10) ? "-0" : "-") + curr.getDate();
        })

        function setCurrentWeek(){
            let currDate = new Date();
            document.getElementById("weekInfo").innerHTML =currDate.getWeekYear()+((currDate.getWeek() < 10) ? "-W0" : "-W")+currDate.getWeek();
            document.getElementById('weekValue').value =currDate.getWeekYear()+((currDate.getWeek() < 10) ? "-W0" : "-W")+currDate.getWeek();
        }

        function getWeekOfDate() {
            let currDate = new Date(document.getElementById("visitingDate").valueAsDate)
            document.getElementById("weekInfo").innerHTML = currDate.getWeekYear()+((currDate.getWeek() < 10) ? "-W0" : "-W")+currDate.getWeek();
            document.getElementById('weekValue').value =currDate.getWeekYear()+((currDate.getWeek() < 10) ? "-W0" : "-W")+currDate.getWeek();
        }

        function updateListUsers(elem){
            let currId = elem.selectedOptions[0].value;
            let previousOptions = document.querySelectorAll('option[value="'+previousId+'"]');
            previousOptions.forEach((item)=>{
                item.style = ""
            })
            let newOptions = document.querySelectorAll('option[value="'+currId+'"]');
            newOptions.forEach((item)=>{
                item.style = "display:none"
            })
            previousId = elem.selectedOptions[0].value;
        }
        // Returns the ISO week of the date.
        Date.prototype.getWeek = function() {
            var date = new Date(this.getTime());
            date.setHours(0, 0, 0, 0);
            // Thursday in current week decides the year.
            date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
            // January 4 is always in week 1.
            var week1 = new Date(date.getFullYear(), 0, 4);
            // Adjust to Thursday in week 1 and count number of weeks from date to week1.
            return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000
                - 3 + (week1.getDay() + 6) % 7) / 7);
        }

        // Returns the four-digit year corresponding to the ISO week of the date.
        Date.prototype.getWeekYear = function() {
            var date = new Date(this.getTime());
            date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
            return date.getFullYear();
        }

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

    </script>
</@c.page>