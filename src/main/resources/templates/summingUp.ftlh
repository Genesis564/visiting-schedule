<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="card mb-3">
    <div class="card-header d-flex justify-content-center">
        <div class="col-sm-10 text-center">
            <label>Выводы</label>
        </div>
    </div>
    <div class="card-body">
        <div class="input-group mb-3">
            <div class="form-floating">
                <textarea class="form-control" id="virtuesOfOccupation" style="height: 15vh"></textarea>
                <label for="virtuesOfOccupation">Достоинства занятия</label>
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="form-floating">
                <textarea class="form-control" id="problems" style="height: 15vh"></textarea>
                <label for="problems">Проблемы, замечания</label>
            </div>
        </div>
        <div class="text-center">
            <label class="mb-3">План устранения замечаний:</label>
            <button type="button" class="btn btn-outline-secondary btn-xs" onclick="addEvent()">Добавить
                мероприятие
            </button>
        </div>
        <div id="events">
            <div class="input-group-sm mb-3">
                        <span class="input-group-text d-flex justify-content-between">
                                Мероприятие
                            <button type="button" class="btn btn-outline-secondary btn-xs"
                                    onclick="deleteEvent(this)">Удалить</button>
                        </span>
                <textarea class="form-control" id="eventName" style="height: 15vh"></textarea>
            </div>
        </div>
        <div>
            <form id="submitForm">
                <div class="mb-3">
                    <button type="submit" class="btn btn-outline-secondary btn-xs">
                        Подвести итоги
                    </button>
                    <input type="hidden" name="_csrf"
                           value="${_csrf.token}">
                </div>
            </form>
        </div>
    </div>
    <script>
        function addEvent() {
            let eventsDiv = document.getElementById('events');
            let innerHtmlEvents = eventsDiv.innerHTML;
            let textAreas = document.querySelectorAll("#eventName");
            let textAreasValue = [];
            textAreas.forEach((elem)=>{
                textAreasValue.push(elem.value);
            })
            let newEventBlock =
                '\n                <div class="input-group-sm mb-3">' +
                '\n                        <span class="input-group-text d-flex justify-content-between">' +
                '\n                                Мероприятие' +
                '\n                            <button type="button" class="btn btn-outline-secondary btn-xs" onclick="deleteEvent(this)">Удалить</button>' +
                '\n                        </span>' +
                '\n                    <textarea class="form-control" id="eventName" style="height: 15vh"></textarea>' +
                '\n                </div>' +
                '\n            '
            eventsDiv.innerHTML = innerHtmlEvents + newEventBlock;
            textAreas = document.querySelectorAll("#eventName");
            let i =0;
            textAreasValue.forEach((elem)=>{
                textAreas[i].value = elem;
                i++;
            })
        }

        function deleteEvent(elem) {
            let deleteEventDiv = elem.parentElement.parentElement
            deleteEventDiv.remove();
        }

        submitForm.onsubmit = async (e) => {
            e.preventDefault();
            let eventRequestDTO = new FormData(submitForm);
            let eventList = document.querySelectorAll('#eventName');
            let eventNames = [];
            eventList.forEach((elem) => {
                eventNames.push(elem.value);
            })

            eventRequestDTO.append("eventNames",eventNames);
            eventRequestDTO.append("virtuesOfOccupation",document.getElementById("virtuesOfOccupation").value);
            eventRequestDTO.append("problems",document.getElementById("problems").value);
            eventRequestDTO.append("scheduleId",${schedule.id});
            let response = await fetch('/schedule/summing-up/add', {
                method: 'POST',
                body: eventRequestDTO
            });
        }
    </script>
</@c.page>