importUsers2OpenLM
==================

Import users CSV file into OpenLM Server.

The purpose of the software is to import new users or update users details from a CSV file to OpenLM server. The software works with OpenLM License Management software  - www.openlm.com

This is a Java software that uses the OpenLM API in order to upload/synchronize users between CSV file and the OpenLM server.

####How does it work:####
1. The programs reads the resources to use from a Java properties file and as arguments. 
2. The program reads all existing users from OpenLM Server using API. 
3. The program iterates over all the records in the CSV file. And do: 
   - add new user if it does not exist.
   - Ignore if it exists and there are no updates
   - Update if the user exists and there are updates
4. Report statistics - updated, added, exists.

####The program is using OpenLM API in order to perform the following actions:####
- read the existing list of user from OpenLM Server.
- add/update a user as needed.
- authenticate to OpenLM server, if applicable.

####Authentication: 
The program support OpenLM Server authentication, if applicable. It will use credentials as provided in the properties file. 

####Input CSV file format:####
The software expects to get a file in CSV format with the list of the user.
Every line in the file need to contain: 
Username (has to be unique)
first name
last name
Email
```
for exsample:
joe, joe, smith,joe@company.com
emily.fields, Emily, fields, emilyf@company.com
```

####Java properties file format:####
The properties file specify the following information 

|Information|tag|
|-----------|---|
|Authentication required|useAuthentication = true\false|
|OpenLM username - relevant only when authentication is required|username =|
|OpenLM password - relevant only when authentication is required|password =|
|OpenLM server URL|OpenLMServerURL =|
|The delimiter you used in the CSV file|delimiter =|

```
for example:
useAuthentication = true
username = adi
password = adi
OpenLMServerURL = http://<OpenLM Server>:7014/OpenLMServer
delimiter = ,
```

####Usage - how to run (Using Jar file):####
java -jar importUsers2OpenLM.jar <properties file> <users csv file>

####Next steps####
This is the initial version of the software, developed as an  in-house tool in order to periodically update our users. A better solution would be to use Active Directory synchronization but it is not currently supported in our organization.

####The next development steps:####
- Support additional fields in the CSV file.

