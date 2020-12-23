# Authority-Management-System-2.0
项目采用SpringBoot对SSM 写的Authority-Management-System企业权限管理系统进行了重构，并放弃了SpringSecurity而使用Shiro用于权限控制，使其代码更加简洁易懂，非常适合初学者学习，如果对你有所帮助请给个Star鼓励一下！
SpringBoot 企业权限管理系统
完整项目github地址：
SSM企业权限管理系统：[https://github.com/lindaifeng/Authority-Management-System]

SpringBoot企业权限管理系统：https://github.com/lindaifeng/Authority-Management-System-2.0

一、前言
1、SpringBoot 企业权限管理系统 是对SSM企业权限管理系统的重构。
2、简化代码开发减少重复代码的书写，改变了之前原有的复杂sql注解开发，采用xml文件形式，使得代码整体显得更加美观、简洁。

具体详细介绍可参考：SSM企业权限管理系统https://editor.csdn.net/md/?articleId=107418838
在这里插入图片描述
|技术选型|
|—|—|
| AdminLTE前端模板 |
| JSTL+El表达式|
|maven |
|SpringBoot |
| Shiro|
| MyBatis|
| Mysql|

二、新技术介绍
主要采用了一种新的权限管理控制框架Shiro。

Shiro是Apache旗下的一个开源项目，它是一个非常易用的安全框架，提供了包括认证、授权、加密、会话管理等功能，与Spring Security一样属基于权限的安全框架，但是与Spring Security 相比，Shiro使用了比较简单易懂易于使用的授权方式。
Shiro属于轻量级框架，相对于Spring Security简单很多，并没有security那么复杂。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。
实际上，Shiro的主要功能是管理应用程序中与安全相关的全部，同时尽可能支持多种实现方法。Shiro是建立在完善的接口驱动设计和面向对象原则之上的，支持各种自定义行为。Shiro提供的默认实现，使其能完成与其他安全框架同样的功能，这不也是我们一直努力想要得到的吗！

优势特点

它是一个功能强大、灵活的、优秀的、开源的安全框架。
它可以胜任身份验证、授权、企业会话管理和加密等工作。
它易于使用和理解，与Spring Security相比，入门门槛低。
Shiro可以做什么

● 验证用户身份
● 用户访问控制，比如用户是否被赋予了某个角色；是否允许访问某些资源
● 在任何环境都可以使用Session API，即使不是WEB项目或没有EJB容器
● 事件响应(在身份验证，访问控制期间，或是session生命周期中)
● 集成多种用户信息数据源
● SSO-单点登陆
● Remember Me，记住我
● Shiro尝试在任何应用环境下实现这些功能，而不依赖其他框架、容器或应用服务器。
在这里插入图片描述

在这里插入图片描述
四大基石——身份验证，授权，会话管理，加密

Authentication（认证）, Authorization（授权）, Session Management（会话管理）, Cryptography（加密）代表Shiro应用安全的四大基石。

它们分别是：

Authentication（认证）：用户身份识别，通常被称为用户“登录”。
Authorization（授权）：访问控制。比如某个用户是否具有某个操作的使用权限。 Session
Management（会话管理）：特定于用户的会话管理,甚至在非web 应用程序。
Cryptography（加密）：在对数据源使用加密算法加密的同时，保证易于使用。
除此之外，还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点。

特别是对以下的功能支持：

Web支持：Shiro 提供的 web 支持 api ，可以很轻松的保护 web 应用程序的安全。
缓存：缓存是 Apache Shiro 保证安全操作快速、高效的重要手段。
并发：Apache Shiro 支持多线程应用程序的并发特性。
测试：支持单元测试和集成测试，确保代码和预想的一样安全。
“Run As”：这个功能允许用户在许可的前提下假设另一个用户的身份。
“Remember Me”：跨 session 记录用户的身份，只有在强制需要时才需要登录。
在这里插入图片描述
三个主要理念：

Subject：代表当前用户，Subject 可以是一个人，也可以是第三方服务、守护进程帐户、时钟守护任务或者其它当前和软件交互的任何事件。
SecurityManager：管理所有Subject，SecurityManager 是 Shiro 架构的核心，配合内部安全组件共同组成安全伞。
Realms：用于进行权限信息的验证，我们自己实现。Realm 本质上是一个特定的安全 DAO：它封装与数据源连接的细节，得到Shiro 所需的相关的数据。在配置 Shiro 的时候，你必须指定至少一个Realm 来实现认证（authentication）和/或授权（authorization）。
我们需要实现Realms的Authentication 和 Authorization。其中 Authentication 是用来验证用户身份，Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。

三、SpringBoot整合Shiro
1、导入依赖
<!-- shiro -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.1</version>
        </dependency>
2、自定义认证器
添加一个MyShiroRealm并继承AuthorizingRealm，实现其中的两个方法。

doGetAuthenticationInfo：实现用户认证，通过服务加载用户信息并构造认证对象返回。
doGetAuthorizationInfo：实现权限认证，通过服务加载用户角色和权限信息设置进去。

/**
 * @author: 清峰
 * @date: 2020/12/15 20:51
 * @code: 愿世间永无Bug!
 * @description: 自定义shiro验证规则
 */
public class MyShiroRealm extends AuthorizingRealm{
    //注入service用于调用方法查询数据库用户信息
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户认证
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("1：身份认证-->MyShiroRealm.doGetAuthorizationInfo()");
        //获取用户输入的信息
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String userName=token.getUsername();
        String userPassword=new String(token.getPassword());

        //通过用户名去数据库查询数据并判断输入是否正确
        UserInfo user=userInfoService.findByUsername(userName);
        if(user==null){
            throw new UnknownAccountException("账号不存在！");
        }else if(!user.getPassword().equals(userPassword)){
            throw new IncorrectCredentialsException("密码不正确！");
        }

        //认证信息
        SimpleAuthenticationInfo authorizationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return authorizationInfo;
    }

    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名

        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        String name = userInfo.getUsername();
        // 查询用户名称
        UserInfo user = userInfoService.findByUsername(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                // 添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }

}
3、Shiro配置类
添加一个Shiro配置类，主要配置路由的访问控制，以及注入自定义的认证器MyShiroRealm


@Configuration
public class ShiroConfig {

    // 将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    // 权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
       ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录
        shiroFilterFactoryBean.setLoginUrl("/");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 错误页面，认证不通过跳转
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/failPage");

        Map<String, String> filterMap = new HashMap<String, String>();

        /** authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器
         * org.apache.shiro.web.filter.authc.FormAuthenticationFilter
            anon：它对应的过滤器里面是空的,什么都没做,可以理解为不拦截
         */

        //未认证可访问的权限 放行拦截资源
        filterMap.put("/webapp/**", "anon");
        filterMap.put("/*/**", "anon");
        filterMap.put("/**", "anon");
        // 已认证可访问的权限
        filterMap.put("/**", "authc");
        // 注销(记住我状态可清除cookie)
        filterMap.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    // 加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
4、权限控制
可添加两个注解在方法上用于权限控制，只有拥有该权限的用户才能访问该方法。
@RequiresRoles(value = {“King”,”USER”,”ADMIN”},logical = Logical.OR)
@RequiresPermissions(“create”)，
加上了权限注解@RequiresPermissions(“create”)，表示用户需要拥有”create”的权限才能访问。
在这里插入图片描述

