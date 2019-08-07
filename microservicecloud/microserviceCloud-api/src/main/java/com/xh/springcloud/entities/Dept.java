package com.xh.springcloud.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Dept implements Serializable {
    private  Long deptno;  // 主键
    private String dname; // 部门名称
    private String db_source;  // 来自哪个数据库，因为一个微服务可以对应一个数据库
}
