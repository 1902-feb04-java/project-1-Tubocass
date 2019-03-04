let table = document.getElementById("response-table");

document.getElementById('populate_pending').addEventListener('click', () => pullDown('pending'));
document.getElementById('populate_accepted').addEventListener('click', () => pullDown('accepted'));
document.getElementById('populate_rejected').addEventListener('click', () => pullDown('rejected'));


function pullDown(status)
{
	clearTable();
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=> 
    {
        if (xhr.readyState == 4) 
        {
        	var data = JSON.parse(xhr.response);
        	console.log(data);
        	
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
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("status="+status);
}

function clearTable()
{
	console.log(table.children.length);
	let children = table.children;
	for(let node = children.length-1; node>=0; node--)
	{
		console.log(children[node]);
		children[node].parentNode.removeChild(children[node]);
	}
}