let form=document.getElementById("form1")
let email=document.getElementById("email")
let pw=document.getElementById("pw")
form.addEventListener("submit",(e)=>{
    e.preventDefault()
    let data=window.fetch("./user.json", {
        method: 'GET' 
    });
    data.then((d)=>{
    let fD=d.json()
    fD.then(
        (final)=>{
            let k=final["users"]
            if (email.value in k){
                if (pw.value == k[email.value]){
                    alert("Login Succefully !!");
                    form.submit();
                }
                else{
                    alert("Wrong Password");
                }
            }
            else{
                alert("Email does not exists");
                e.preventDefault()

            }
        })
    },
    (err)=>console.log(err)
)
},
(e)=>console.log(e)
)


