+ mvn install:install-file -Dfile=/root/barbecue-1.1.jar -DgroupId=net.sourceforge -DartifactId=barbecue -Dversion=1.1 -Dpackaging=jar
+ pg_ctl -D /var/lib/pgsql/data/ -l logfile start


* ffmpeg screen record

```
ffmpeg -video_size 1368x768 -framerate 30 -f x11grab -i :0.0 -c:v libx264 -qp 0 -preset ultrafast capture.mkv
```

* ffmpeg video scale

```
ffmpeg -i input.jpg -vf scale=w=320:h=240:force_original_aspect_ratio=decrease output_320.png
```
