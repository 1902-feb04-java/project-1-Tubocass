document.getElementById('populate').addEventListener('click', function(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=>{
        if (xhr.readyState == 4) 
        {
            var data = JSON.parse(xhr.response);
            console.log(data);
            
            document.getElementById("response-block").innerHTML = (xhr.responseText);
        }
    };
    xhr.open('POST','past_requests', true);
    xhr.send();
})