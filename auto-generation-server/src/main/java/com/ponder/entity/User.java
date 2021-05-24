package com.ponder.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ponder Yao
 * @since 2021-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    @TableField("registerTime")
    private LocalDate registerTime;

    private Boolean gender;

    private String avatar;

    private String major;

    @TableField("phoneNumber")
    private String phoneNumber;


}
