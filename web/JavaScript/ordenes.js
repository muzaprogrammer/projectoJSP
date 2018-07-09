/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function statement_loading() {
    var mensaje = '<div class="modal-dialog"><div class="modal-content"><div class="modal-body" align="center"><div class="panel-body"><strong><h2>PROCESANDO LA INFORMACI&Oacute;N</h2></strong><img src="assets/images/progress.gif" width="150" height="150"></div></div></div></div>';
    return mensaje;
}

$('#tabla_ordenes').DataTable({
    "aaSorting": []
    , "displayLength": 25
});

function cargar() {
    $.ajax({
        url: 'SERVOrdenes',
        type: "POST",
        data: {accion: "cargar",estado:$("#estado").val()},
        beforeSend: function () {
            $('#div_tabla').show();
            $('#div_tabla').html('<div align="center"><h3>BUSCANDO REGISTROS</h3><img src="assets/images/progress.gif" width="75" height="75"></div></div>');
        },
        success: function (response) {
            $('#div_tabla').html(response);
        }
    });
}

function cargarDetalle() {
    var estadoSave = $("#estadoSave").val();
    $.ajax({
        url: 'SERVOrdenes',
        type: "POST",
        data: {accion: "cargarDetalle", estadoSave: estadoSave},
        beforeSend: function () {
            $('#div_tabla').show();
            $('#div_tabla').html('<div align="center"><h3>BUSCANDO REGISTROS</h3><img src="assets/images/progress.gif" width="75" height="75"></div></div>');
        },
        success: function (response) {
            $('#div_tabla').html(response);
        }
    });
}

function agregar_producto() {

    var cliente = $("#cliente").val();
    var idTipoOrden = $("#idTipoOrden").val();
    var observaciones = $("#observaciones").val();
    var idArticulo = $("#idArticulo").val();
    var cantidad = $("#cantidad").val();
    var estadoSave = $("#estadoSave").val();

    if (cliente == "") {
        swal("No llenado el campo de cliente!");
    } else if (idTipoOrden == "") {
        swal("No seleccionado el tipo de orden!");
    } else if (idArticulo == "") {
        swal("No seleccionado ningun producto!");
    } else if (cantidad == "") {
        swal("Debe llenar la cantidad");
    } else {
        $.ajax({
            url: 'SERVOrdenes',
            type: "POST",
            data: {
                accion: "addDetalle",
                cliente: cliente,
                idTipoOrden: idTipoOrden,
                observaciones: observaciones,
                idArticulo: idArticulo,
                cantidad: cantidad,
                estadoSave: estadoSave
            },
            beforeSend: function () {
                $('#stateModal').modal('toggle');
                $('#stateModal').modal('show');
            },
            complete: function () {
                $('#stateModal').modal('hide');
            },
            success: function (response) {
                if (response > 0) {
                    swal({
                        title: 'Exito!',
                        text: 'Producto agregado a la orden!',
                        type: 'success',
                        confirmButtonColor: '#ff9933'
                    }).then(function () {
                        $("#cantidad").val("");
                        $("#idProducto").val("").trigger('change');
                        $("#estadoSave").val(response);
                        cargarDetalle();
                    });

                } else {
                    swal({
                        title: 'Oops...',
                        text: 'Hubo un error al agregar el producto, por favor intentelo de nuevo mas tarde!',
                        type: 'error',
                        confirmButtonColor: '#4fb7fe'
                    }).done();
                }
            }
        });
    }
}

function cerrar_modal(){
  $('#div_modal').modal('hide');
}

function ver(id){
  $.ajax({
    url: 'SERVOrdenes',
    type: "POST",
    data: {accion:"verDetalle",estadoSave:id},
    beforeSend: function(){
      $('#div_modal').html(statement_loading());
      $('#div_modal').modal({backdrop: 'static', keyboard: false});
    },
    complete: function(){
      $('#div_modal').html();
     },
    success: function (response) {
      $('#div_modal').html(response);
    }
  });
}

function proceso(id){
  if(id!=""){
    swal({
      title: '¿Estas seguro?',
      text: '¿Estas a punto de asignar esta orden en proceso?',
      type: 'question',
      showCancelButton: true,
      confirmButtonColor: '#4fb7fe',
      cancelButtonColor: '#EF6F6C',
      allowOutsideClick: false,
      confirmButtonText: 'Si, Procesar!',
      cancelButtonText: 'Cancelar'
    }).then(function () {
      $.ajax({
        url: 'SERVOrdenes',
        type: "POST",
        data: {id:id,estado:4,accion:"procesar"},
        beforeSend: function(){
          $('#stateModal').modal('toggle');
          $('#stateModal').modal('show');
        },
        complete: function(){
          $('#stateModal').modal('hide');
        },
        success: function (response) {
          if(response == 1){
            swal({
              title: 'Exito!',
              text: 'Orden en proceso!',
              type: 'success',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }else {
            swal({
              title: 'Ooops !',
              text: 'Error al procesar. Por favor vuelva a intentarlo mas tarde!',
              type: 'error',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }
        }
      });
    });
  }else {
    swal("Debe llenar los campos requeridos!");
  }
}

function finalizar(id){
  if(id!=""){
    swal({
      title: '¿Estas seguro?',
      text: '¿Estas a punto de finalizar esta orden?',
      type: 'question',
      showCancelButton: true,
      confirmButtonColor: '#4fb7fe',
      cancelButtonColor: '#EF6F6C',
      allowOutsideClick: false,
      confirmButtonText: 'Si, Finalizar!',
      cancelButtonText: 'Cancelar'
    }).then(function () {
      $.ajax({
        url: 'SERVOrdenes',
        type: "POST",
        data: {id:id,estado:5,accion:"procesar"},
        beforeSend: function(){
          $('#stateModal').modal('toggle');
          $('#stateModal').modal('show');
        },
        complete: function(){
          $('#stateModal').modal('hide');
        },
        success: function (response) {
          if(response == 1){
            swal({
              title: 'Exito!',
              text: 'Orden finalizada!',
              type: 'success',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }else {
            swal({
              title: 'Ooops !',
              text: 'Error al finalizar. Por favor vuelva a intentarlo mas tarde!',
              type: 'error',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }
        }
      });
    });
  }else {
    swal("Debe llenar los campos requeridos!");
  }
}

function eliminar(id){
  if(id!=""){
    swal({
      title: '¿Estas seguro?',
      text: '¿Estas a punto de eliminar esta orden?',
      type: 'question',
      showCancelButton: true,
      confirmButtonColor: '#4fb7fe',
      cancelButtonColor: '#EF6F6C',
      allowOutsideClick: false,
      confirmButtonText: 'Si, Eliminar!',
      cancelButtonText: 'Cancelar'
    }).then(function () {
      $.ajax({
        url: 'SERVOrdenes',
        type: "POST",
        data: {id:id,estado:2,accion:"procesar"},
        beforeSend: function(){
          $('#stateModal').modal('toggle');
          $('#stateModal').modal('show');
        },
        complete: function(){
          $('#stateModal').modal('hide');
        },
        success: function (response) {
          if(response == 1){
            swal({
              title: 'Exito!',
              text: 'Orden eliminada!',
              type: 'success',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }else {
            swal({
              title: 'Ooops !',
              text: 'Error al eliminar. Por favor vuelva a intentarlo mas tarde!',
              type: 'error',
              confirmButtonColor: '#ff9933'
            }).then( function() {
              cargar();
            });
          }
        }
      });
    });
  }else {
    swal("Debe llenar los campos requeridos!");
  }
}