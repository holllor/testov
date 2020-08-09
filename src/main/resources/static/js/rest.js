/* функции для работы страницы */
'use strict';

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
        for (var i = 0; i < data.length; i++) {
            var row = table1.insertRow(i);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i].column1;
//            console.log(data[i].column1);
//            console.log(i);
            var cell2 = row.insertCell(1);
            cell2.innerHTML = data[i].column2;

            var cell3 = row.insertCell(2);
            cell3.innerHTML = data[i].column3;

            var cell4 = row.insertCell(3);
            cell4.innerHTML = data[i].column4;

            var cell5 = row.insertCell(4);
            cell5.innerHTML = data[i].column5;

        }


        // alert(data);
//     alert(xhr.response);
//        console.log(xhr.response);
//        console.log(xhr);

//        alert(data[0].column1);
    }
};

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

function saveTovar1(id) {
    if (Number.parseInt(id)) {//если ИД есть то обновить
        alert('est id');
    } else {//если ИД нет то сохранить новыую запись
        let idElem = document.getElementById('id1').value;
        let nameTovarElem = document.getElementById('nameTovar1').value;
        let descrElem = document.getElementById('descr1').value;
        let dateElem = document.getElementById('datepik1').value;
        let placeElem = document.getElementById('place1').value;
        let reservElem = document.getElementById('reserv1').value;
        let dataSave = {
            column1: nameTovarElem,
            column2: descrElem,
            column3: dateElem,
            column4: placeElem,
            column5: reservElem
        };

        alert(JSON.stringify(dataSave));
       
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/tovars');
      //  xhr.responseType = 'json';
       xhr.setRequestHeader("Content-Type","application/json");
        
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
//    let table1 = document.getElementById('test_test1_body');
//
//let xhr = new XMLHttpRequest();
//xhr.open('GET', '/tovars');
//xhr.responseType = 'json';
//xhr.send();
//
//xhr.onload = function (id) {
//    if (xhr.status !== 200) {
//        console.log('status:' + xhr.status);
//    } else {
//        let data = JSON.parse(xhr.response);
//        console.log(data.length);
//        for (var i = 0; i < data.length; i++) {
//            var row = table1.insertRow(i);
//            var cell1 = row.insertCell(0);
//            cell1.innerHTML = data[i].column1;
////            console.log(data[i].column1);
////            console.log(i);
//             var cell2 = row.insertCell(1);
//            cell2.innerHTML = data[i].column2;
//
//            var cell3 = row.insertCell(2);
//            cell3.innerHTML = data[i].column3;
//
//            var cell4 = row.insertCell(3);
//            cell4.innerHTML = data[i].column4;
//
//            var cell5 = row.insertCell(4);
//            cell5.innerHTML = data[i].column5;
//
//        }
//
//   }
//};
}