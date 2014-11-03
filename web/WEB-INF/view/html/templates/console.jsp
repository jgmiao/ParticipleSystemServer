<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>
	<c:if test="title!=null and title != ''"> - ${title}</c:if>
</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="expires" content="0" />
<link rel="stylesheet" href="support/styles/bootstrap.min.css">
<link rel="stylesheet" href="support/styles/bootstrap-theme.min.css">
<tiles:useAttribute name="styles" classname="java.util.List" />
</head>
<body>
	<tiles:insertAttribute name="body" />
	<script type="text/javascript" src="support/scripts/lib/html5.js"></script>
	<script type="text/javascript" src="support/scripts/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="support/scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="support/scripts/core.js"></script>
	<tiles:insertAttribute name="scripts" defaultValue="" />
</body>
</html>