<%@include file="includes/auth.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<%@include file="includes/header.jsp"%>
</head>
<body>

	<%@include file="includes/IncludeNavbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Login</div>
			<div class="card-body">
				<form action="login" method="post">

					<div class="form-group">
						<label>Email</label> <input type="email" class="form-control"
							name="login-email" placeholder="nome@email.com" required>
					</div>
					<div class="form-group">
						<label>Senha</label> <input type="text" class="form-control"
							name="login-password" placeholder="******" required>
					</div>

					<div class="text-center">
						<span id="error-msg">${erro}</span>
						<button type="submit" class="btn btn-primary">Login</button>
					</div>


				</form>

			</div>
		</div>
	</div>

	


	<%@include file="includes/footer.jsp"%>
</body>
</html>
