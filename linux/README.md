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
