var submit = document.getElementById("submit");

    submit.addEventListener('click', (e) => 
    {
        e.preventDefault();
        ajax('hey');
    })

    function ajax(url) 
    {
        // let user = document.getElementById("user").value;
        // let password = document.getElementById("password").value;
        // let formdata = new FormData();
        // formdata.append("user", user);
        // formdata.append("password", password);


        // fetch(url,{
        //     method: "Post",
        //     headers: {"Content-Type": "application/json"},
        //     body: { 'user': user, 'password': password }
        // })
        // .then((response) => {
        //     document.getElementById("paste").innerHTML = response.text;
        //     console.log(response);
        // })
      
        // var xhr = new XMLHttpRequest();
        // xhr.onreadystatechange = function () 
        // {
        //     if (xhr.readyState == 4) 
        //     {
        //         console.log(user, password);
        //         document.getElementById("paste").innerHTML = (xhr.responseText);
        //     }
        // }
        // xhr.open('POST', url, true);
        // // var package = JSON.stringify({ 'user': user, 'password': password })
        // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        // xhr.send(formdata);
      
    }
