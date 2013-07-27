<head>
	<title>State [show]</title>
</head>
<body>
	<p>
		<b>Thirsty level:</b>
		${state.thirstyLevel}
	</p>
	<p>
		<b>Hungry level:</b>
		${state.hungryLevel}
	</p>

	<a href="${pageContext.request.contextPath}/states/${state.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/states">Back</a>
</body>