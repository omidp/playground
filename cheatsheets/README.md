+ mvn install:install-file -Dfile=/root/barbecue-1.1.jar -DgroupId=net.sourceforge -DartifactId=barbecue -Dversion=1.1 -Dpackaging=jar
+ pg_ctl -D /var/lib/pgsql/data/ -l logfile start
