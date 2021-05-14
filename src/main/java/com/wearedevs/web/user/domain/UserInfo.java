package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "csh_user_info")
public class UserInfo extends BaseDateTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 사용자 정보 고유 번호
     */
    @Id
    @ApiModelProperty("사용자 정보 고유 번호")
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * (사용자 고유 번호)
     */
    @ApiModelProperty("(사용자 고유 번호)")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    /**
     * 소개 내용
     */
    @ApiModelProperty("소개 내용")
    @Column(name = "INTRODUCE")
    private String introduce;

    /**
     * 핸드폰 번호
     */
    @ApiModelProperty("핸드폰 번호")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

}
