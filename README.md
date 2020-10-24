# locus assignment
 A role based auth system. 
 
 This is a command line interface assignment. When you will run this, there would be several options to chose from.


## Installation

All you need is a Java 1.8+ installed. Once Java environment is ready, run below command in project root directory.

```bash
cd src\
javac com\locus\Main.java
```
This will build all the classfiles,


```bash
java com.locus.Main
```
 Now, run the Main class file that was generated, and you will see below mentioned commands in console
```bash
Enter your choice:
Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9
```

Based on your need make a choice and follow the instructions in command line.

##Usage

###Create new user
```bash
Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
1
Enter user's name:
Chintan
User Chintan added successfully, User id 0ba508c6-8608-43c8-887c-598678efd12a

```

###Add new role
```bash

Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
3
Enter role name:
Admin
Enter action types (Separate by comma if more than one):
read,write,delete
Role Admin added successfully , roleId ef515ef1-eb64-41d9-ad51-5e020010490f


```

###Assign role to user
```bash

Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
5
Enter role id:
ef515ef1-eb64-41d9-ad51-5e020010490f
Enter user id:
0ba508c6-8608-43c8-887c-598678efd12a
Role Admin assigned to Chintan
```

###Remove role from user
```bash

Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
6
Enter role id to remove from user:
ef515ef1-eb64-41d9-ad51-5e020010490f
Enter userId:
0ba508c6-8608-43c8-887c-598678efd12a
Role ef515ef1-eb64-41d9-ad51-5e020010490f removed from Chintan

```

###Create Resource
```bash

Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
7
Enter resource name:
user-info
Available action types:
[read, write, delete]
Enter action type for resource (Separate by comma if more than 1):
read,write
Resource user-info added successfully , resourceId 9facd4b7-dbf8-4d65-b304-a03628f150d0

```
###Check if user has permission to access resource
```bash

Add new user: 1
Get user: 2
Add new role: 3
Get available roles: 4
Assign role to user: 5
Remove role from user: 6
Create Resource: 7
Get Resource: 8
Check if user has permission to access resource: 9

Enter your choice:
9
Enter resource id:
9facd4b7-dbf8-4d65-b304-a03628f150d0
Enter userId:
0ba508c6-8608-43c8-887c-598678efd12a
User Chintan is authorized to access the resource user-info

```

##Assumption and Limitations

- All data is stored in-memory, so once you close the program data will be lost.
- No data need to updated after creation. I have not considered scenario when Role or Resource info is updated.

######For questions contact cpandya23@gmail.com