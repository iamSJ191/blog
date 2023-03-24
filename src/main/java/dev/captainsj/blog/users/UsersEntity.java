package dev.captainsj.blog.users;

import dev.captainsj.blog.articles.ArticlesEntity;
import dev.captainsj.blog.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Nullable
    @Setter @Column(name = "bio")
    private String bio;

    @Nullable
    @Setter @Column(name = "image")
    private String image;

    @ManyToMany(fetch = LAZY, mappedBy = "likers")
    private Set<ArticlesEntity> likedArticles;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<UsersEntity> followers;

    @ManyToMany(fetch = LAZY, mappedBy = "followers")
    private Set<UsersEntity> following;

    @OneToMany(mappedBy = "author", cascade = ALL, fetch = LAZY)
    private Set<ArticlesEntity> authoredArticles;
}
