var ajaxUrl = 'ajax/cost/';
var datatableApi;

function updateTableFilter() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
}

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
        "paging": true,
        "info": true,
        "columns": [
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
                "data": "type",
                /*"render": function (data, type, row) {
                    if (type == 'display'){
                        return '<span>'+row.group.group+ '</span>';
                    }
                }*/
            },
            {
                "data": "amount"
            },
            {
                "data": "cashAccountsAndCards"
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
        "createdRow": function (row, data, dataIndex) {
            if (data.incomeOrCost != undefined) {
                $(row).addClass(data.incomeOrCost ? 'income' : 'cost');
            }

        },
        "footerCallback": function( tfoot, data, start, end, display ) {
            summ_amount(data);
        },
        "initComplete": makeEditable
    });
});