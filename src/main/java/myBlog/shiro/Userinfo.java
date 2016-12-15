package myBlog.shiro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kj on 2016/10/26.
 */
@Entity
public class Userinfo implements Serializable {
    @Id
    @GeneratedValue
    private long uid;
    private String uname;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="uid_rid", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleid") })
    private List<Role> roleList;



}
