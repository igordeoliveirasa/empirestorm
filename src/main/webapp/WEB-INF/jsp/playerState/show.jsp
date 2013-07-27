<head>
	<title>PlayerState [show]</title>
</head>
<body>
	<p>
		<b>Thirsty level:</b>
		${playerState.thirstyLevel}
	</p>
	<p>
		<b>Hungry level:</b>
		${playerState.hungryLevel}
	</p>

	<a href="${pageContext.request.contextPath}/playerStates/${playerState.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/playerStates">Back</a>
</body>