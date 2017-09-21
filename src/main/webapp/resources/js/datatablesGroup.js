var ajaxUrl = 'ajax/types/';
var datatableApiCostType;
var datatableApiIncomeType;

function updateTableCost() {
    $.get(ajaxUrl+'cost', updateTableByDataCost);
}

function updateTableIncome() {
    $.get(ajaxUrl+'income', updateTableByDataIncome);
}

$(function () {
    datatableApiCostType = $('#datatableCostType').DataTable({
        "ajax": {
            "url": ajaxUrl+'cost',
            "type": "GET",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "type",
                /*"render": function (data, type, row) {
                    if (type == 'display'){
                        return '<span>'+data+ '</span>';
                    }
                }*/
            },
            {
                "data": "description",
                "defaultContent": ""
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtnCost
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtnCost
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


$(function () {
    datatableApiIncomeType = $('#datatableIncomeType').DataTable({
        "ajax": {
            "url": ajaxUrl+'income',
            "type": "GET",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "type",
                /*"render": function (data, type, row) {
                    if (type == 'display'){
                        return '<span>'+data+ '</span>';
                    }
                }*/
            },
            {
                "data": "description",
                "defaultContent": ""
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtnIncome
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtnIncome
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
});