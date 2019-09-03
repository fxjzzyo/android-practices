# anroid-practices
This is a repository for android study practices. 

## 1. EventBus的使用及原理

### 代码实例
[EventBusTest](https://github.com/fxjzzyo/android-practices/tree/master/app)

### 参考文章：

> [Android EventBus 3.0 实例使用详解](https://www.cnblogs.com/upwgh/p/6394901.html)

> [Android EventBus实战 没听过你就out了](https://blog.csdn.net/lmj623565791/article/details/40794879)

> [Android EventBus源码解析 带你深入理解EventBus](https://blog.csdn.net/lmj623565791/article/details/40920453)

## 2. MVP设计模式

### 代码实例
[MVPTest](https://github.com/fxjzzyo/android-practices/tree/master/mvptest)

### 参考文章：

> [Android MVP设计模式实例详解](https://blog.csdn.net/u012721519/article/details/82977134)

## 3. recyclerview下拉刷新与上拉加载(支持网格布局)

### 代码实例
[recyclerviewrefreshload](https://github.com/fxjzzyo/android-practices/tree/master/recyclerviewrefreshload)


### 参考文章：
> 抱歉我忘了在哪看的了...>_<（支持网格布局是我新加的）

## 4. 一个GIF图片压缩算法的实现例子(Kotlin)



本来想用阿里的那个**simpleimage**库，可是**依赖包都下好了**，发现android平台无法引用java中的awt模块。无法用，放弃了。
只能另找它法。

最后找到方法：使用glide对gif图片编码，解码，从而实现压缩。工具类：ImageCompress.kt

  **需要注意的是：** 经本人测试，压缩限制的最大小不能太小，否则会很慢。
  
### 代码实例
[simpleimagetest](https://github.com/fxjzzyo/android-practices/tree/master/simpleimagetest)

  
  参考文章：
  > https://www.jianshu.com/p/1a0a58cf6fab
  
  > https://github.com/Nemocdz/ImageCompress-Android
  
 ## 5. AIDL练习
 
 来源于《Android开发艺术探索》第二章IPC机制的相关实践
 
 包含了
 - 一个基本的AIDL实现
 - messenger通信实例
 - content_provider通信实例
 - sorcket通信实例
 - binder连接池实例
 
 运行每个实例只需要将对应的activity设为启动activity即可
 
### 代码实例

 [aidl_test](https://github.com/fxjzzyo/android-practices/tree/master/aidl_test)
