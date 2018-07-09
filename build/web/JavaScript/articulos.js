/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function statement_loading(){
  var mensaje = '<div class="modal-dialog"><div class="modal-content"><div class="modal-body" align="center"><div class="panel-body"><strong><h2>PROCESANDO LA INFORMACI&Oacute;N</h2></strong><img src="assets/images/progress.gif" width="150" height="150"></div></div></div></div>';
  return mensaje;
}

function cargar(){
  $.ajax({
    url: 'SERVArticulos',
    type: "POST",
    data: {accion:"cargar"},
    beforeSend: function(){
      $('#div_tabla').show();
      $('#div_tabla').html('<div align="center"><h3>BUSCANDO REGISTROS</h3><img src="assets/images/progress.gif" width="75" height="75"></div></div>');
    },
    success: function (response) {
      $('#div_tabla').html(response);
    }
  });
}

function agregar(){
  $.ajax({
    url: 'SERVArticulos',
    type: "POST",
    data: {accion:"agregar"},
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

function editar(idArticulo,idCategoria,codigo,descripcion,precioVenta,precioCompra){
  $.ajax({
    url: 'SERVArticulos',
    type: "POST",
    data: {
        accion:"editar",
        idArticulo:idArticulo,
        idCategoria:idCategoria,
        codigo:codigo,
        descripcion:descripcion,
        precioVenta:precioVenta,
        precioCompra:precioCompra
    },
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

function cerrar_modal(){
  $('#div_modal').modal('hide');
}

function eliminar(id){
  swal({
    title: '¿Estas seguro?',
    text: '¿Estas a punto de eliminar el articulo?',
    type: 'question',
    showCancelButton: true,
    confirmButtonColor: '#4fb7fe',
    cancelButtonColor: '#EF6F6C',
    allowOutsideClick: false,
    confirmButtonText: 'Si, Eliminar!',
    cancelButtonText: 'Cancelar'
  }).then(function () {
    $.ajax({
    url: 'SERVArticulos',
    type: "POST",
    data: {accion:"eliminar",id:id},
      beforeSend: function(){
        $('#myModal').modal('toggle');
        $('#myModal').modal('show');
      },
      complete: function(){
        $('#myModal').modal('hide');
      },
      success: function (response) {
        if(response == 1){
          swal({
            title: 'Exito!',
            text: 'Articulo eliminado!',
            type: 'success',
            confirmButtonColor: '#ff9933'
          }).then( function() {
            cargar();
          });
        }else {
          swal({
            title: 'Ooops !',
            text: 'Error al eliminar el articulo!',
            type: 'error',
            confirmButtonColor: '#ff9933'
          }).then( function() {  });
        }
      }
    });
  });
}

function guardar() {
    var idCategoria = $("#idCategoria").val();
    var codigo = $("#codigo").val();
    var descripcion = $("#descripcion").val();
    var precioVenta = $("#precioVenta").val();
    var precioCompra = $("#precioCompra").val();

    if (idCategoria > 0) {
        if (codigo == "") {
            swal("Debe escribir el codigo!");
        } else if (descripcion == "") {
            swal("Debe escribir la descripcion!");
        } else if (precioVenta > 0) {
            if (precioCompra > 0) {
                swal({
                    title: '¿Estas seguro?',
                    text: '¿Estas a punto de guardar el articulo?',
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#4fb7fe',
                    cancelButtonColor: '#EF6F6C',
                    allowOutsideClick: false,
                    confirmButtonText: 'Si, Guardar!',
                    cancelButtonText: 'Cancelar'
                }).then(function () {
                    $.ajax({
                        url: 'SERVArticulos',
                        type: "POST",
                        data: {
                            accion:"guardar",
                            idCategoria:idCategoria,
                            codigo:codigo,
                            descripcion:descripcion,
                            precioVenta:precioVenta,
                            precioCompra:precioCompra
                        },
                        beforeSend: function () {
                            $('#myModal').modal('toggle');
                            $('#myModal').modal('show');
                        },
                        complete: function () {
                            $('#myModal').modal('hide');
                        },
                        success: function (response) {
                            if (response == 1) {
                                swal({
                                    title: 'Exito!',
                                    text: 'Articulo Guardado!',
                                    type: 'success',
                                    confirmButtonColor: '#ff9933'
                                }).then(function () {
                                    cerrar_modal();
                                    cargar();
                                });
                            } else {
                                swal({
                                    title: 'Ooops !',
                                    text: 'Error al guardar el articulo!',
                                    type: 'error',
                                    confirmButtonColor: '#ff9933'
                                }).then(function () { });
                            }
                        }
                    });
                });
            } else {
                swal("Debe escribir el precio de compra!");
            }
        } else {
            swal("Debe escribir el precio de venta!");
        }
    } else {
        swal("Debe seleccionar la categoria!");
    }
}

function actualizar(){
    var idArticulo = $("#idArticulo").val();
    var idCategoria = $("#idCategoria").val();
    var codigo = $("#codigo").val();
    var descripcion = $("#descripcion").val();
    var precioVenta = $("#precioVenta").val();
    var precioCompra = $("#precioCompra").val();

    if (idCategoria > 0) {
        if (codigo == "") {
            swal("Debe escribir el codigo!");
        } else if (descripcion == "") {
            swal("Debe escribir la descripcion!");
        } else if (precioVenta > 0) {
            if (precioCompra > 0) {
                swal({
                    title: '¿Estas seguro?',
                    text: '¿Estas a punto de actualizar el articulo?',
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#4fb7fe',
                    cancelButtonColor: '#EF6F6C',
                    allowOutsideClick: false,
                    confirmButtonText: 'Si, Actualizar!',
                    cancelButtonText: 'Cancelar'
                }).then(function () {
                    $.ajax({
                        url: 'SERVArticulos',
                        type: "POST",
                        data: {
                            accion:"actualizar",
                            idArticulo:idArticulo,
                            idCategoria:idCategoria,
                            codigo:codigo,
                            descripcion:descripcion,
                            precioVenta:precioVenta,
                            precioCompra:precioCompra
                        },
                        beforeSend: function () {
                            $('#myModal').modal('toggle');
                            $('#myModal').modal('show');
                        },
                        complete: function () {
                            $('#myModal').modal('hide');
                        },
                        success: function (response) {
                            if (response == 1) {
                                swal({
                                    title: 'Exito!',
                                    text: 'Articulo Actualizado!',
                                    type: 'success',
                                    confirmButtonColor: '#ff9933'
                                }).then(function () {
                                    cerrar_modal();
                                    cargar();
                                });
                            } else {
                                swal({
                                    title: 'Ooops !',
                                    text: 'Error al actualizar el articulo!',
                                    type: 'error',
                                    confirmButtonColor: '#ff9933'
                                }).then(function () { });
                            }
                        }
                    });
                });
            } else {
                swal("Debe escribir el precio de compra!");
            }
        } else {
            swal("Debe escribir el precio de venta!");
        }
    } else {
        swal("Debe seleccionar la categoria!");
    }
}
