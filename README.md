###项目初始化步骤和介绍：
1. 安装Mysql数据库，然后使用other/db/init_db.sql建立数据库和dcms用户
2. 在eclipse中导入代码格式化配置code-style/eclipse-java-google-style.xml
3. 配置maven私服，将other/maven/settings.xml复制到$MAVEN_HOME/conf下
4. 将项目导入到eclipse中
5. 从git@code.aliyun.com:ljt/dcms_static.git clone前端页面项目
6. 从\\192.168.13.3\SHARE\软件\开发相关\nginx-1.8.1 下载nginx，修改conf/nginx.conf中“ root   D:\DEV\ljt\static;”指向前端页面项目
7. 接口工具地址：http://127.0.0.1:{项目端口号}/{项目路径}/swagger-ui.html

