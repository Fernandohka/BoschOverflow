package com.duo.duo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserSpace {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    /*
     ? NÍVEIS DE PERMISSÃO - REPRESENTADOS POR UM INTEIRO
     * 1 - USUÁRIO SEM PERMISSÃO ALGUMA
     * 2 - USUÁRIO MEMBRO DE UM SPACE
     * 3 - ADMINISTRADOR
     */

    private Integer permissionLevel;

    @ManyToOne
    @JoinColumn(name="spaceId")
    private Space space;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;


    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
