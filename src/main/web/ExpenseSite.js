const fillTable = async (url) => {
    const response = await fetch(url);
   return await response.json(); //extract JSON from the http response
};

const AllExpensesTable = "AllExpensesTable";
const AllExpensesCols = ["amount", "spendingCategory","date","comment"];

const EpensesBySpendingCategory = "EpensesBySpendingCategory";
const BySpendingCols = ["categoryName","amount"];

const ExepensesByMonth = "ExepensesByMonth";
const ByMonthCols = ["date","amount"];

function clearTables() {
    document.getElementById(AllExpensesTable).innerHTML =
        '<div class="row" id="AllExpensesDiv" style="display: none;">\n' +
        '        <label for="AllExpensesTable" style="display: block;">List of All Expenses</label>\n' +
        '        <table id="AllExpensesTable" >\n' +
        '            <tr>\n' +
        '                <th scope="col">Cost ($)</th>\n' +
        '                <th scope="col">Spending Category</th>\n' +
        '                <th scope="col">Date</th>\n' +
        '                <th scope="col">Comment</th>\n' +
        '                <th scope="col">Delete Entry</th>\n' +
        '            </tr>\n' +
        '        </table>\n' +
        '    </div>';
    document.getElementById('expenseReport').innerHTML =
        '        <div class="row expenseReportRow">\n' +
        '            <label for="EpensesBySpendingCategory">Total Expenditure by Category</label>\n' +
        '            <label for="ExepensesByMonth">Total Monthly Expenditure</label>\n' +
        '        </div>\n' +
        '        <div class="row expenseReportRow">\n' +
        '            <table id="EpensesBySpendingCategory">\n' +
        '                <tr>\n' +
        '                    <th scope="col">Spending Category</th>\n' +
        '                    <th scope="col">Cost</th>\n' +
        '                </tr>\n' +
        '            </table>\n' +
        '\n' +
        '            <table id="ExepensesByMonth">\n' +
        '                <tr>\n' +
        '                    <th scope="col">Date</th>\n' +
        '                    <th scope="col">Cost</th>\n' +
        '                </tr>\n' +
        '            </table>\n';
}

function loadTables() {
    fillTable('http://localhost:8080/showExpenses').then(r => constructExpenseTable(r, AllExpensesTable, AllExpensesCols, true));
    fillTable('http://localhost:8080/getExpenseReport').then(r => {
        constructExpenseTable(r["categoryTotals"], EpensesBySpendingCategory, BySpendingCols, false);
        constructExpenseTable(r["monthlyTotals"], ExepensesByMonth, ByMonthCols, false);
    });
}

loadTables();

function constructExpenseTable(expenses, tableId, colNames, deleteButtons){
    const table = document.getElementById(tableId);

    for(let i=0; i<expenses.length; ++i){
        var row = table.insertRow(-1);
        row.id = 'row' + row.rowIndex;
        //let row = '<tr>';
        for(let j=0; j<colNames.length; ++j){
            var cell = row.insertCell();

            let val = expenses[i][colNames[j]];
            if(val == null){
                val = "";
            }
            if(colNames[j] === 'amount')
            {
                val = val.toFixed(2);
            }
            cell.innerHTML = val;
        }
        if(deleteButtons) {
            //add the delete button
            var deleteCell = row.insertCell();
            deleteCell.insertAdjacentHTML("afterbegin", '<button class=\'deleteBtn\' id=\'deleteBtn' + row.rowIndex +
                '\' onclick=\"removeExpense(' + row.rowIndex + ')\">X</button>')
        }
    }
}

function submitExpense() {
    let spendingCategory = document.getElementById('SpendingCategoryField').value;
    let amount = parseFloat(document.getElementById('AmountField').value);
    let comment = document.getElementById('CommentField').value;

    let today = new Date();
    let date = String(today.getFullYear()) + '-' + String(today.getMonth()+1) + '-' + String(today.getDate());
    console.log('today\'s date is: ' + date);

    const request = {spendingCategory: spendingCategory, amount: amount, comment: comment, date: date};
    fetch('http://localhost:8080/addExpense', {
        method: 'put',
        body: JSON.stringify(request),
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(function(response) {
        return response;
    }).then(function(data) {
        console.log('Added Expense');
        clearTables();
        loadTables();
    });
}

function removeExpense(rowNum) {
    let row = document.getElementById('row' + rowNum).innerHTML;
    console.log(row.replace(/<td>/g, ""));
    let array = row.replace(/<td>/g, "").split('</td>');
    console.log(array);
    const request = {spendingCategory: array[1], amount: array[0], comment: array[3], date: array[2]};
    fetch('http://localhost:8080/removeExpense', {
        method: 'delete',
        body: JSON.stringify(request),
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(function(response) {
        return response;
    }).then(function(data) {
        console.log('Removed Expense');
        clearTables();
        loadTables();
    });


}