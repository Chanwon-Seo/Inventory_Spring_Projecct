
package com.example.im_project.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//이벤트를 기반으로 동작한다는 어노테이션
@EntityListeners(AuditingEntityListener.class)

//공통 매핑 정보가 필요할 때, 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 @MappedSuperclass를 사용한다.
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /**
     * 어느 엔티티에서는 등록자 수정자가 필요 없을 수도 있기 때문에
     * 입맛에 따라서 결정할 수 있도록
     * BaseTimeEntity라는 클래스를 따로 만들어 상속받는다.
     */
    /*
    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String lastModifiedBy;
     */
}
