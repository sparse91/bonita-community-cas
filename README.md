# bonita-community-cas
CAS integration for bonita community 7.7.4
1. Download Apereo Java CAS client and copy `cas-client-core-3.5.1-SNAPSHOT.jar` to `server/lib`.

2. Add this filter to `server/webapps/bonita/WEB-INF/web.xml`:

```
    <filter>
      <filter-name>CAS Authentication Filter</filter-name>
      <filter-class>org.jasig.cas.client.authentication.AuthenticationFilter</filter-class>
      <init-param>
        <param-name>casServerUrlPrefix</param-name>
        <param-value>https://your.cas.server/cas</param-value>
      </init-param>
      <init-param>
        <param-name>service</param-name>
        <param-value>https://your.bonita.server/bonita/loginservice</param-value>
      </init-param>
    </filter>

    <filter-mapping>
        <filter-name>CAS Authentication Filter</filter-name>
        <url-pattern>/cas</url-pattern>
    </filter-mapping>
```

3. `setup/setup.sh pull`

4. Add `authentication.service.ref.name=casAuthenticationService` to `setup/platform_conf/current/tenants/1/tenant_engine/bonita-tenant-community-custom.properties` and `setup/platform_conf/current/tenant_template_engine/bonita-tenant-community-custom.properties`.

5. Add `<bean id="casAuthenticationService" class="com.mybonita.cas.CASAuthenticationService"/>` to `setup/platform_conf/current/tenant_template_engine/bonita-tenants-custom.xml` and `setup/platform_conf/current/tenants/1/tenant_engine/bonita-tenants-custom.xml` between `<beans></beans>`.

6. Add `auth.AuthenticationManager = com.mybonita.cas.CASAuthenticationManagerImpl` to `setup/platform_conf/current/tenant_template_portal/authenticationManager-config.properties` and `setup/platform_conf/current/tenants/1/tenant_portal/authenticationManager-config.properties`.

7. `setup/setup.sh push`

8. Add `console-common-7.7.4.jar` and `console-server-7.7.4.jar` from `server/webapps/bonita/WEB-INF/lib` to your local maven repo. See `pom.xml` comments.

9. Compile and copy this project jar to `server/webapps/bonita/WEB-INF/lib`.

10. Open `https://your.bonita.server/bonita/cas`.
