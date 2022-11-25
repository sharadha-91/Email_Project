<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send an e-mail</title>
</head>
<body>
	<form action="EmailSendingServlet" method="post" enctype="multipart/form-data">
		<table border="0" width="35%" align="center">
			<caption><h2>Send New E-mail</h2></caption>
			<tr>
				<td width="50%">To Address</td>
				<td><input type="text" name="recipient" size="50"/></td>
			</tr>
			<tr>
				<td width="50%">CC</td>
				<td><input type="text" name="cc" size="50"/></td>
			</tr>
			<tr>
				<td width="50%">BCC</td>
				<td><input type="text" name="bcc" size="50"/></td>
			</tr>
			<tr>
				<td>Subject </td>
				<td><input type="text" name="subject" size="50"/></td>
			</tr>
			<tr>
				<td>Content/Body </td>
				<td><textarea rows="10" cols="39" name="content"></textarea> </td>
			</tr>
			<tr>
				<td>Attach File</td>
				<td><input type="file" name="file" size="50" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Send"/></td>
			</tr>
		</table>
		
	</form>
</body>
</html>