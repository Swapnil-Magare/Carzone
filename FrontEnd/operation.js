const formData = JSON.parse(sessionStorage.getItem('formData'));
// console.log(formData)

    
    if (formData.operation === 'update_car') {
        document.getElementById("update_car").style.display="flex";
    } else if (formData.operation === 'add_car') {
        document.getElementById("add_car").style.display="flex";
    }
    
    else if (formData.operation === 'delete_car') {
        document.getElementById("delete_car").style.display="flex";
    }


else if (formData.operation === 'update') {
    document.getElementById("update_company").style.display="flex";
} else if (formData.operation === 'add') {
    document.getElementById("add_company").style.display="flex";
}

else if (formData.operation === 'delete') {
    document.getElementById("delete_company").style.display="flex";
}


let add_company_form=document.getElementById("add_company")
add_company_form.addEventListener("submit",async(e)=>{
    e.preventDefault()
    let data=Object.fromEntries(new FormData(add_company_form))
    try{
        await window.fetch("http://localhost:8080/company/add",{
            method:"POST",headers:{'Content-Type':"application/json"},
            body:JSON.stringify(data)
        });
        alert("New Company Added Succesfully")
    window.location.href = "home.html";
    }
        catch(err){
            console.log(AggregateError);
        }})



        let update_company_form=document.getElementById("update_company")
        update_company_form.addEventListener("submit",async(e)=>{
            e.preventDefault()
            let data=Object.fromEntries(new FormData(update_company_form))
            
            try{
                await window.fetch(`http://localhost:8080/company/update/${data.id}`,{
                    method:"PUT",headers:{'Content-Type':"application/json"},
                    body:JSON.stringify(data)
                });
                alert(`Company ${data.id} Updated Succesfully`)
            window.location.href = "home.html";
            }
                catch(err){
                    console.log(AggregateError);
                }

            })

            let delete_company_form=document.getElementById("delete_company")
            delete_company_form.addEventListener("submit",async(e)=>{
                e.preventDefault()
                let data=Object.fromEntries(new FormData(delete_company_form))
                
                try{
                    await window.fetch(`http://localhost:8080/company/delete/${data.id}`,{
                        method:"DELETE"
                    });
                    alert(`Company ${data.id} deleted Succesfully`)
                window.location.href = "home.html";
                }
                    catch(err){
                        console.log(AggregateError);
                    }
    
                })

                let add_car_form=document.getElementById("add_car")
                add_car_form.addEventListener("submit",async(e)=>{
                    e.preventDefault()
                    const id = JSON.parse(sessionStorage.getItem('car'));
                    let data=Object.fromEntries(new FormData(add_car_form))
                    data["companyId"]=id.id
                    console.log(data);
                    try{
                        await window.fetch("http://localhost:8080/car/add",{
                            method:"POST",headers:{'Content-Type':"application/json"},
                            body:JSON.stringify(data)
                        });
                        alert("New car Added Succesfully")
                    window.location.href = "home.html";
                    }
                        catch(err){
                            console.log(AggregateError);
                        }
                    })
                
                
                
                        let update_car_form=document.getElementById("update_car")
                        update_car_form.addEventListener("submit",async(e)=>{
                            e.preventDefault()
                            let data=Object.fromEntries(new FormData(update_car_form))
                            
                            try{
                                await window.fetch(`http://localhost:8080/car/update/${data.id}`,{
                                    method:"PUT",headers:{'Content-Type':"application/json"},
                                    body:JSON.stringify(data)
                                });
                                alert(`car ${data.id} Updated Succesfully`)
                            window.location.href = "home.html";
                            }
                                catch(err){
                                    console.log(AggregateError);
                                }
                
                            })
                
                            let delete_car_form=document.getElementById("delete_car")
                            delete_car_form.addEventListener("submit",async(e)=>{
                                e.preventDefault()
                                let data=Object.fromEntries(new FormData(delete_car_form))
                                
                                try{
                                    await window.fetch(`http://localhost:8080/car/deleteById/${data.id}`,{
                                        method:"DELETE"
                                    });
                                    alert(`car ${data.id} deleted Succesfully`)
                                window.location.href = "home.html";
                                }
                                    catch(err){
                                        console.log(AggregateError);
                                    }
                    
                                })
