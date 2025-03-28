const formData = JSON.parse(sessionStorage.getItem('formData'));
console.log(formData)

    
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
