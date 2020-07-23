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

function hideTable1(){
    let table1 = document.getElementById('test_test1');
    table1.hidden = true;
}
function viewTable1(){
    let table1 = document.getElementById('test_test1');
    table1.hidden = false;
}
console.log(table1.toString());
//alert('testoviii'); 