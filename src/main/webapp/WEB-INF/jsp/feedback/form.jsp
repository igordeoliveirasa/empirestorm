<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/feedbacks" method="post">
  
	<c:if test="${not empty feedback.id}">
		<input type="hidden" name="feedback.id" value="${feedback.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
            Por favor, deixe-nos sugestões, críticas ou elogios:<br />
            <input type="text" name="feedback.message" value="${feedback.message}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/feedbacks">Back</a>
