*Template:*

`12/7/2020` - **Inception**

**attendees:**

**discussion items:**

**action items:**

## Meeting Notes

`12/8/2020` - Application Configuration Definitions

**attendees:**

**discussion items:**

**action items:**

`application.yml`

```
application:
	name:
	description:
	id:
	persistence:
		oracle:
			name:
			url:
			username:
			password:
			schema:
			connection-pool:
			  name:
				maximumPoolSize:
				connectionTimeout:
       idleTimeout:
       maxLifetime:
   cache:
		 redis:
			name:
			url:
			username:
			password:
			schema:
			......
		messaging:
		  kafka:
		  	<property1>
		  	<property2>
   	
   	spring.datasource.url = ${application.persistence.datasource1.url}
   	spring.cache.redis.connection.url = ${application.cache.cache1.url}

```



**USER STORY**: starter project with ready to use datasource

**GIVEN**: the database/connection pool properties are defined in the *specific format* in 

​                application.yml

**WHEN**: the application.yml is read during spring boot app startup

**THEN**:  we expect the following

   - oracle, oracle-connection-pool beans created on startup
				- healthcheck endpoints (health, info)
   - Logging to STDOUT enabled
   - recipe to configure the datasource/connection pool
   - cookbook hosted on PCF with the above recipe

