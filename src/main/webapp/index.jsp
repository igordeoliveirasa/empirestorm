<html>
<head>
	<title>VRaptor Scaffold Project</title>
</head>
<body>
<div id="fb-root"></div>
<script type="text/javascript">
  // You probably don't want to use globals, but this is just example code
  var fbAppId = '212988905522826';

  // Additional JS functions here
  window.fbAsyncInit = function() {
    FB.init({
      appId      : fbAppId,        // App ID
      status     : true,           // check login status
      cookie     : true,           // enable cookies to allow the server to access the session
      xfbml      : true            // parse page for xfbml or html5 social plugins like login button below
    });

    // Put additional init code here
  };

  // Load the SDK Asynchronously
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/all.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));

</script>    
    
    
	It works!!
</body>
</html>