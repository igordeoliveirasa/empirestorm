<head>
	<title>Index</title>
</head>
<body>
    <div class="container">
        <h1>Empire Storm</h1>
        Jogador: <strong>${sm.player.name}</strong>
        <hr/>
        <div class="row-fluid">

            <div class="span2">
                <h3>Resumo</h3>
                <table class="table table-bordered">
                  <!--thead>
                    <tr>
                      <th></th>
                    </tr>
                  </thead-->
                  <tbody>
                    <tr>
                      <td>Gold</td>
                      <td>$ 0.00</td>
                    </tr>
                  </tbody>
                </table>
            </div>

            <div class="span7">
                <h3>Ações</h3>
            </div>

            <div class="span3">

                <h3>Ranking de Amigos</h3>
                <ul>
                    <c:forEach var="player" items="${players}">
                        <li>${player.name}</li>
                    </c:forEach>
                </ul>

                <h3>Ranking Geral</h3>
                <ul>
                    <c:forEach var="player" items="${players}">
                        <li>${player.name}</li>
                    </c:forEach>
                </ul>

                <h3>Feedback</h3>
                <form action="${pageContext.request.contextPath}/feedbacks" method="post">
                    <textarea name="feedback.message" placeholder="Críticas, sugestões e/ou elogios..." rows="10">${feedback.message}</textarea><br/>
                    <button type="submit">Enviar</button>
                </form>                
            </div>
        </div>
    </div>
</body>