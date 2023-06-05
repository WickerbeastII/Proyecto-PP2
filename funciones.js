
// get/usuario

function enviar(){
    console.log("llega inicio de sesion")
    let usuario = document.getElementById("usser").value;
    let pass = document.getElementById("pass").value;  
    let data = {"usuario" : usuario, "pass": pass}; 

    alert(data.usuario+" "+data.pass);   
    
    $.ajax({
            type: "POST",
            url: " ",                // post/login (user, pass)
            data: JSON.stringify(data),
            async: false,
            cache: false,                    
            success: function(res){
                
            
            },
            error:function(){                
                alert("ocurrio una falla con el envio ");
            }   
    });
    
}



function registrar_usuario(){

    alert("registar usuario")      
    //let data = JSON.stringify()    
      
    $.ajax({
        type:"POST",
        url:" ", //post/usuario (id,nombre/user/pass/rol) 
        data: data,
        async: false,
        cache: false,                    
        success: function(response){
            // res=JSON.parse(response) 
            console.log("esperando respuesta ...");                
        },
        error:function(error){                
            alert("falla");
        }   
    });
    
}
