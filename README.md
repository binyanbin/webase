多门店业务系统开发框架

一直从事线下门店saas业务平台开发,而使用系统的全国各地都有,要满足各地客户的需求,可扩展性和可维护性应该优先考虑,所以框架设计的主要目是将业务代码做最大化拆分.所以它更合适复杂业务系统

![architecture](https://www.gitee.com/yanbin_yb/webase/raw/master/webase/architecture.png)

功能:

1. 把系统所有的功能定义为查询query和操作operate.query读数据进行展示,operate进行数据更新.
2. 把表与业务分离,并对业务对象通用业务进行定义.如标识,状态,门店,
3. operate的业务及数据更新操作，统一交给工作单元完成.
4. query查询时尽量用view进行数据聚合
4. 对基础业务数据如权限,操作,日志,令牌提供初始定义

流程:
![architecture](https://www.gitee.com/yanbin_yb/webase/raw/master/webase/workflow.png)

**基于springboot开始:**

```java

@Component
public class InitWe implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        WeContext.init(iContext, "com.bin.api");
    }
}
```