let form1=document.getElementById("form2")
let email1=document.getElementById("email1")
let pw1=document.getElementById("pw1")
let pw2=document.getElementById("pw2")


form1.addEventListener("submit",(e)=>{
    alert("Right Now This Service is not available ")
    e.preventDefault()
    let data=window.fetch("./user.json", {
        method: 'POST' 
    });
    data.then((d)=>{
    let fD=d.json()
    fD.then(
        (final)=>{
            console.log(pw1.value,pw2.value,email1.value);
        })
    },
    (err)=>console.log(err)
)
},
(e)=>console.log(e)
)