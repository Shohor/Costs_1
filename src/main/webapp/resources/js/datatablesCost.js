var ajaxUrl = 'ajax/cost/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
}

function updateTable1() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'bygroup',
        data: $('#bygroup').serialize(),
        success: updateTableByData
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "type": "GET",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "group.group",
                /*"render": function (data, type, row) {
                    if (type == 'display'){
                        return '<span>'+row.group.group+ '</span>';
                    }
                }*/
            },
            {
                "data": "price"
            },
            {
                "data": "date",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return '<span>' + date.substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "description",
                "defaultContent": ""
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});