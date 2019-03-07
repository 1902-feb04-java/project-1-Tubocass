let table = document.getElementById("employee-table");

document.getElementById('populate_page').addEventListener('click', () => pullDown(1));
document.getElementById('update').addEventListener('click', updateTable);
var data;
function pullDown(who)
{
	clearTable();
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = ()=> 
    {
        if (xhr.readyState == 4) 
        {
        	data = JSON.parse(xhr.response);
        	console.log(data);
        	let person;
        	if(who =='all')
    		{
	        	for(let item of data)
	        	{
	        		person = html(item);
	        		table.insertAdjacentHTML('beforeend', person)
	        	}
    		}else {
    			person = html(data);
    			table.insertAdjacentHTML('beforeend', person)
    		}
        }
    };
    xhr.open('POST','../EmployeeServlet', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("who="+who+"&crud=read");
}

var html = (item) => {
	let string = `
		<tr>
			<td style="text-align: center">${item.id}</td>
			<td>${item.firstName} ${item.lastName}</td>
			<td>${item.jobTitle}</td>
			<td>${item.reportsTo}</td>
			<td>${item.isManager}</td>
		</tr>`;
	return string;
}
function updateTable(){
	clearTable();
	
	let inputTable = `
		<tr>
			<td><input type="text" id="empJobTitle" name="employee_job_title" value="${data.jobTitle}"</td>
			<td><input type="text" id="empFirstName" name="employee_first_name" value="${data.firstName}"></td>
			<td> <input type="text" id="empLastName" name="employee_last_name" value="${data.lastName}"></td>
			<td><input type="text" id="empManager" name="manager_id" value="${data.reportsTo}"></td>
			<td><label>Manager:</label>
			<input type="checkbox" name="manager_status" value="is_manager"`; if(data.isManager){inputTable += `checked="true"`;} inputTable+= `></td>
		</tr>`;
	table.insertAdjacentHTML('beforeend', inputTable);
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