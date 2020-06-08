# trusteeship 配套使用
远程数据库定时备份：mysql -uUsername -pPassword -h 192.168.0.1 -e "show databases;" | grep -Ev "Database|information_schema|mysql|performance_schema" | xargs mysqldump -uUsername -pPassword -h 192.168.0.1 --column-statistics=0 --databases > /trusteeship/2020-03-31_15-19-00.sql

本地数据库定时备份：mysql -uUsername -pPassword -e "show databases;" | grep -Ev "Database|information_schema|mysql|performance_schema" | xargs mysqldump -uUsername -pPassword --column-statistics=0 --databases > /local_db/`date +%Y-%m-%d_%H-%M-%S`.sql

定时清除30天前的本地备份：find /local_db -mtime +30 -type f -name "*" -exec rm -rf {} \;


