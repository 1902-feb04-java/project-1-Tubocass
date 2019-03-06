let form = document.getElementById("create_requests_form");
//form.addEventListener("submit",function(){
//    let formData = new FormData(form);
//    var xhr = new XMLHttpRequest();
//    xhr.onreadystatechange = ()=>{
//        if (xhr.readyState == 4) 
//        {
//            document.getElementById("response-block").innerHTML = (xhr.responseText);
//        }
//    };
//    xhr.open('POST','../create_requests', true);
//   // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//   // xhr.send(formData);
//})
let formData = new FormData(form);
console.log(formData.getAll)
