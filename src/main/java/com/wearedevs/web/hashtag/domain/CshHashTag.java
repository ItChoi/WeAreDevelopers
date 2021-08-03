package com.wearedevs.web.hashtag.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.hashtag.HashTagType;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.web.user.domain.CshUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CSH_HASH_TAG")
public class CshHashTag extends BaseDateTimeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("사용자 정보 고유 번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 고유 번호")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private CshUser cshUser;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("해시태그 타입")
    @Column(name = "Type")
    private HashTagType type;

    @ApiModelProperty("해시태그 내용")
    @Column(name = "CONTENT")
    private String content;





}
