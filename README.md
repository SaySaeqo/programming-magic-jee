# Configuration
You need to change path to project directory for correct initialization and portraits saving. Files to update:
- [DataStoreInitializer.java](src/main/java/programmingmagic/DataStoreInitializer.java)
- [web.xml](src/main/webapp/WEB-INF/web.xml)

# How to run
1. Run `war` task
2. Copy directory `wlp` from `backup` to `build` directory (only with first build)
3. Run `libertyDev` task