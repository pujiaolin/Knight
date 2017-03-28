# Knight
The scheduler with UI base on quartz-scheduler（spring boot 1.4.3 + angular 2.4 + quartz-scheduler 2.2.1）
# 快速开始
1.执行 knight-service项目中resources的db文件夹下的sql语句，这里是使用mysql。  

2.在 knight-service项目中 application.properties配置数据库相关。  

3.执行 mvn package。  

4.运行knight-service项目，访问http://localhost:8088/index.html  

当然你可以同时运行knight-web(ng server | npm start) 项目来访问http://localhost:4200  


# 项目功能
1.添加任务，需要自己编写任务 继承 QuartzJobBean，根据准确目录进行添加，只是实现了cron表达式方式。  

2.任务列表，查看所有任务，可删除。  

3.添加任务监听，这里主要是提供两个关联任务的执行，监听到第一个任务执行，立马执行第二个任务。这时第一个任务必须是正在运行的任务。  

4.任务监听列表，查看所有监听，可以删除。  

5.不管是任务还是监听都可以SchedulerConfig类中配置，这样项目每次启动都会加载执行。  
