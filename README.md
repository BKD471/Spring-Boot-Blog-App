<h1>Directions to use</h1>

<p>This applications mimicks a typical blog application where user sign up and sign In and then uses the application</p>

<p>In this app, i have developed Rest apis that supports all CRUD functinalities that includes Features like<p>

<ol>
<li>Authenticating through JWT (JSON web token)</li>
<li>Creating Post</li>
<li>fetching Post By id</li>
<li>Fetching all posts</li>
<li>Updating Post</li>
<li>Deleting post</li>  
<li>Commenting for a post</li>
<li>Updating the comment</li>
<li>fetching all comments</li>
</ol>

<p>Resources used in this project</p>
<ul><li>Post</li><li>Categories</li><li>Comments</li>
</ul>

<br>
<p>Note Delete operations on Any Resource can only be performed by admin.
You will need admin credentials for it.
Note: Only admin can do any operations on Category Resources.</p>
<br>

<b>For better experience use postman to test the apis and not browser.</b>

<h2>All Endpoints</h2>

<h4>First Authenticate</h4>

<ul>
<li>Sign UP:
  <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signup">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signup</a></li>
  <textarea rows="500" col="500" maxlength="1000">
body-->{ "name":"Yr name",
         "email":"Yr email",
        "username":"yr username",
         "password":"yr password"
        }
</textarea>
<p>It will only display the confirmation. Next sign in</p>
<li>Sign In: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signin">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signin</a></li>
 <textarea rows="10" col="500" maxlength="1000">
body-->{
    "name":"Yr name",
"email":"Yr email",
   "username":"yr username",
  "password":"yr password"
}
</textarea>
<p>If successful, it will generate a token,  use that bearer token in postman authentication for every POST,PUT,DEL request</p>
</ul>



