package dev.captainsj.blog.comments;

import dev.captainsj.blog.articles.ArticlesEntity;
import dev.captainsj.blog.common.BaseEntity;
import dev.captainsj.blog.users.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UsersEntity author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    private ArticlesEntity article;
}
