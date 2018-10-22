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

5. Add `auth.AuthenticationManager = com.mybonita.cas.CASAuthenticationManagerImpl` to `setup/platform_conf/current/tenant_template_portal/authenticationManager-config.properties` and `setup/platform_conf/current/tenants/1/tenant_portal/authenticationManager-config.properties`.

6. `setup/setup.sh push`

7. Compile and copy this project to `server/webapps/bonita/WEB-INF/lib`.
