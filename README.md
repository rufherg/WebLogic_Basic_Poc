#  WebLogic Basic Poc & Exp

## 前言

第一次接触weblogic，一开始不太懂怎么去试poc，因此跟着各位师傅的文章去学习。

从A-Team[文章](https://paper.seebug.org/1012/#t3)学习到测试方法，即构造t3协议发包，但是本着扩展的原则，示例中的脚本是不便于拓展的。因此，参照脚本进行改写参构美化，并且打算在不断学习中拓展工具。虽然github已经有很多相关weblogic的工具，但本人并非以重复造轮子的心思去写这个工具，而是想要在不断学习中得到自己新的想法新的思路，并以此不断完善工具，打造一个一体化的WebLogic检测利用工具。

## 用法

```shell
usage: weblogic_poc.py [-h] [-u URL] [-p PORT] [-f FILE]

WebLogic POC & EXP Basic Script

optional arguments:
  -h, --help            show this help message and exit
  -u URL, --url URL     WebLogic服务器ip
  -p PORT, --port PORT  WebLogic服务器端口(默认7001)
  -f FILE, --file FILE  payload序列化数据文件
  
$python weblogic_poc.py -u 127.0.0.1 -p 7001 -f test.ser
```

## TODO

- 研究T3协议各个版本的不同，增加自动匹配版本功能
- 添加各版本各利用链payload
- 重构工具，添加自动生成payload功能
- 不断思考，只为简便...

## 注意事项

- ```-f```参数为序列化数据文件，需自行序列化好测试用payload到指定文件
- 本工具仅供学习使用，禁止用于非法行为，否则后果自负

