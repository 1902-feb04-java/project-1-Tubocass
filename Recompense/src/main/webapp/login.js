var submit = document.getElementById("submit");
if(submit)
{
    submit.addEventListener('click', (e) =>{
    e.preventDefault();
    let user = document.getElementById("user").value;
    let password = document.getElementById("password").value;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4)
        {
            console.log(user, password);
            document.getElementById("paste").innerHTML = (xhr.responseText);
        }
    }
    xhr.open('POST','hey',true);
    var package = JSON.stringify({'user': user, 'password': password})
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(package);
    // console.log(user, password);
    })                                  
}else console.log("nope")