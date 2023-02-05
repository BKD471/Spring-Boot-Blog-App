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
Request Method:POST <br> 
Request Body in JSON:
       <br>body: { <br>" name":" &lt Yr name &gt ",<br>
         "email":" &lt Yr email &gt ",<br>
        "username":" &lt yr username &gt ",<br>
         "password":" &lt yr password &gt "<br>
}
<p>It will only display the confirmation. Next sign in</p>
<li>Sign In: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signin">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/auth/signin</a></li>
Request Method:POST<br> 
Request Body in JSON:<br>
body: {<br>
    "usernameOrEmail":" &lt yr email u gave while sign up &gt",<br>
     "password":" &lt yr password that you gave while sign up &gt"<br>
}
<p>If successful, it will generate a token,  use that bearer token in postman authentication for every POST,PUT,DEL requests</p>
</ul>
<br>
<h4>Categories</h4>

<p>Categories/topic of post are pre created, you just need to use then in yr request body.
Only a admin can create/update/delete categories</p>
<br>
<h6>All Categories with their category Id</h6>
<ol>
<li> DSA (id:1)</li>
<li> Backend Development  (id:2)</li>
<li> Frontend Development (id:3)</li>
<li> Microservices        (id:4)</li>
<li> System Design        (id:5)</li>
<li> Deep Learning        (id:6)</li>
<li> Devops               (id:7)</li>
<li> Data Engineering (id:8)</li>
<li> AWS (id:9)</li>
</ol>
<br>

<h4>All Post resource services Endpoints</h4>

<ul>
    <li> create post: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts</a></li>
TOKEN REQUIRES: YES  <br>    
Request Method:POST<br>
Request Body in JSON:
       <br>{ "title": " &lt title of post &gt ",<br>
     "description":" &lt description of post &gt ",<br>
     "content":" &lt content of post &gt ",<br>
    "categoryId": &lt category id from 1 to 9 given above to which yr post belong &gt <br>
}
<li>Get Post By Id: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/2?version=1">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt YOUR POST_ID &gt; </b>?version=1</a><br>Request Method:GET <br>TOKEN REQUIRES: NO</li><br>
<li>Get All Posts: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts?pageNo=0&pageSize=10&sortBy=id&sortDir=asc">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts?pageNo=0&pageSize=10&sortBy=id&sortDir=asc</a><br> Request Method:GET<br>TOKEN REQUIRES: NO</li>
<p>Sort all the posts by any attributes (default:id) and in any direction (default:ascending)</p>

<li>Update POST By Id: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/3">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt YOUR_POST_ID &gt;</b></a><br>Request Method: PUT<br>TOKEN REQUIRES: YES</li><br>
Request Body:<br>
{
            "title": "&lt updated title of post &gt",<br>
            "description": " &lt updated description of post &gt",<br>
            "content": "&lt  updated content of post &gt",<br>
            "categoryId": &lt updated category id &gt
}<br>
<li>Delete POST By Id: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/111">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt YOUR_POST_ID &gt</b></a><br>Request method: DELETE<br>TOKEN REQUIRES: ADMIN</li>
Only admin can perform this
</ul>
<br>

<h4>Comments resource  services endpoints</h4>

<ul>
<li>Create Comments for a Post By Id: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/1/comments">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b> &lt YOUR_POST_ID &gt; </b>/comments</a></li><br>
Request Method: POST<br>
Request Body in json<br>
TOKEN REQUIRES: YES<br>
{<br>
    "name":" &lt name of commenter &gt",<br>
    "email":" &lt email id &gt",<br>
    "body":" &lt comments left by him/her &gt"<br>
}

<li>Get All Comments for a post By Post Id:<a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/2/comments">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt POST_ID &gt;</b>/comments</a></li>
Request Method:GET<br>TOKEN REQUIRES: NO<br><br>
<li>Get a particular comment by comment id under a post by post id:<a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/1/comments/2">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt POST_ID &gt</b>/comments/<b>&lt COMMENT_ID &gt</b></a></li>
Request Method:GET<br>TOKEN REQUIRES: NO<br><br>
<li>Update a Comment By Id: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/2/comments/5">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt POST_ID &gt</b>/comments/<b>&lt COMMENT_ID &gt</b></a></li>
Request Method:PUT <br>
TOKEN REQUIRES: YES<br>
Rquest Body in JSON: <br>
{

                    "name": "name of commenter",
                    "email": "user20@gmail.com",
                    "body": "<updated comment>"
}

<li>Delete Comments By Id:<a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/20/comments/8">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt POST_ID &gt</b>/comments/<b>&lt COMMENT_ID &gt</b></a></li>
Request Method: DELETE<br>
TOKEN REQUIRES: YES<br>
</ul>

<br>


<h4>Categories</h4>
<p>Unfortunately you need admin access to create/delete/update categories</p>

<ul>
<li>Create Categories: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories</a></li>
Request Method:POST<br>
TOKEN REQUIRES: ADMIN<br>
Request Body in json<br>
{
    
    "name": "<topic>",
    "description": "<description of topic>"<br>
}<br>
<li>Get category by categoryId: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/4">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/<b>&lt CATEGORY_ID &gt</b></a> </li>
Request Mtehod:GET<br>TOKEN REQUIRES: NO<br><br>
<li>Get to know the category of a post by postId: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/4/categories">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/<b>&lt POST_ID &gt</b>/categories</a></li>
Request Method: GET<br>TOKEN REQUIRES: NO<br>
<br>
<li>Get all posts for a category : <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/categories/9/allposts">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/posts/categories/<b>&lt CATEGORY_ID &gt</b>/allposts</a></li>
Request Method:GET<br>TOKEN REQUIRES: NO<br><br>
<li>Get all categories: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories</a></li>
Request Method: GET<br>
TOKEN REQUIRES: NO<br><br>
<li>Update category NA for non admin: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/2">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/<b>&lt CATEGORY_ID &gt</b></a></li>
Request Method: PUT<br>
TOKEN REQUIRES: ADMIN<br>
Request Body:<br>
{<br>
    "id": &lt Category_id &gt,<br>
    "name": "&lt updated category name &gt",<br>
    "description": "&lt updated category description &gt"<br>
}
<li>Delete category NA for non admin: <a href="http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/555">http://blogrestapis-env.eba-mubssczk.ap-south-1.elasticbeanstalk.com/api/v1/categories/<b>&lt CATEGORY_ID &gt</b></a></li>
Request Method:DELETE<br>
TOKEN REQUIRES: ADMIN<br>

</ul>

<br>
<p>Sharing my admin credentials.<b>PLEASE DONT MISUSE</b></p>

{ "usernameOrEmail":"admin",
"password":"admin@gmail.com"
}

signin with it and YOU are ADMIN :), it will give you bearer token use it in every PUT/POST/DELETE requests auth token in postman.
