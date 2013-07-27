<head>
	<title>Index</title>
</head>
<body>
    <div class="row-fluid">
        <div class="span4">
            Nome do Jogador: ${sm.player.name}
        </div>
        
        <div class="span4">
            Jogadores cadastrados:<br/>
            <c:forEach var="player" items="${players}">
                ${player.name}<br/>
            </c:forEach>
        </div>
        <div class="span4">
            <form action="${pageContext.request.contextPath}/feedbacks" method="post">
                Por favor, deixe-nos sugestões, críticas ou elogios:<br />
                <textarea name="feedback.message">${feedback.message}</textarea><br/>
                <button type="submit">Enviar</button>
            </form>
        </div>
    </div>
</body>