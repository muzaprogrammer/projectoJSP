/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validar(){
  var usuario = $("#usuario").val();
  var contrasena = $("#contrasena").val();
  if(usuario==""){
      swal("Debe llenar el usuario!");
  }else if(contrasena==""){
      swal("Debe llenar la contraseña!");
  }else{
      $.ajax({
        url: 'SERVLogin',
        type: "POST",
        data: {usuario:usuario,contrasena:contrasena},
        /*
        beforeSend: function(){
          $('#stateModal').modal('toggle');
          $('#stateModal').modal('show');
        },
        complete: function(){
          $('#stateModal').modal('hide');
        },
        */
        success: function (response) {
          if(response == 1){
            swal({
              title: 'Exito!',
              text: 'Usuario validado!',
              type: 'success',
              confirmButtonColor: '#ff9933'
            }).then( function() {
                window.location = "index.jsp";
            });
          }else {
            swal({
              title: 'Ooops !',
              text: 'El usuario o contraseña que ingreso no son validos!',
              type: 'error',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cerrar_modal();
            });
          }
        }
      });      
  }  
}
