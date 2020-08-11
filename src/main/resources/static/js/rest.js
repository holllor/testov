/* функции для работы страницы */
'use strict';
//первоначальная загрузка списка товаров на страницу
//let data = JSON.parse(json);
let table1 = document.getElementById('test_test1_body');
//console.log(table1.);
//let tr = table1.insertRow(-1);
let xhr = new XMLHttpRequest();
xhr.open('GET', '/tovars');
//xhr.responseType = 'json';
xhr.send();

xhr.onload = function () {
    if (xhr.status !== 200) {
        console.log('status:' + xhr.status);
    } else {
        let data = JSON.parse(xhr.response);
        console.log(data.length);
        for (let i = 0; i < data.length; i++) {
            let row = table1.insertRow(i);
            row.setAttribute("class", "clickable");
             row.setAttribute("id-data", data[i].id);
             row.setAttribute("id", "table1_row_"+data[i].id);
              row.setAttribute("onclick", "selectRow("+data[i].id+");");
             
// т.к. количество полей в таблице задано разметкой, оно должно совпадать с количеством элементов JSON
            let objectRows = data[i];// обрабатываем каждый объект
            // Работаем с объектом
            let counter = 0;//костыль
            for (let value in objectRows) {
                //  console.log(value, objectRows[value]);
                let cell = row.insertCell(counter);
                cell.innerHTML = objectRows[value];
                counter++;
            }
        }

    }
};
/**
 * обновление списка товаров по нажатию на кнопку
 * @returns {undefined}
 */
function loadDataTable(){
    
//let data = JSON.parse(json);
let table1 = document.getElementById('test_test1_body');
//console.log(table1.);
//let tr = table1.insertRow(-1);

var tableHeaderRowCount = 0;
var rowCount = table1.rows.length;
for (var i = tableHeaderRowCount; i < rowCount; i++) {
    table1.deleteRow(tableHeaderRowCount);
}



let xhr = new XMLHttpRequest();
xhr.open('GET', '/tovars');
//xhr.responseType = 'json';
xhr.send();

xhr.onload = function () {
    if (xhr.status !== 200) {
        console.log('status:' + xhr.status);
    } else {
        let data = JSON.parse(xhr.response);
        console.log(data.length);
        for (let i = 0; i < data.length; i++) {
            let row = table1.insertRow(i);
            row.setAttribute("class", "clickable");
             row.setAttribute("id-data", data[i].id);
             row.setAttribute("id", "table1_row_"+data[i].id);
              row.setAttribute("onclick", "selectRow("+data[i].id+");");
             
// т.к. количество полей в таблице задано разметкой, оно должно совпадать с количеством элементов JSON
            let objectRows = data[i];// обрабатываем каждый объект
            // Работаем с объектом
            let counter = 0;//костыль
            for (let value in objectRows) {
                //  console.log(value, objectRows[value]);
                let cell = row.insertCell(counter);
                cell.innerHTML = objectRows[value];
                counter++;
            }
        }

    }
};

}

function hideTable1() {
    let table1 = document.getElementById('test_test1');

    table1.hidden = true;

}
function hideForm1() {
    let formDiv = document.getElementById('formTovar1');
    formDiv.hidden = true;
}
function viewTable1() {
    let table1 = document.getElementById('test_test1');
    table1.hidden = false;
    hideForm1();
    loadDataTable();
}
console.log(table1.toString());
//alert('testoviii'); 

function createTovar1() {
    let formDiv = document.getElementById('formTovar1');
    formDiv.style.visibility = "visible";
    formDiv.hidden = false;
    //console.log(formDiv.style.visibility );
    hideTable1();
}
/**
 * посылает данные на сервер для сохранения или обновления
 * зависит от наличия ИД
 * @param {type} id
 * @returns {undefined}
 */
function saveTovar1(id) {
    if (Number.parseInt(id)) {//если ИД есть то обновить
        alert('est id');
    } else {//если ИД нет то сохранить новыую запись
//        let idElem = document.getElementById('id1').value;
        let nameTovarElem = document.getElementById('nameTovar1').value;
        let descrElem = document.getElementById('descr1').value;
        let dateElem = document.getElementById('datepik1').value;
        let placeElem = document.getElementById('place1').value;
        let reservElem = document.getElementById('reserv1').checked;
       
      
        let dataSave = {
            name: nameTovarElem,
            description: descrElem,
            create_date: dateElem,
            place_storage: placeElem,
            reserved: reservElem
        };

        alert(JSON.stringify(dataSave));

        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/tovars');
        //  xhr.responseType = 'json';
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.send(JSON.stringify(dataSave));

        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('status:' + xhr.status);
            } else {
//                let data = JSON.parse(xhr.response);
                console.log(xhr.response.toString());


            }
        };
    }

}
/**
 * 
 * @param {type} idRow
 * @returns {undefined}
 */
function selectRow(idRow){
 let row= document.getElementById('table1_row_'+idRow).classList;
    //console.log(row);
//    row.add("active");
//let cleanRow = document.getElementsByTagName("tr");
//    console.log(cleanRow);

if (row.contains("clickable")) {
    row.remove("clickable");
 } else {
    row.add("clickable");
 }
 if (row.contains("active")) {
    row.remove("active");
      
 } else {
  
    row.add("active");
 }
    console.log(idRow);
}