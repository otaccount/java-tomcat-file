<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	version="2.0">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" />

<jsp:include page="/common/jsp/header.jsp" />

<body>
	<button onclick="history.back()">戻る</button><br />
	entry<br />
	name:${name}
</body>

<jsp:include page="/common/jsp/footer.jsp" />

</jsp:root>