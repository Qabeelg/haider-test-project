package com.azu.demohaider.user.securirty.token;


import com.azu.demohaider.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token{

    @Id
    @GeneratedValue
    private Long tokenId;
    private String token;
    private boolean isExpire;
    private boolean isRevoke;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_user_id")
    private User users;

    public String getToken() {
        return token;
    };

    public void setToken(String token) {
        this.token = token;
    };


}