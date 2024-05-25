## Archetype

1. Create archetype
```shell
./mvnw archetype:generate \
  -DgroupId=com.mashkario.maven \
  -DartifactId=archetype \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-archetype
```
2. Install locally
```shell
cd archetype/
../mvnw install
```

3. Create new project from archetype
```shell
./mvnw archetype:generate \
  -DgroupId=com.mashkario.maven \
  -DartifactId=dummy \
  -DarchetypeGroupId=com.mashkario.maven \
  -DarchetypeArtifactId=archetype
```