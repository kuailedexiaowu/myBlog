package myBlog.shiro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kj on 2016/10/26.
 */
@Entity
public class Permission implements Serializable {
    @Id
    private long pid;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @ManyToMany
    @JoinTable(name="rid_pid",joinColumns = { @JoinColumn(name = "permissionid") }, inverseJoinColumns ={@JoinColumn(name = "rid") } )
    private List<Role> roleList;
    private String permission;



}
