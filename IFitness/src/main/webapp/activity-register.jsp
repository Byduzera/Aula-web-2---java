<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>iFitness - Página de Cadastro de Atividade</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container ">
		<div class="center col-lg-6 col-sm-12">
				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Atividade cadastrada com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'notRegistered'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Atividade não cadastrada. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
			<form action="activityRegister" method="post" id="form1">

					<h1 class="text-center">Nova Atividade</h1>
					
					<div class="mb-2">
						<label for="type">Tipo*</label> 
						<select class="form-select"
							name="type" id="type" required="required">
							<option value="" selected>Selecione</option>
							<option value="CAMINHADA">Caminhada</option>
							<option value="CICLISMO">Ciclismo</option>
							<option value="CORRIDA">Corrida</option>
							<option value="NATACAO">Natação</option>
						</select>
					</div>
					
					<div class="mb-2">
						<label for="date">Data*</label> 
						<input type="date" name="date" id="date"
							class="form-control" required="required">
					</div>

					<div class="mb-2">
						<label for="distance">Distância (Km)*</label> 
						<input type="number"
							name="distance" id="distance" class="form-control" step="0.1" 
							required="required">
					</div>

					<div class="mb-2">
						<label for="duration">Duração (minutos)*</label> 
						<input type="number" step="1"
							name="duration" id="duration" class="form-control" required="required">
					</div>

					<div class="col-12 mb-2">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>