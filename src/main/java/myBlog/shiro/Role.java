package myBlog.shiro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kj on 2016/10/26.
 */
@Entity
public class Role implements Serializable {
    @Id
    private Long rid;
    @ManyToMany
    @JoinTable(name="uid_rid", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns ={@JoinColumn(name = "uid") })
    private List<Userinfo> userinfoList;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="rid_pid",joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns ={@JoinColumn(name = "permissionid") } )
    private List<myBlog.shiro.Permission> permissionList;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public List<Userinfo> getUserinfoList() {
        return userinfoList;
    }

    public void setUserinfoList(List<Userinfo> userinfoList) {
        this.userinfoList = userinfoList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
