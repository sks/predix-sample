# Code Changes

To See the code changes that made this happen [here](https://github.com/sks/predix-sample/pull/1)
#Steps

* Create file ``` index.html ``` with some content ``` <h1>It Works</h1> ```
* Run the command ``` cf push APP_NAME -b staticfile_buildpack ```
* You may see ``` grep: Staticfile: No such file or directory ```, This is OK for now

* Go to http://APP_NAME.run.aws-usw02-pr.ice.predix.io/ on your browser and checkout yourself

* Curl output
	```
	curl "http://APP_NAME.run.aws-usw02-pr.ice.predix.io/" -v
	*   Trying **.***.246.12...
	* Connected to APP_NAME.run.aws-usw02-pr.ice.predix.io (**.***.246.12) port 80 (#0)
	> GET / HTTP/1.1
	> Host: my-static-content.run.aws-usw02-pr.ice.predix.io
	> User-Agent: curl/7.43.0
	> Accept: */*
	> 
	< HTTP/1.1 200 OK
	< Accept-Ranges: bytes
	< Content-Length: 19
	< Content-Type: text/html
	< Date: Sat, 21 Nov 2015 05:05:34 GMT
	< Etag: "564ffb34-13"
	< Last-Modified: Sat, 21 Nov 2015 05:03:48 GMT
	< Server: nginx
	< X-Cf-Requestid: b5d3d526-cb06-432c-714f-58e888e82caa
	< 
	* Connection #0 to host my-static-content.run.aws-usw02-pr.ice.predix.io left intact
	<h1> It Works </h1>
	```
Look at that ``` <h1> It Works </h1> ``` 

It just works