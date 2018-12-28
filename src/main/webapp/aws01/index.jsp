<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	version="2.0">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" />

<jsp:include page="/common/jsp/header.jsp" />

<body>
	file01<br />
	<form action="AwsAct01" method="POST" enctype="multipart/form-data">
		<input type="text" name="name" value="テスト０１" /><br />
		<input type="file" name="file" size="70" /><br />
		<input type="submit" value="送信" />
	</form>
</body>

<jsp:include page="/common/jsp/footer.jsp" />

</jsp:root>