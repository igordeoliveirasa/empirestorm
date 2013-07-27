<head>
	<title>Place [show]</title>
</head>
<body>
	<p>
		<b>Name:</b>
		${place.name}
	</p>
	<p>
		<b>X:</b>
		${place.x}
	</p>
	<p>
		<b>Y:</b>
		${place.y}
	</p>

	<a href="${pageContext.request.contextPath}/places/${place.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/places">Back</a>
</body>