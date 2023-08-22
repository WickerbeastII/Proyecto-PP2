// get/usuario

function enviar_c(){
    console.log("llega inicio de sesion")
    let usuario = document.getElementById("usser").value;
    let pass = document.getElementById("pass").value;  
    let data = {"usuario" : usuario, "pass": pass}; 

    alert(data.usuario+" "+data.pass);   
    
    $.ajax({
            type: "POST",
            url: "http://localhost:8081/login",                // post/login (user, pass)
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

function enviar(){
    console.log("llega inicio de sesion");
    let usuario = document.getElementById("usser").value;
    let pass = document.getElementById("pass").value;  
    let data = {"user" : usuario, "password": pass}; 
    
    if(validar_usuario(usuario)==true){

        if(validar_pass(pass)==true){

			//window.location = "./menuprincipal.html";
            $.ajax({
                    type: "POST",
                    url: "http://localhost:8081/login",                // post/login (user, pass)
                    contentType: "application/json",                   // Se añadio la forma en que se envia los datos
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
    }
    
}

function registrar_usuario(){

    console.log("registar usuario") ;     
    let data;
    try { // Nota: esto sirve para coger los datos del formulario
        data = {
            apellido: document.getElementById("apellido").value,
            dni: document.getElementById("dni").value,
            tipo: document.getElementById("tipo_usuario").value,
            email: document.getElementById("email").value,
            password: document.getElementById("pass_1").value,
            fechaNacimiento: document.getElementById("fecha_nacimiento").value
        }
    } catch (error) {
        console.error("Fallo al conseguir los datos de formulario", error)
    }

    //let data = JSON.stringify()
    if(validar_registro()==true){    
      
       // window.location = "./mensajebienvenida.html";
       $.ajax({
            type:"POST",
            url:"http://localhost:8081/registrar", //post/usuario (id,nombre/user/pass/rol) 
            contentType: "application/json",       // Se añadio la forma en que se envia los datos
            data: JSON.stringify(data),
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


    
}
function registrar_usuario_c(){

    alert("registar usuario")      
    //let data = JSON.stringify()    
      
    $.ajax({
        type:"POST",
        url:"http://localhost:8081/registrar", //post/usuario (id,nombre/user/pass/rol) 
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


//----------------- VALIDACIONES -------------------------------

function validar_pass(p){
    document.getElementById("lbl_pass").classList.add("t_login")

    if(p.length == 8){
        return true;
    }
    else{
        //clear
        document.getElementById("lbl_pass").classList.add("t_error")
        document.getElementById("pass").value="";           
        alert("la contraseña no es valida")
        document.getElementById("pass").focus();
    }
}

function validar_usuario(u){
    document.getElementById("lbl_usuario").classList.add("t_login")
    
    if(u!=""){
        return true;
    }
    else{
        alert("verifique el usuario ingresado");
        document.getElementById("lbl_usuario").classList.add("t_error")    
        document.getElementById("usser").focus();
    }
}


function validar_registro(){
    let email = document.getElementById("email").value;
    
    let pass = document.getElementById("pass_1").value; 
    let apellido = document.getElementById("apellido").value; 
    let nombre = document.getElementById("nombre").value;

    validar_dni();   
    
    if(email!="" && pass!=""  && apellido!="" && nombre!="" && dni!="" ){ //&& validar_pass_pass()==true && validar_dni()==true
        document.getElementById("lbl_tipo_usuario").classList.remove("t_error");
        document.getElementById("lbl_apellido").classList.remove("t_error");
        document.getElementById("lbl_nombre").classList.remove("t_error");       
        document.getElementById("lbl_email").classList.remove("t_error");
         
        if(pass.length>=8){

            if(validar_pass_pass()==true){
                document.getElementById("lbl_pass_1").classList.remove("t_error");       
                document.getElementById("lbl_pass_2").classList.remove("t_error"); 
    
                if(validar_dni()==true){
                    return true;
                }
            }
        }  
        else{
            alert("verifique la contraseña");
            document.getElementById("lbl_pass_1").classList.add("t_error");  
        }
    }
    else{
        document.getElementById("lbl_tipo_usuario").classList.add("t_error");
        document.getElementById("lbl_apellido").classList.add("t_error");
        document.getElementById("lbl_nombre").classList.add("t_error");       
        document.getElementById("lbl_email").classList.add("t_error");
        document.getElementById("lbl_pass_1").classList.add("t_error");       
        document.getElementById("lbl_pass_2").classList.add("t_error");       
        
        alert("complete los datos requeridos");
    }
}

function validar_pass_pass(){
   // console.log("llega validar coincidencia")
    const pass_1= document.getElementById("pass_1").value;
    const pass_2= document.getElementById("pass_2").value;
    if(pass_1!=""){
        if(pass_1.match(pass_2)){                   
            return true;
        }
        else{        
            document.getElementById("pass_2").focus();
            document.getElementById("pass_2").value="";
            alert("Vuelva a ingresar su contraseña")   
        }
    }
    else{               
        alert("Ingrese una contraseña ");
        document.getElementById("pass_1").focus();        
    }    
}

function validar_dni(){

    // document.getElementById("lbl_tipo").classList.remove("t_error");
    // document.getElementById("lbl_doc").classList.remove("t_error");

    let numero = document.getElementById("dni").value;    
   
    const cmb =  document.getElementById("cmb_tipo_doc");
    const opt =  cmb.options[cmb.selectedIndex].text;
    
    const regDNI = /^\d{8}(?:[-\s]\d{4})?$/;
    const regCuilCuit = /^(20|23|27|30|33)([0-9]{9}|-[0-9]{8}-[0-9]{1})$/g;
  
    const long=numero.length;

    if(opt == "DNI" && numero.match(regDNI) && long == 8){
    // if(numero.match(regDNI) && long == 8){             
        return true;      
    }
    else if((opt == "CUIL" || opt == "CUIT") && numero.match(regCuilCuit) && long == 11){
         return true;
    }
    else{
        console.log("verificar datos tipo/num Documento");  
        document.getElementById("lbl_tipo").classList.add("t_error");
        document.getElementById("lbl_doc").classList.add("t_error");
        
        return false;
    }     
}