package com.action.file.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: MybatisPlus配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2019/11/18
 */
@Configuration
@MapperScan("com.action.file.**.mapper*")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
         /*目前已有的功能；
            自动分页: PaginationInnerInterceptor
            多租户: TenantLineInnerInterceptor
            动态表名: DynamicTableNameInnerInterceptor
            乐观锁: OptimisticLockerInnerInterceptor
            sql 性能规范: IllegalSQLInnerInterceptor
            防止全表更新与删除: BlockAttackInnerInterceptor*/

        // 防止全表更新与删除：攻击 SQL 阻断解析器、加入解析链，防止恶意进行 delete update 全表操作
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // 自动分页，并指定数据库类型
        // 支持的数据库很多：
        // mysql，oracle，db2，h2，hsql，sqlite，postgresql，sqlserver，Phoenix，Gauss
        // clickhouse，Sybase，OceanBase，Firebird，cubrid，goldilocks，csiidb
        // 达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神通数据库，瀚高数据库
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
