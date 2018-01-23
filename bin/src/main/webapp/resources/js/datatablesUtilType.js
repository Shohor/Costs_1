var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function add(value) {
    $('#modal-title').html(add_title);
    form.find(":input").val("");
    $('#incomeOrCost').val(value);
    $('#editRow').modal();
}

function updateRowCost(id) {
    $('#modal-title').html(edit_title);
    $.get(ajaxUrl + 'cost/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#incomeOrCost').val('cost')
        $('#editRow').modal();
    });
}

function deleteRowCost(id, incomeOrCost) {
    $.ajax({
        url: ajaxUrl + 'cost/'+ id,
        type: 'DELETE',
        success: function () {
            updateTableCost();
            successNoty('common.deleted');
        }
    });
}

function updateRowIncome(id) {
    $('#modal-title').html(edit_title);
    $.get(ajaxUrl + 'income/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#incomeOrCost').val('income')
        $('#editRow').modal();
    });
}

function deleteRowIncome(id) {
    $.ajax({
        url: ajaxUrl + 'income/'+ id,
        type: 'DELETE',
        success: function () {
            updateTableIncome();
            successNoty('common.deleted');
        }
    });
}

function updateTableByDataCost(data) {
    datatableApiCostType.clear().rows.add(data).draw();
}

function updateTableByDataIncome(data) {
    datatableApiIncomeType.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl+'/'+$('#incomeOrCost').val(),
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            if ($('#incomeOrCost').val()=='cost')
            {
                updateTableCost();
            }
            else
            {
                updateTableIncome();
            }
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

function renderEditBtnCost(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRowCost('+row.id+');">'+i18n['common.update']+'</a>';
    }
}

function renderDeleteBtnCost(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRowCost('+row.id+');">'+i18n['common.delete']+'</a>';
    }
}

function renderEditBtnIncome(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRowIncome('+row.id+');">'+i18n['common.update']+'</a>';
    }
}

function renderDeleteBtnIncome(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRowIncome('+row.id+');">'+i18n['common.delete']+'</a>';
    }
}