let company=document.getElementById('company')
let title=document.getElementById("title")
// console.log(company)
let cd=document.getElementById('company_data')
// console.log(cd);
let title2=document.getElementById("title2")
let c1=document.getElementById("car")
let b1=document.getElementById("back")
let b=0;



let obj={"operation":"car"}
sessionStorage.setItem('formData', JSON.stringify(obj))

let data=window.fetch("http://localhost:8080/company/all")
data.then( (d)=>{
    let fD=d.json()
    fD.then(
        (final)=>{
            console.log(final)
            
            final.t.sort((a,b)=>a.id-b.id)

            for (let i of final.t){
                let {name,location,id}=i
                company.innerHTML+=`<section id="${id}" onclick=clk(event,id) class="company_name ${name}"><p>${name}</p><address>${location}</address></section>
                
            <a id="info" href="./operation.html">INFO</a>
                
                `
            }

        },
        (err)=>console.log(err)
    )
},
(e)=>console.log(e)
)

async function clk(event,id){
    b=1
    company.style.display="none"
    b1.style.display="inline"
    cd.style.display="flex"
    let data=window.fetch("http://localhost:8080/company/all")
        data.then(    (d)=>{
            let fD=d.json()
            fD.then(
                (final)=>{
                    // console.log(final)
                    
                    final.t.sort((a,b)=>a.id-b.id)
                    for (let k of final.t){
                        if (id==k.id){
                            let {cars,name}=k
                            title.innerText=`${name}`
                            title2.innerText='List of the Cars'
                            let obj1={"operation":"car11","id":id}
                            sessionStorage.setItem('car', JSON.stringify(obj1))
                            for (let car of cars){
                                let {id:No,model,year}=car
                                c1.innerHTML+=`
                                <tr class="car_data">
                                <td>${No}</td>
                                <td>${model}</td> 
                                <td>${year}</td>   
                                </tr>`
                            }
                            let obj={"operation":"car11"}
                            sessionStorage.setItem('formData', JSON.stringify(obj))
                            let kt=document.getElementById(`add`)
                            kt.addEventListener("click",(v)=>{
                            let obj={"operation":"add_car"}
                            sessionStorage.setItem('formData', JSON.stringify(obj));
                            window.location.href = "operation.html";})

                            let k1=document.getElementById(`update`)
                            k1.addEventListener("click",(v)=>{
                                let obj={"operation":"update_car"}
                                sessionStorage.setItem('formData', JSON.stringify(obj));
                                window.location.href = "operation.html";

                            })
                            let k2=document.getElementById(`delete`)
                            k2.addEventListener("click",(v)=>{
                                let obj={"operation":"delete_car"}
                                sessionStorage.setItem('formData', JSON.stringify(obj));
                                window.location.href = "operation.html";})
                    }}
                },
                (err)=>console.log(err)
            )
        },
        (e)=>console.log(e)
)
    
}


const formData = JSON.parse(sessionStorage.getItem('formData'));
console.log(formData.operation,"server")


if (formData.operation=="car"){
console.log("aaa");
let k=document.getElementById(`add`)
k.addEventListener("click",(v)=>{
let obj={"operation":"add","company":"id"}
sessionStorage.setItem('formData', JSON.stringify(obj));
window.location.href = "operation.html";})

let k1=document.getElementById(`update`)
k1.addEventListener("click",(v)=>{
    let obj={"operation":"update"}
    sessionStorage.setItem('formData', JSON.stringify(obj));
    window.location.href = "operation.html";

})
let k2=document.getElementById(`delete`)
k2.addEventListener("click",(v)=>{
    let obj={"operation":"delete"}
    sessionStorage.setItem('formData', JSON.stringify(obj));
    window.location.href = "operation.html";

})
}
