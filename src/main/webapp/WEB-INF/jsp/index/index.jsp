<head>
	<title>Index</title>
</head>
<body>
    <div class="container">
        <h1>Empire Storm</h1>
        Jogador: <strong>${sm.player.name}</strong>
        <hr/>
        <div class="row-fluid">

            <div class="span3">
                <h4>Resumo</h4>
                <table class="table table-bordered">
                  <!--thead>
                    <tr>
                      <th></th>
                    </tr>
                  </thead-->
                  <tbody>
                    <tr>
                      <td>Level</td>
                      <td>1</td>
                    </tr>
                    <tr>
                      <td>Gold</td>
                      <td>$ 0.00</td>
                    </tr>
                  </tbody>
                </table>
                
                <hr/>
                
                <h4>Feedback</h4>
                <form style="width: 254px;" action="${pageContext.request.contextPath}/feedbacks" method="post">
                    <textarea style="width: 100%;" name="feedback.message" placeholder="Críticas, sugestões e/ou elogios..." rows="10">${feedback.message}</textarea><br/>
                    <button type="submit">Enviar</button>
                </form>                
            </div>

            <div class="span6">
                <h4>Ações</h4>
            </div>

            <div class="span3">

                <h4>Ranking de Amigos</h4>
                <ul>
                    <c:forEach var="player" items="${players}">
                        <li>${player.name}</li>
                    </c:forEach>
                </ul>
                <hr/>
                <h4>Ranking Geral</h4>
                <ul>
                    <c:forEach var="player" items="${players}">
                        <li>${player.name}</li>
                    </c:forEach>
                </ul>

            </div>
        </div>
    </div>
</body>