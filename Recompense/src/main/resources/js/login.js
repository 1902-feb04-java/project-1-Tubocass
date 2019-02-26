// const validUser = "BoB";
// const validPassword = "password";
// function validateLogin(user, password)
// {
//     let vUser = false;
//     let vPassword = false;

//     if(user !==null && password !== null)
//     {
//         if(user === validUser)
//         {
//             vUser = true;
//         }else return 'Bad User Name'
//         if(password === validPassword)
//         {
//             vPassword = true;
//         }else return 'Bad Password';
//     } 
//     if(vUser && vPassword)
//     return 'Success';
// }

let form;

 window.addEventListener('DOMContentLoaded', (e)=>{
    e.preventDefault();
    form = document.getElementById('form-control')
    if(form)
    {
        form.addEventListener('submit',(e)=>{
            e.preventDefault();
            let user = document.getElementById('login-name');
            let password = document.getElementById('login-password');

            console.log(user.value);
            console.log(password.value);
            let tryLogin = validateLogin(user.value, password.value);
            if(tryLogin === 'Success')
            {
                window.location = 'access.html';
                document.getElementById('welcome').textContent = `Welcome ${user.value}`;

            }else {
                window.location = 'error.html';
                document.getElementById('errormsg').textContent = "There was a problem with your login \n"
                +"Error: "+ tryLogin;
            }
        })    
    }
})

