let company=document.getElementById('company')
let title=document.getElementById("title")
// console.log(company)
let cd=document.getElementById('company_data')
console.log(cd);
let title2=document.getElementById("title2")
let c1=document.getElementById("car")
let b1=document.getElementById("back")




let data=window.fetch("./data.json")
data.then(    (d)=>{
    let fD=d.json()
    fD.then(
        (final)=>{
            // console.log(final)
            for (let i of final){
                let {name,location,id}=i
                company.innerHTML+=`<section id="${id}" onclick=clk(event,id) class="company_name ${name}"><p>${name}</p><address>${location}</address>
                <button class="operations"><a href="http://localhost:8080/company/add
">ADD</a></button>
                <button class="operations"><a href="http://localhost:8080/company/update/${id}
">UPDATE</a></button>
                <button class="operations"><a href="http://localhost:8080/company/delete/${id}
">DELETE</a></button>
                <button class="operations"><a href="http://localhost:8080/company/get/${id}
">INFO</a></button>
                
                </section>`
            }
        },
        (err)=>console.log(err)
    )
},
(e)=>console.log(e)
)

async function clk(event,id){
    company.style.display="none"
    b1.style.display="inline"
    console.log(title);
    cd.style.display="flex"
    let data=window.fetch("./data.json")
        data.then(    (d)=>{
            let fD=d.json()
            fD.then(
                (final)=>{
                    // console.log(final)
                    for (let k of final){
                        if (id==k.id){
                            let {cars,name}=k
                            title.innerText=`${name}`
                            title2.innerText='List of the Cars'
                            for (let car of cars){
                                let {id:No,model,year}=car
                                c1.innerHTML+=`
                                <tr class="car_data">
                                <td>${No}</td>
                                <td>${model}</td> 
                                <td>${year}</td> 
                                <td id="operationss">
                                <button class="operations"><a href="http://localhost:8080/car/add
">ADD</a></button>
                                <button class="operations"><a href="http://localhost:8080/car/update/${id}
">UPDATE</a></button>
                                <button class="operations"><a href="http://localhost:8080/car/delete/${id}
">DELETE</a></button>
                                <button class="operations"><a href="http://localhost:8080/car/${id}
">INFO</a></button>
                                </td>    
                                </tr>`
                            }
                    }}
                },
                (err)=>console.log(err)
            )
        },
        (e)=>console.log(e)
)
    
}