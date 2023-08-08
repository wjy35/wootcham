package com.ssafy.wcc.domain.member.db.entity;

import com.ssafy.wcc.common.db.BaseEntity;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import com.ssafy.wcc.domain.record.db.entity.Record;
import com.ssafy.wcc.domain.report.db.entity.Report;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "nickname"})})
@RequiredArgsConstructor
public class Member extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    private Integer point;
    private Integer money;
    private Integer admin;

    @Column(name = "suspension_day")
    private LocalDate suspensionDay;

    @Column(name = "current_login")
    private LocalDate currentLogin;


    @OneToMany(mappedBy = "member")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)

    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberItem> memberItems = new ArrayList<>();

//    @Builder
//    public Member(long id, String email, String password, String nickname, int point, int money, int admin, int suspensionDay, LocalDate currentLogin) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.point = point;
//        this.money = money;
//        this.admin = admin;
//        this.suspensionDay = suspensionDay;
//        this.currentLogin = currentLogin;
//    }

    @Builder
    public Member(Long id, String email, String password, String nickname, Integer point, Integer money, Integer admin, LocalDate suspensionDay, LocalDate currentLogin, List<Report> reports, List<Record> records, List<Notice> notices, List<MemberItem> memberItems) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.admin = admin;
        this.suspensionDay = suspensionDay;
        this.currentLogin = currentLogin;
        this.reports = reports;
        this.records = records;
        this.notices = notices;
        this.memberItems = memberItems;
    }

    public void updateCurrentLogin() {
        this.currentLogin = LocalDate.now();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
