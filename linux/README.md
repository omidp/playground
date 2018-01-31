# Cheatsheet

 * replace all text recursively
 
 ```
	#replace omid with omidbiz
	egrep -lRZ 'omidbiz' . | xargs -0 -l sed -i -e 's/omidbiz/omid/g' 
 ```
 
 * yes | cp -rf /opt/build_scripts/standalone.xml /opt/jboss-eap-6.2/standalone/configuration/standalone.xml
 
 * Finding all files containing a text string on Linux
 
 ```
 grep -rnw '/home/omidp/richfaces_3.3.1/ui' -e "rich:clientId"
 ```

 * search in huge file and save result to a file

```
grep -C 20 -i 'insert into `charge_user`'  20150331_1124.sql >> output.txt 
```
 * delete .svn directory recursivly* 

```
find . -name .svn -exec rm -rf {} \;

find . -name .svn -exec ls {} \;

find . -type d -name .svn -exec rm -rf {} \;

```
 
* Which application is using port 8080

```
lsof -i :8080
fuser 1723/tcp
netstat -nlp
```
* count files in a directory

```
find accessFiles -type f | wc -l
```

* move files recursively

```
find . -name '*.orig' -exec mv "{}" /home/omidp/temp/merge/ \;
```

* remote desktop share linux folder with windows

```
rdesktop -r disk:linux=/home/omidp/windows IP
```
