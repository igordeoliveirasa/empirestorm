<head>
	<title>PlayerCredentials [show]</title>
</head>
<body>
	<p>
		<b>Name:</b>
		${playerCredentials.name}
	</p>
	<p>
		<b>Email:</b>
		${playerCredentials.email}
	</p>
	<p>
		<b>Facebook user:</b>
		${playerCredentials.facebookUserId}
	</p>
	<p>
		<b>Password:</b>
		${playerCredentials.password}
	</p>

	<a href="${pageContext.request.contextPath}/playerCredentials/${playerCredentials.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/playerCredentials">Back</a>
</body>