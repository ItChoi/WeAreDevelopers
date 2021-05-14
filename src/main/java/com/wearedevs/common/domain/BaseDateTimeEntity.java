package com.wearedevs.common.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDateTimeEntity {
    @CreatedBy
    private Long createdUserId;
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private Long updatedUserId;
    @CreatedDate
    private LocalDateTime updatedDate;
}
