+ mvn localnstall 

```
mvn install:install-file -Dfile=/root/barbecue-1.1.jar -DgroupId=net.sourceforge -DartifactId=barbecue -Dversion=1.1 -Dpackaging=jar
```

+ start postgres 

```
pg_ctl -D /var/lib/pgsql/data/ -l logfile start
```

* ffmpeg record desktop

```
ffmpeg -video_size 1600x900 -framerate 25 -f x11grab -i :0.0+0,0 -f pulse -ac 2 -i default capture.mkv
```


* ffmpeg convert mkv to mp4

```
#!/bin/bash
VIDEOS=/opt/video/springwebflow/
find "$VIDEOS" -name '*.mkv' -exec sh -c 'ffmpeg -y -i "$0" -c:v libx264 -preset slow -crf 22 -pix_fmt yuv420p -c:a aac -b:a 128k "${0%%.mkv}.mp4"' {} \;
exit;
```

* ffmpeg convert mp4 to webm

```
#!/bin/bash
VIDEOS=/opt/video/springwebflow/
find "$VIDEOS" -name '*.mp4' -exec sh -c 'ffmpeg -i "$0" -c:v libvpx -crf 10 -b:v 1M -c:a libvorbis "${0%%.mp4}.webm"' {} \;
exit;
```

* ffmpeg video duration

```
ffmpeg -i file.mp4 2>&1 | grep Duration | awk '{print $2}' | tr -d ,
```

Linux

```
java -cp client-1.0-SNAPSHOT.jar:lib/* TestClient.App
```

Windows

```
java -cp client-1.0-SNAPSHOT.jar;lib/* TestClient.App
```

```
<build>
		<finalName>persian</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<filters>
								<filter>
									<artifact>*:*</artifact>
									<excludes>
										<exclude>META-INF/*.SF</exclude>
										<exclude>META-INF/*.DSA</exclude>
										<exclude>META-INF/*.RSA</exclude>
									</excludes>
								</filter>
							</filters>
						</configuration>
					</execution>
				</executions>
				<configuration>
					<finalName>persian-${version}</finalName>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				<configuration>
					<archive>
						<manifest>
							<addClasspath>false</addClasspath>
							<mainClass>com.omidbiz.persianutils.Main</mainClass>
						</manifest>
					</archive>
				</configuration>
			</plugin>

		</plugins>
	</build>
```
