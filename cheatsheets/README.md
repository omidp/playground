+ mvn install:install-file -Dfile=/root/barbecue-1.1.jar -DgroupId=net.sourceforge -DartifactId=barbecue -Dversion=1.1 -Dpackaging=jar
+ pg_ctl -D /var/lib/pgsql/data/ -l logfile start


* ffmpeg from screen record to scaling video ogg

```

ffmpeg -video_size 1368x768 -framerate 30 -f x11grab -i :0.0 -c:v libx264 -qp 0 -preset slow capture.mkv

ffmpeg -i capture.mkv -vf scale=800:-1 output_800.mkv

ffmpeg -i output_800.mkv -codec:v libtheora -qscale:v 10 -codec:a libvorbis -qscale:a 7 output.ogv

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
