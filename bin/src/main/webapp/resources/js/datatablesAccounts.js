var ajaxUrl = 'ajax/accounts/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl,
        "dataSrc": "",
        success: updateTableByData
    });
}


$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "type": "GET",
            "dataSrc": "",
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "type",
                /*"render": function (data, type, row) {
                    if (type == 'display'){
                        return '<span>'+row.type.type+ '</span>';
                    }
                }*/
            },
            {
                "data": "description",
                "defaultContent": ""
            },
            {
                "data": "amount"
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
