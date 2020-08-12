/* функции для работы страницы */
'use strict';
//первоначальная загрузка списка товаров на страницу

let table1 = document.getElementById('test_test1_body');
let id4read;
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
            row.setAttribute("id", "table1_row_" + data[i].id);
            row.setAttribute("onclick", "selectRow(" + data[i].id + ");");
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
function loadDataTable() {
    id4read = 0;
    let table1 = document.getElementById('test_test1_body');

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
            // console.log(data.length);
            for (let i = 0; i < data.length; i++) {
                let row = table1.insertRow(i);
                //атрибуты при создании строки
                row.setAttribute("class", "clickable");
                row.setAttribute("id-data", data[i].id);
                row.setAttribute("id", "table1_row_" + data[i].id);
                row.setAttribute("onclick", "selectRow(" + data[i].id + ");");

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


function createTovar1() {
    let formDiv = document.getElementById('formTovar1');
    document.getElementById('id1').value = null;
    document.getElementById('nameTovar1').value = null;
    document.getElementById('descr1').value = null;
    document.getElementById('datepik1').value = null;
    document.getElementById('place1').value = null;
    document.getElementById('reserv1').checked = null;

    formDiv.style.visibility = "visible";
    formDiv.hidden = false;

    hideTable1();
}
/**
 * посылает данные на сервер для сохранения или обновления
 * зависит от наличия ИД
 * @returns {undefined}
 */
function saveTovar1() {
    let id1 = document.getElementById('id1').value;
//    console.log(id);
    if (typeof id1 !== "undefined"
            && Number.parseInt(id1)
            && id1 > 0) {//если ИД есть то обновить
        console.log('update id ' + id1);
        //здесь другой метод на обновление!!!!
        let nameTovarElem = document.getElementById('nameTovar1').value;
        let descrElem = document.getElementById('descr1').value;
        let dateElem = document.getElementById('datepik1').value;
        let placeElem = document.getElementById('place1').value;
        let reservElem = document.getElementById('reserv1').checked;

        let dataUpdate = {
            id: id1,
            name: nameTovarElem,
            description: descrElem,
            create_date: dateElem,
            place_storage: placeElem,
            reserved: reservElem
        };
        let xhr = new XMLHttpRequest();
        xhr.open('PUT', '/tovars/' + id1);
        //  xhr.responseType = 'json';
        xhr.setRequestHeader("Content-Type", "application/json");
        
        xhr.send(JSON.stringify(dataUpdate));
        xhr.onload = function () {
            console.log(xhr.status);
            if (xhr.status === 200) {
                console.log('status:' + xhr.status);
//                alert('успешно обновлено');
                hideForm1();//скрываем форму
                viewTable1();// показываем таблицу
            } else {
                alert('ошибки обновления');
                console.log('bad status:' + xhr.response.toString());
            }
        };

    } else {//если ИД нет то сохранить новыую запись// надо вынести в отдельную функцию
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
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/tovars');
        //  xhr.responseType = 'json';
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(dataSave));
        xhr.onload = function () {
            console.log(xhr.status);
            if (xhr.status === 201) {
                console.log('status:' + xhr.status);
                alert('успешно сохранено');
//                window.location.reload(true);
                hideForm1();//скрываем форму
                viewTable1();// показываем таблицу
            } else {
//                let data = JSON.parse(xhr.response);
                alert('ошибки сохранения');
                console.log('bad status:' + xhr.response.toString());
            }
        };
    }
}
/**
 * 
 * @param {type} idRow
 * @returns {undefined}
 */
function selectRow(idRow) {
    let row = document.getElementById('table1_row_' + idRow).classList;
    id4read = idRow;// общая переменная для передачи ИД
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

/**
 * заполняет форму по указанному ИД
 * который определен при выборе строки в таблице
 * 
 * @returns {undefined}
 */
function editTovar1() {
    if (typeof id4read !== "undefined" && id4read > 0) {
        console.log('id=' + id4read);
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/tovars/' + id4read);//готовим запрос
//xhr.responseType = 'json';
        xhr.send();

        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('status:' + xhr.status);//error
            } else {
                let data = JSON.parse(xhr.response);
                console.log(data.name);
                // console.log(data.length);
                document.getElementById('id1').value = data.id;
                document.getElementById('nameTovar1').value = data.name;
                document.getElementById('descr1').value = data.description;
                document.getElementById('datepik1').value = data.create_date;
                document.getElementById('place1').value = data.place_storage;
                document.getElementById('reserv1').checked = data.reserved;
            }
        };
        //----открываем форму где уже заполнены поля
        let formDiv = document.getElementById('formTovar1');//готовим форму
        formDiv.style.visibility = "visible";
        formDiv.hidden = false;
        hideTable1();
    } else {
        console.log('не выбрано строк');
    }
}
function deleteTovar1() {
    if (typeof id4read !== "undefined" && id4read > 0) {
        console.log('id=' + id4read);

        let xhr = new XMLHttpRequest();
        xhr.open('DELETE', '/tovars/' + id4read);//готовим запрос
//xhr.responseType = 'json';
        xhr.send();
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('status:' + xhr.status);//error
            } else {
                console.log('error delete');
           }
        };
        //----обновляем таблицу
        loadDataTable();
    } else {
        console.log('не выбрано строк');
    }
}