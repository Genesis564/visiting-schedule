<#macro create path,headerName,bodyName,variableName>

    <button type="button" class="btn d-flex align-self-center btn-sm" data-bs-toggle="modal" href="#${variableName}"
            data-bs-whatever="@getbootstrap">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi flex-fill bi-plus-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
        </svg>
    </button>
    <div class="modal fade" id="${variableName}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Создать ${headerName}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="${variableName}Form" action="${path}" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="recipient-name" class="col-form-label">${bodyName} : </label>
                            <input type="text" class="form-control" id="${variableName}" name="${variableName}">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <div>
                            <button class="btn btn-primary" type="submit" data-bs-dismiss="modal">Сохранить</button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        ${variableName}Form.onsubmit = async (e) => {
            e.preventDefault();
            let requestDTO = new FormData(${variableName}Form);

            await fetch('${path}', {
                method: 'POST',
                body: requestDTO,
            }).then((res) => {
                if (res.ok) {
                    window.location.href = '/admin/';
                }
            });;
        }
    </script>
</#macro>
