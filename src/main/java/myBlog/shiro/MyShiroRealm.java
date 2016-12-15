package myBlog.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by kj on 2016/10/26.
 */
public class MyShiroRealm  extends AuthorizingRealm{
    @Resource
    private UserinfoServiceImpl userinfoServiceImpl;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        String uname=(String)authenticationToken.getPrincipal();
        System.out.println(uname);
        Userinfo userinfo=userinfoServiceImpl.findByUname(uname);
        if(null==userinfo){
        return null;}
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userinfo.getUname(),userinfo.getPassword(),getName());
        System.out.println("info:"+authenticationInfo.getCredentials().toString());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Userinfo userinfo=(Userinfo) principalCollection.getPrimaryPrincipal();
        for(Role r:userinfo.getRoleList())
        {authorizationInfo.addRole(r.getRid().toString());
        for(Permission p:r.getPermissionList()){
            authorizationInfo.addStringPermission(p.getPermission());
        }
        }
        return authorizationInfo;
    }

}
