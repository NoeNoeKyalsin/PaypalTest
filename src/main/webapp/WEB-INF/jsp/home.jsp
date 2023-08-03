<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Check Out</title>
	    <style type="text/css">
	        table { border: 0; }
	        table td { padding: 10px; }
	    </style>
	</head>
	<body>
		<div align="center">
		    <h1>Check Out</h1>
		    <br/>
		    <form action="/pay" method="post">
		    	<table>
		    		<tr>
		    			<td>
		    				<label for="price">Total</label>
		    				<input type="text" id="price" name="price" value="10">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<label for="currency">Currency</label>
		    				<input type="text" id="currency" name="currency" placeholder="Enter Currency">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<label for="method">Payment Method</label>
		    				<input type="text" id="method" name="method" placeholder="Payment Method">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<label for="intent">Intent</label>
		    				<input type="text" id="intent" name="intent" value="sale">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<label for="description">Payment Description</label>
		    				<input type="text" id="description" name="description" placeholder="Payment Description">
		    			</td>
		    		</tr>
		    	</table>
		    	<input type="submit" value="Continue to Check Out">
		    </form>
		</div>
	</body>
</html>