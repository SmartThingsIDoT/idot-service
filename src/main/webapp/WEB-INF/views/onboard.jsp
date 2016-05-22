<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Integratingfactor.com</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- override CSS -->
<link href="/css/signin.css" rel="stylesheet">
<!-- favicons for the site -->
<link rel="apple-touch-icon" sizes="57x57" href="/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon/favicon-16x16.png">
<link rel="manifest" href="/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
</head>
<body>
    <div class="container">
      <!-- Example row of columns -->
	      <form class="form-signin" action="${actionUrl }" method="POST">
	        <div class="text-center">
	        <!-- <label><span class="integratingfactor-logo"></span></label> -->
	        <h4 class="form-signin-heading">Register a smart device</h4>
			  <c:if test="${errorMessage != null }">
			    <div class="alert alert-danger" role="alert">${errorMessage}</div>
		        <div class="form-group">
		          <label class="lead">Forgot username or password?</label>
		    	  <a class="btn btn-warning btn-block" role="button" href="${resetUrl }">Help with sign in</a>
  	        	</div>
			  </c:if>
	        </div>
	        <div class="form-group">
		        <input type="text" name="clientId" class="form-control" placeholder="client id" required autofocus>
	        </div>
	        <div class="form-group">
		        <%-- <div class="text-right"><label for="${password }">(<a href="/pwreset">Forgot password?</a>)</label></div> --%>
		        <input type="text" name="clientSecret" class="form-control" placeholder="client secret" required>
	        </div>
	        <div class="form-group">
	        <c:if test="${redirect != null }">
		        <input type="hidden" name="redirect" value="${redirect }"/>
	        </c:if>
	        <input type="hidden" name="type" value="smartthings"/>
	        <button class="btn btn-success btn-block" type="submit">Register Device</button>
	        </div>
	    	<a class="btn btn-primary btn-block" role="button" href="${redirect }">Go back</a>
	      </form>
  		<!-- FOOTER -->
  		<hr>
  		<div class="form-signin">
		<footer>
			<p class="pull-center">
			&copy; 2016 Integratingfactor.com
			</p>
		</footer>
		</div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>