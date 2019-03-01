var submit = document.getElementById("submit");

    submit.addEventListener('click', (e) => 
    {
        e.preventDefault();
        ajax('hey');
    })

    function ajax(url) 
    {
        let user = document.getElementById("user").value;
        let password = document.getElementById("password").value;
        let formdata = new FormData();
        formdata.append("user", user);
        formdata.append("password", password);
        console.log(formdata.getAll());

        fetch(url,{
            method: "Post",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({ 'user': user, 'password': password })
        })
        .then((response) => response.text)
        .then(text =>{
            document.getElementById("paste").innerHTML = text;
            console.log(text);
        })
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
        // xhr.send(formdata);
      
    }
