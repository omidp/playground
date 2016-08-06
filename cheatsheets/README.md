+ mvn install:install-file -Dfile=/root/barbecue-1.1.jar -DgroupId=net.sourceforge -DartifactId=barbecue -Dversion=1.1 -Dpackaging=jar
+ pg_ctl -D /var/lib/pgsql/data/ -l logfile start


* ffmpeg from screen record to scaling video ogg

```

ffmpeg -video_size 1368x768 -framerate 30 -f x11grab -i :0.0 -c:v libx264 -qp 0 -preset slow capture.mkv

ffmpeg -i capture.mkv -vf scale=800:-1 output_800.mkv

ffmpeg -i output_800.mkv -codec:v libtheora -qscale:v 10 -codec:a libvorbis -qscale:a 7 output.ogv

```
