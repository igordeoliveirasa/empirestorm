<head>
	<title>Player [show]</title>
</head>
<body>
	<p>
		<b>Name:</b>
		${player.name}
	</p>

	<a href="${pageContext.request.contextPath}/players/${player.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/players">Back</a>
</body>