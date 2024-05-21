/**
 * 
 */

$('.editarEstudianteModal').on('click', function () {
	var cedula = $(this).data('cedula');
	var nombre = $(this).data('nombre');
	var apellido = $(this).data('apellido');
	var telefono = $(this).data('telefono');
	var mail = $(this).data('mail');
	//var cedulaInt = parseInt(cedula, 10);
		        //console.log(cedula)
	$('#cedulaEstudianteEditar').val(cedula);
	$('#nombreEstudianteEditar').val(nombre);
	$('#apellidoEstudianteEditar').val(apellido)
	$('#telefonoEstudianteEditar').val(telefono);
	$('#mailEstudianteEditar').val(mail);
});

$('.EliminarEstudianteModal').on('click', function () {
	let cedula = $(this).data('cedula');
	let nombre = $(this).data('nombre');
	let apellido = $(this).data('apellido');

	Swal.fire({
		title: '¿Estás seguro?',
		text: `¿Deseas eliminar a ${nombre} ${apellido}?`,
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#d33',
		cancelButtonColor: '#3085d6',
		confirmButtonText: 'Sí, eliminar',
		cancelButtonText: 'Cancelar'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "POST",
		            url: "/eliminarEstudiante",
		            data: {
		            	cedula: cedula
		            },
		            success: function (response) {
		            	// Aquí puedes mostrar un mensaje de éxito con SweetAlert
		                        
		                Swal.fire('Eliminado', 'El estudiante ha sido eliminado', 'success').then(() => {
		                	// Redireccionar a la página principal después de la animación de SweetAlert
		                	window.location.href = '/';
		                 });
		                        
		            },
		            error: function (xhr, status, error) {
		            	// Manejo de errores
		                Swal.fire('Error', 'Hubo un problema al eliminar el estudiante', 'error');
		          	}
		    	});
			}
		});
});


$('#buscarEstudiante').on('keyup', function (){

	var timeout = null;
	var cedula = $(this).val();
	console.log(cedula)
	if (timeout != null){
		clearTimeout(timeout);
	}
	
	timeout = setTimeout(function (){
		if(cedula === ""){
			location.reload();
		}else{
			$.ajax({
				type: "GET",
				url: "/buscarPorCedula",
				data:{
					cedula: cedula
					},
					success: function(data){
						$('#listaEstudiantes').html(data);
					},
					error: function(xhr, status, error){
						console.error(error);
					}
							
				});
			}		
	}, 500);

});

$(document).ready(function() {
	$('#enviarFormularioRegistro').on('click', function() {
		// Obtener los datos del formulario
		var formData = $('#nuevoEstudiante').serialize();

		// Enviar la solicitud AJAX
		$.ajax({
			type: 'POST',
		    url: '/CrearEstudiante',
		    data: formData,
		    success: function(response) {
			    // Manejar la respuesta del servidor
			    Swal.fire({
					icon: "success",
					title: "Estudiante registrado"
				}).then(()=>{
					window.location.href="/"
				});
		                    // Aquí puedes mostrar un mensaje de éxito o redireccionar a otra página
		                    // por ejemplo:
		                    // window.location.href = '/ruta-de-destino';
			},
		    error: function(xhr, status, error) {
		    	// Manejar errores
		    	console.error('Error al enviar el formulario:', error);
		   	}
		});
	});
});

$(document).ready(function() {
	$('#enviarFormularioRegistroCurso').on('click', function() {
		// Obtener los datos del formulario
		var formData = $('#nuevoCurso').serialize();

		// Enviar la solicitud AJAX
		$.ajax({
			type: 'POST',
		    url: '/crearCurso',
		    data: formData,
		    success: function(response) {
			    // Manejar la respuesta del servidor
			    Swal.fire({
					icon: "success",
					title: "Curso creado"
				}).then(()=>{
					window.location.href="/"
				});
		                    // Aquí puedes mostrar un mensaje de éxito o redireccionar a otra página
		                    // por ejemplo:
		                    // window.location.href = '/ruta-de-destino';
			},
		    error: function(xhr, status, error) {
		    	// Manejar errores
		    	console.error('Error al enviar el formulario:', error);
		   	}
		});
	});
});

$('.editarCursoModal').on('click', function () {
	var codigo = $(this).data('codigo');
	var nombre = $(this).data('nombre');
	var descripcion = $(this).data('descripcion');
	//var cedulaInt = parseInt(cedula, 10);
		        //console.log(cedula)
	$('#codigoCursoEditar').val(codigo);
	$('#nombreCursoEditar').val(nombre)
	$('#descripcionCursoEditar').val(descripcion);
});