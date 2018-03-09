# file_upload
Project name: file_upload

Description: 
The main purpose of this project is to manage file(upload and download) and corresponding metadata.


Installation:
This project is a Maven project created by SpringBoot. The user should have Maven support for this project.
The project also needs a directory for file upload. By default the directory for the uploaded file is "/Users/cs/Documents/upload/".
User of this project could change upload directory in application.properties file

Also this project provides local H2 in-memory database, user could access database console in browser by accessing "/h2console"
The JDBC Url should be "jdbc:h2:mem:testdb" and username is "sa" with no password


APIs:
This projects provides several Restful APIs for file management purpose
"/meta" is the root directory for file management purpose

  "/meta/file" with post method would upload file and metadata. The file would be saved in the given directory discussed in "Installation" part
  and metadata would be saved in H2 local memory database. 
  User should be aware that the file name should not contains more than one "." or there will be an IllegalFileNameException throwed
  
  "/meta/file" with get method would fetch file with given file name. The file name should be included in header of request. If there are 
  multiple file with same given name, there will be multiple files returned. 
  
  "meta/file/{id}" would return one file metadata. If the id does not exist, there would be an exception thrown.
  
  "/download/{id}" would download file with corresponding id. If the id does not exist, there would be an exception thrown.


