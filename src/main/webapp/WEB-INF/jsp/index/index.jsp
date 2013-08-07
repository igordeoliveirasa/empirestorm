<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
	<title>Index</title>
</head>
<body>
        
    <div class="row-fluid">

        <div class="span3">            

            <h4>Itens Básicos</h4>
            <table class="table table-bordered">
              <!--thead>
                <tr>
                  <th></th>
                </tr>
              </thead-->
              <tbody>
                <tr>
                  <td>Madeira</td>
                  <td>0</td>
                </tr>
              </tbody>
            </table>                

            <h4>Acessórios</h4>
            <table class="table table-bordered">
              <!--thead>
                <tr>
                  <th></th>
                </tr>
              </thead-->
              <tbody>
                <tr>
                  <td>Bastão</td>
                  <td>0</td>
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
            <c:if test="${playerActionWalkInProgress!=null}">
                <h4>Atividades em progresso</h4>
                
                    Caminhando de "${playerActionWalkInProgress.fromPlace.name}" para "${playerActionWalkInProgress.toPlace.name}"...<br/>

                    <div class="progress progress-striped active">
                        <div id="bar1" class="bar" style="width: ${playerActionWalkInProgress.progressValue*100}%;"></div>
                    </div>
                    <a href="${linkTo[PlayerController].actionStopWalkTo}" class="btn btn-small btn-danger">Parar</a>

                    <script>


                        firstTime = ${playerActionWalkInProgress.createdAt.time};
                        currentTime = ${playerActionWalkInProgress.currentTime};
                        endTime = (firstTime + ${playerActionWalkInProgress.durationInMinutes} * 60 * 1000);

                        intervalId = setInterval(myMethod, 500); // each 1 second

                        function myMethod( )
                        {
                            currentTime += 500;
                            diffTime1 = currentTime - firstTime;
                            diffTime2 = endTime - firstTime;
                            perc = (diffTime1/diffTime2) * 100;
                            $("#bar1").css('width', perc + '%');

                            if (perc>=100) {
                                clearInterval(intervalId);
                                document.location.href = "/";
                            }
                        }

                    </script>
            </c:if>
            
            <c:if test="${playerActionWalkInProgress==null}">
                <h4>Localização</h4>
                <p>Você se encontra em: <strong>${sm.player.place.name}</strong></p>
                <p>Tipo de ambiente: <strong>${sm.player.place.type.name}</strong></p>

                <!--h5>Aqui você pode:</h5>
                <a href="#" class="btn btn-small btn-success">Beber água</a>

                <hr/-->

                <hr/>
                <h5>Ir para:</h5>

                    <table class="table table-bordered">
                        <thead>
                          <tr>
                            <th>Nome</th>
                            <th>Ambiente</th>
                            <th>Distância</th>
                            <th>Tempo</th>
                            <th>#</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="action" items="${actionsWalk}">
                                <tr>
                                    <td>${action.toPlace.name}</td>
                                    <td>${action.toPlace.type.name}</td>
                                    <td>${action.formattedDistance}</td>
                                    <td>${action.formattedDuration}</td>
                                    <td><a href="${linkTo[PlayerController].actionWalkTo}${action.toPlace.id}" class="btn btn-small btn-success">Ir</a></td>
                                </tr>
                          </c:forEach>
                        </tbody>
                      </table>   
            </c:if>

                
            
        </div>

        <div class="span3">

            <h4>Forças</h4>
            <table class="table table-bordered">
                <!--thead>
                  <tr>
                    <th>Forças</th>
                  </tr>
                </thead-->
                <tbody>
                  <tr>
                    <td>Gold</td>
                    <td>$ ${sm.player.gold}</td>
                  </tr>
                </tbody>
            </table>
            
            <h4>Estado Fisiológico</h4>
            
            
            <table class="table table-bordered ">
                <!--thead>
                  <tr>
                    <th>Estado Fisiológico</th>
                  </tr>
                </thead-->
                <tbody>
                  <tr>
                    <td>Sede</td>
                    <td style="width: 100%">
                        <div class="progress">
                            <div class="bar bar-danger" style="width: ${sm.player.state.thirstyLevel*100}%;"></div>
                        </div>
                    </td>
                  </tr>
                  <tr>
                    <td>Fome</td>
                    <td>
                        <div class="progress">
                            <div class="bar bar-danger" style="width: ${sm.player.state.hungryLevel*100}%;"></div>
                        </div>                        
                    </td>
                  </tr>
                </tbody>
            </table>   
            
            <hr/>
            
            <h4>Ranking de Amigos</h4>
            <ul>
                <c:forEach var="player" items="${players}">
                    <li>${player.name} | $ ${player.gold}</li>
                </c:forEach>
            </ul>
            <hr/>
            <h4>Ranking Geral</h4>
            <ul>
                <c:forEach var="player" items="${players}">
                    <li>${player.name} | $ ${player.gold}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

</body>