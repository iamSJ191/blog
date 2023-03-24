package dev.captainsj.blog.articles;

import dev.captainsj.blog.comments.CommentsEntity;
import dev.captainsj.blog.common.BaseEntity;
import dev.captainsj.blog.users.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    private String slug;

    @Column(name = "sub_title", length = 150)
    private String subTitle;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UsersEntity author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UsersEntity> likers;

    @OneToMany(mappedBy = "article", cascade = ALL, fetch = LAZY)
    private Set<CommentsEntity> comments;


}
