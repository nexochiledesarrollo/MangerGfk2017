<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- #modal-Cargando -->
<div class="modal fade"  id="modalg-charge" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-primary">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-refresh fa-spin fa-stack-1x "></i>
				</span>
				Cargando solicitud...</h1>
			</div>
			<div class="modal-body" id="modalg-charge-text"></div>
			
			<div class="modal-footer">
				<!-- <a href="javascript:;" class="btn btn-lg btn-white" data-dismiss="modal">Cerrar</a> -->
			</div>
		</div>
	</div>
</div>

<!-- #modal-error -->
<div class="modal fade"  id="modalg-error" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-danger">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-info-circle fa-stack-1x "></i>
				</span>
				Error en la solicitud...</h1>
			</div>
			<div class="modal-body" id="modalg-error-text"></div>
			
			<div class="modal-footer">
				 <a href="javascript:;" class="btn btn-lg btn-danger" data-dismiss="modal">Cerrar</a> 
			</div>
		</div>
	</div>
</div>

<!-- #modal-success -->
<div class="modal fade"  id="modalg-success" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-success">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-check fa-stack-1x "></i>
				</span>
				Solicitud Generada!</h1>
			</div>
			<div class="modal-body" id="modalg-success-text"></div>
			
			<div class="modal-footer">
				 <a href="JavaScript: $('#modalg-success').modal('hide');" class="btn btn-lg btn-success">Cerrar</a> 
			</div>
		</div>
	</div>
</div>
<!-- #modal-alert -->
<div class="modal fade"  id="modalg-alert" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-warning">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-exclamation fa-stack-1x "></i>
				</span>
				Notificaci&oacute;!</h1>
			</div>
			<div class="modal-body" id="modalg-alert-text"></div>
			
			<div class="modal-footer">
				 <a href="javascript:;" class="btn btn-lg btn-warning" data-dismiss="modal">Cerrar</a> 
			</div>
		</div>
	</div>
</div>

<!-- #modal-validando -->
<div class="modal fade"  id="modalg-val" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-info">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-refresh fa-spin fa-stack-1x "></i>
				</span>
				Validando dato...</h1>
			</div>
			<div class="modal-body" id="modalg-val-text"></div>
			
			<div class="modal-footer">
				
			</div>
		</div>
	</div>
</div>

<!-- #modal-alert -->
<div class="modal fade"  id="modalg-restlogout" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				<h1 class="modal-title">
				<span class="fa-stack fa-2x text-warning">
					<i class="fa fa-circle-o fa-stack-2x"></i>
					<i class="fa fa-exclamation fa-stack-1x "></i>
				</span>
					Session Exit!
				</h1>
			</div>
			<div class="modal-body" id="modalg-restsession-text"></div>
				<center><strong>Se ha expirado la sesión,  favor volver ingresar credenciales.</strong></center>
			<div class="modal-footer">
				 <a href="JavaScript: windowLocationLoginRestService();" class="btn btn-lg btn-warning" >Aceptar</a> 
			</div>
		</div>
	</div>
</div>
