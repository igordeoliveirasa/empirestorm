<head>
	<title>Feedback [new]</title>
</head>
<body>
    
<script>
  
  var facebookUserId = "";
  
  window.fbAsyncInit = function() {
  
        FB.init({
            appId      : '212988905522826', // App ID
            channelUrl : '//WWW.EMPIRESTORM.COM/channel.html', // Channel File
            status     : true, // check login status
            cookie     : true, // enable cookies to allow the server to access the session
            xfbml      : true  // parse XFBML
        });

  
        FB.api('/me', function(response2) {
          facebookUserId = response2.id;
          alert(facebookUserId)
        });
  };

  // Load the SDK asynchronously
  (function(d){
   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement('script'); js.id = id; js.async = true;
   js.src = "//connect.facebook.net/en_US/all.js";
   ref.parentNode.insertBefore(js, ref);
  }(document));

</script>    
	<%@include file="form.jsp"%>
</body>