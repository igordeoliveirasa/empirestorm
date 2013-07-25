<html>
<head>
	<title>VRaptor Scaffold Project</title>
</head>
<body>


<div id="welcomeMessage"></div>                                                                                                                                         

         <!-- Load the Facebook JavaScript SDK -->                                                                                                                               
         <div id="fb-root"></div>                                                                                                                                                
         <script>                                                                                                                                                                
           window.fbAsyncInit = function() {                                                                                                                                     
             FB.init({                                                                                                                                                           
               appId      : '212988905522826', // App ID                                                                                                                         
               status     : true, // check login status                                                                                                                          
               cookie     : true                                                                                                                                                 
             });                                                                                                                                                                 

             // Check if the current user is logged in                                                                                                                           
             // and has authorized the app                                                                                                                                       
             FB.getLoginStatus(function(response) {                                                                                                                              
               // Check the result of the user                                                                                                                                   
               if(response && response.status == 'connected') {                                                                                                                  
                 // The user is connected to Facebook                                                                                                                            
                 // and has authorized the app.                                                                                                                                  
                 // Now personalize the user experience                                                                                                                          

                 FB.api('/me', function(response) {                                                                                                                              
                   var message = document.getElementById('welcomeMessage');                                                                                                      
                   message.innerHTML = 'Hello, ' + response.first_name;                                                                                                          
                 });                                                                                                                                                             
               } else {                                                                                                                                                          
                 // The user has not authenticated your app,                                                                                                                     
                 // proceed with your normal (anonymous) flow.                                                                                                                   
               }                                                                                                                                                                 
             });                                                                                                                                                                 
           };                                                                                                                                                                    

           // Load the SDK Asynchronously
           (function(d, s, id) {
             var js, fjs = d.getElementsByTagName(s)[0];
             if (d.getElementById(id)) return;
             js = d.createElement(s); js.id = id;
             js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=113869198637480";
             fjs.parentNode.insertBefore(js, fjs);
           }(document, 'script', 'facebook-jssdk'));
         </script>
</body>
</html>