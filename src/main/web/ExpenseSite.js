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

fillTable('http://localhost:8080/showExpenses').then(r => constructExpenseTable(r, AllExpensesTable, AllExpensesCols));
fillTable('http://localhost:8080/getExpenseReport').then(r => {
    console.log(r["categoryTotals"] + r["monthlyTotals"] + "HELLO");
    constructExpenseTable(r["categoryTotals"], EpensesBySpendingCategory, BySpendingCols);
    constructExpenseTable(r["monthlyTotals"], ExepensesByMonth, ByMonthCols);
});



function constructExpenseTable(expenses, tableId, colNames){
    const table = document.getElementById(tableId);

    for(let i=0; i<expenses.length; ++i){
        var row = table.insertRow(-1);
        //let row = '<tr>';
        for(let j=0; j<colNames.length; ++j){
            var cell = row.insertCell();

            let val = expenses[i][colNames[j]];
            if(val == null){
                val = "";
            }
            cell.innerHTML = val;
        }
    }
}

function submitExpense() {
    let spendingCategory = document.getElementById('SpendingCategoryField').innerText;
    let amount = document.getElementById('AmountField').innerText;
    let comment = document.getElementById('CommentField').innerText;

    let date = "2021-03-18"; //TODO: implement fetching today's date

    const request = {spendingCategory: spendingCategory, amount: amount, date: date, comment: comment};
    fetch('http://localhost:8080/addExpense', {
        method: 'post',
        body: JSON.stringify(request)
    }).then(function(response) {
        return response.json();
    }).then(function(data) {
        console.log('Added Expense');
    });
}