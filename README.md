# Programming magic Jakarta EE

Study of Jakarte EE framework. I added myself challenge to it to try run it with groovy and gradle. This wasn't the best idea though... (finally I dropped groovy due to incompatibilities)  

Different version are saved as tags:  
- Basic usage with servlets
- After adding CDI
- Using JSF
- using JAX-RS
- using JPA
- using EJB
- using EJB with added security
- using JSF-security and with i18n
- final version with validation

API calls for testing can be found in [requests.http](requests.http)  
All sended through API images should be saved in portraits folder.  

# How to run
1. First change path to project directory for correct initialization and portraits saving. Files to update:
- [DataStoreInitializer.java](src/main/java/programmingmagic/init/DataStoreInitializer.java)
- [web.xml](src/main/webapp/WEB-INF/web.xml)
2. Run `war` task
3. Copy directory `wlp` from `backup` to `build` directory (only with first build)
4. Run `libertyDev` task