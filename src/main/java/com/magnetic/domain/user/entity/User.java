package com.magnetic.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magnetic.domain.auth.entity.Token;
import com.magnetic.domain.calendar.entity.Todo;
import com.magnetic.domain.crew.entity.Like;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.entity.enums.Role;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String profileImg;
    private String email;
    private String password;
    private String nickname;
    private String region;
    private Role role;

    @Column(name = "plant_token", columnDefinition = "BIGINT default 3")
    private Long plantToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCrew> userCrews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserChallenge> userChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlant> userPlantList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    // Token
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;

    //Like
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void updateProfile(UserRequestDto.Profile request) {
        nickname = request.nickname();
        region = request.region();
    }

    public void receiveToken() {
        plantToken++;
    }

    public void pay() {
        plantToken--;
    }
}
