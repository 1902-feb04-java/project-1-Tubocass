document.getElementById('populate').addEventListener('click', function(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=>{
        if (xhr.readyState == 4) 
        {
            var data = JSON.parse(xhr.response);
            console.log(data);
            // for(let i =0; i<data.length;i++);
            document.getElementById("response-block").innerHTML = (xhr.responseText);
        }
    };
    xhr.open('GET','create_requests', true);
    xhr.send();
})