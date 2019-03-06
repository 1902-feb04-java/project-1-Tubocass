let table = document.getElementById("employee-table");

document.getElementById('populate_page').addEventListener('click', () => pullDown('all'));

function pullDown(who)
{
//	clearTable();
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=> 
    {
        if (xhr.readyState == 4) 
        {
        	var data = JSON.parse(xhr.response);
        	console.log(data);
        	
        	for(let item of data)
        	{
        		
        		let html = `
				<tr>
					<td style="text-align: center">${item.id}</td>
					<td>${item.firstName} ${item.lastName}</td>
					<td>${item.jobTitle}</td>
					<td>${item.reportsTo}</td>
					<td>${item.isManager}</td>
				</tr>`;
   
        		console.log( `${item.id}`);
        		table.insertAdjacentHTML('beforeend', html)
        	}
        }
    };
    xhr.open('POST','../pull_employees', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("who="+who);
}