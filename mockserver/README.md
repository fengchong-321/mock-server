# Mock服务

## 功能清单

### 策略匹配

​	请求相同接口，不同两个人要求返回的结果不一样

- 以create_account接口为例，参数包含accountId，accountName

- 参数匹配

  accountId = 123， 返回成功

  accountId = 456， 返回失败

- ip匹配

  ip=123.123.123.1， 返回成功

  ip=123.123.123.2， 返回失败

- 权重匹配（自行配置weight）

  多个返回报文，为每个报文配置weight，weight大的做返回

  

  策略配置的样式

  ![image-20210507150914544](D:\Users\chongfeng\Desktop\test\demos\mockserver\img\image-20210507150914544.png)

  

### 数据处理

​	如果返回数据不是固定写死的，需要做处理

- 时间戳处理，返回报文中自动生成时间戳
- 随机数随机字符处理，比如要求生成一个随机8位数字，或者随机24位的字符（字母+数字）
- hook参数处理，比如请求的时候，请求参数携带了一个requestid，然后这个requestid本身是个变化的，也是随机的，返回的时候要把这个requestId带回去



​	数据处理配置样式：{radNum:12} 表示 12位随机数 ， {radId:24}表示24位随机字符串

![image-20210507151201361](D:\Users\chongfeng\Desktop\test\demos\mockserver\img\image-20210507151201361.png)

### 回调

​	请求mock后，mock做了返回，但同时会按照配置要求给它去完成某些能力

1. 调http接口请求
2. 调某个mq（未完成）
3. 调公司内部的RPC（soa2）（未完成）
4. 写入某个db（未完成） 



​	callback的配置：url是请求，params是参数

![image-20210507151344947](D:\Users\chongfeng\Desktop\test\demos\mockserver\img\image-20210507151344947.png)

### 透传请求

​	比如一批请求，其中id=123的走mock服务，id=456的走真实服务，拿这个请求完成真实服务请求的调用，再返回给调用端



​	透传配置：请求报文用调用mock服务的，请求地址设置在penetrate：里

![image-20210507151526421](D:\Users\chongfeng\Desktop\test\demos\mockserver\img\image-20210507151526421.png)

### 设置等待	

​	场景：

- 模拟超时

- 模拟真实的服务处理（比如压测时，某些外部请求需要mock，你不可能立即给它返回，也不能返回一个固定值，你需要通过分析生产环境实际处理时间，进行分段处理，再返回一个区间的概率，比0~500ms取随机数时间返回，总流量占比70%）   

  设置等待时间配置： value是值，unit是单位（包括：MILLISECONDS 毫秒，SECONDS 秒）

![image-20210507151658055](D:\Users\chongfeng\Desktop\test\demos\mockserver\img\image-20210507151658055.png)