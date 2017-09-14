var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

}

function selectCostOrIncome(selectedValue) {
    if (selectedValue=='cost')
    {
        $('#costTypeSelect').attr("hidden", null);
        $('#incomeTypeSelect').attr("hidden","hidden");
    }
    if (selectedValue=='income')
    {
        $('#costTypeSelect').attr("hidden", "hidden");
        $('#incomeTypeSelect').attr("hidden", null);
    }
}

function add(add_title) {
    $('#modalTitle').html(add_title);
    form.find(":input").val("");
    $('#editRow').modal();
}

function updateRow(id, incomeOrCost) {
    $('#modalTitle').html(edit_title);
    $.get(ajaxUrl + incomeOrCost?"income/":"cost/" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id, incomeOrCost) {
    $.ajax({
        url: ajaxUrl + incomeOrCost?"income/":"cost/" + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            chkbox.closest('tr').fadeTo(300, enabled ? 1 : 0.3);
            successNoty(enabled ? 'common.enabled' : 'common.disabled');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
    summ_amount(data);
}

function summ_amount(data) {
    var summ=0;
    $.each(data, function (index, value)
    {
        summ=summ+value.amount;
    });
    $('#summ').text(summ);
}

function costs() {
    $('#costFilter').attr("hidden", null);
    $('#incomeFilter').attr("hidden","hidden");
    $.ajax({
        url: ajaxUrl+'costs',
        type: "GET",
        success: updateTableByData
    })
}

function costs_and_incomes() {
    $('#costFilter').attr("hidden", null);
    $('#incomeFilter').attr("hidden",null);
    $.ajax({
        url: ajaxUrl,
        type: "GET",
        success: updateTableByData
    })
}

function incomes() {
    $('#costFilter').attr("hidden", "hidden");
    $('#incomeFilter').attr("hidden",null);
    $.ajax({
        url: ajaxUrl+'incomes',
        type: "GET",
        success: updateTableByData
    })
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl+($('#costOrIncome option:selected').text()=='Cost'?'saveCost':'saveIncome'),
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
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ','+row.incomeOrCost+ ');">'+i18n['common.update']+'</a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id +','+row.incomeOrCost+');">'+i18n['common.delete']+'</a>';
    }
}
function renderCostBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-default" href="costs">'+i18n['users.costs']+'</a>';
    }
}