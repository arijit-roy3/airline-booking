package com.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_role"
)
public class Role {
    @Id
    @SequenceGenerator(
            name="role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long roleId;
    @Column(nullable = false)
    private String roleName;

    public Role(String role_user) {
        this.roleName=role_user;
    }
}
