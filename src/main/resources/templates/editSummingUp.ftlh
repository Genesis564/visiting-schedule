<#import "parts/common.ftlh" as c>
<#import "parts/createObject.ftlh" as cr>
<#include "parts/security.ftlh">

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
                <textarea class="form-control" id="virtuesOfOccupation" style="height: 15vh">${conclusion.virtuesOfOccupation}</textarea>
                <label for="virtuesOfOccupation">Достоинства занятия</label>
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="form-floating">
                <textarea class="form-control" id="problems" style="height: 15vh">${conclusion.problems}</textarea>
                <label for="problems">Проблемы, замечания</label>
            </div>
        </div>
        <div class="text-center mb-3">
            <label class="mb-3">План устранения замечаний:</label>
            <button type="button" class="btn btn-outline-secondary btn-xs" onclick="addEvent()">Добавить
                мероприятие
            </button>
        </div>
        <form id="submitForm">
        <div id="events">
            <#list events as event>
            <div class="input-group-sm mb-3">
                        <span class="input-group-text d-flex justify-content-between">
                                Мероприятие
                            <button type="button" class="btn btn-outline-secondary btn-xs"
                                    onclick="deleteEvent(this,${event.id})">Удалить</button>
                        </span>
                <input type="hidden" id="event-id" value="${event.id}">
                <textarea class="form-control" id="eventName" style="height: 15vh">${event.eventName}</textarea>
            </div>
            </#list>
        </div>
        <div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-outline-secondary btn-xs">
                        Подвести итоги
                    </button>
                    <input type="hidden" name="_csrf"
                           value="${_csrf.token}">
                </div>
        </div>
        </form>
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

        async function deleteEvent(elem,id) {
            let deleteEventDiv = elem.parentElement.parentElement
            deleteEventDiv.remove();

            let requestDTO = new FormData();
            requestDTO.append("_csrf","${_csrf.token}")

            await fetch('/schedule/summing-up/delete/' + id, {
                method: 'DELETE',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/schedule/edit/summing-up/' + ${visiting.schedule.id};
                }
            })
        }

        submitForm.onsubmit = async (e) => {
            e.preventDefault();
            let conclusionRequestDTO = new FormData(submitForm);
            let eventNamesList = document.querySelectorAll('#eventName');

            let eventArray = [];
            eventNamesList.forEach((elem) => {
                if (elem.parentElement.childNodes.length == 7){
                    let event = {
                        eventId: elem.parentElement.childNodes[3].value,
                        eventName: ((elem.value == '') ? "" : elem.value)
                    }
                    eventArray.push(event)
                }else{
                    let event = {
                        eventId: null,
                        eventName: ((elem.value == '') ? "" : elem.value)
                    }
                    eventArray.push(event)
                }
            })
            let eventRequestDTO = {
                events : eventArray
            }
            let response = await fetch('/schedule/summing-up/edit/event/'+${visiting.conclusion.id}+"?_csrf=${_csrf.token}",{
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(eventRequestDTO)
            })

            if (response.ok){
                conclusionRequestDTO.append("virtuesOfOccupation",document.getElementById("virtuesOfOccupation").value);
                conclusionRequestDTO.append("problems",document.getElementById("problems").value);
                await fetch('/schedule/summing-up/edit/' + ${visiting.conclusion.id}, {
                    method: 'POST',
                    body: conclusionRequestDTO
                }).then((res) => {
                    if (res.ok) {
                        window.location.href = '/home';
                    }
                });
            }
        }
    </script>
</@c.page>
