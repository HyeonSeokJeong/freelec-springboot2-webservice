package com.jhs.book.springboot.domain.posts;

import com.jhs.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity Class
 * Entity Class에서는 절대 Setter 메소드를 만들지 않습니다. 메소드 추가로 대체
 */

@Getter // Lombok어노테이션 - 클래스내의 모든 필드 Getter 메소드 생성.
@NoArgsConstructor // Lombok어노테이션 - 기본 생성자 자동 추가. public Posts(){}와 같은 효과
@Entity // JPA어노테이션 - 테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다.(SalesManager.java -> sales_manager table)
public class Posts extends BaseTimeEntity {
    @Id // PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성규칙 나타냄. 스프링부트2.0에선 GenerationType.IDENTITY를 추가해야만 auto_increment됨.
    private Long id;

    @Column(length = 500, nullable = false) // 굳이 선언하지 않아도 이 클래스내에서는 Column임. 사용하는 이유는 디폴트 외에 추가 변경 옵션 때문. varchar(255) 기본값을 TEXT(500)으로 변경.
    private String title;

    @Column(columnDefinition =  "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
