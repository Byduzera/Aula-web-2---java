<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Login</h1>
	<form action="login" method="post">
		<div>
			<label for="email">E-mail:</label> <input type="text" id="email"
				name="email" required>
		</div>

		<div>
			<label for="password">Senha:</label> <input type="password"
				id="password" name="password" required>
		</div>

		<button type="submit">Entrar</button>
	</form>
</body>
</html>