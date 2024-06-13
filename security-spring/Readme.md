# Spring Security playground
## X.509 Certificate Authentication

### Server side certificate
1. Generate Root CA
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout rootCA.key -out rootCA.crt
2. Generate localhost server certificate
openssl req -new -newkey rsa:4096 -keyout localhost.key -out localhost.csr
3. Sign the server certificate
openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in localhost.csr -out localhost.crt -days 365 -CAcreateserial -extfile localhost.ext
4. Create a PKCS12 keystore
openssl pkcs12 -export -out localhost.p12 -name "localhost" -inkey localhost.key -in localhost.crt
5. Convert the PKCS12 keystore to a JKS keystore
keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS
6. Add Root CA to browser, trust to identify websites
7. Move certificates to cert folder
8. Run `./gradlew bootRun`
8. Access https://localhost:8443/user (user Admin, password admin)

### Client side certificate
1. Add the Root CA to the truststore
keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks
2. Generate client certificate
openssl req -new -newkey rsa:4096 -nodes -keyout clientBob.key -out clientBob.csr
3. Sign the client certificate
openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in clientBob.csr -out clientBob.crt -days 365 -CAcreateserial
4. Create a PKCS12 keystore
openssl pkcs12 -export -out clientBob.p12 -name "clientBob" -inkey clientBob.key -in clientBob.crt
5. Add client clientBob.p12 in browser
6. Move certificates to cert folder
7. 8. Run `./gradlew bootRun`
6. Access https://localhost:8443/user (user Admin, password admin), accept certificate prompt