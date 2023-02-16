<#macro create path,headerName,bodyName,variableName>

    <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" href="#${variableName}"
            data-bs-whatever="@getbootstrap">Добавить новое значение
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
                    window.location.href = '/admin/add/';
                }
            });;
        }
    </script>
</#macro>
