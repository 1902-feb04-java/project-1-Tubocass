document.getElementById('populate').addEventListener('click', function(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=>{
        if (xhr.readyState == 4) 
        {
        	var data = JSON.parse(xhr.response);
//        	console.log(data);
        	let table = document.getElementById("response-table");
        	for(let item of data)
        	{
        		//id, date, employeId, amount, description, status
        		let html = `
				<tr>
					<td>${item.id}</td>
					<td>${item.date}</td>
					<td style="text-align: center">${item.employeeId}</td>
					<td>${item.amount}</td>
					<td>${item.description}</td>
					<td>${item.status}</td>
				</tr>`;
        		table.insertAdjacentHTML('beforeend', html)
        	}
        }
    };
    xhr.open('POST','../past_requests', true);
    xhr.send();
})
console.log("I wxist")