var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function add() {
    $('#modal-title').html(add_title);
    form.find(":input").val("");
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}

function updateRow(id) {
    $('#modal-title').html(edit_title);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            /*form.find("input[name='" + key + "']").val(value);*/
            if ((typeof value) == 'object')
            {
                $('#'+key+'option:selected').text(value.type.toString());
            }
            else {
                $('#' + key).val(value);
            }
        });
        $('#editRow').modal();
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}



function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: i18n[key],
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: i18n['common.failed'] + ': ' + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        type: 'error',
        layout: 'bottomRight'
    });
}

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRow('+row.id+');">'+i18n['common.update']+'</a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow('+row.id+');">'+i18n['common.delete']+'</a>';
    }
}