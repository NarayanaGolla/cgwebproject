keytool -genkeypair \
 -alias mylocalcert \
 -keyalg RSA \
 -keysize 2048 \
 -validity 365 \
 -keystore mylocalcert.jks \
 -storepass changeit


 keytool -list -v -keystore mylocalcert.jks -storepass changeit

 keytool -export -alias mylocalcert -file mylocalcert.cer -keystore mylocalcert.jks -storepass changeit

keytool -import -trustcacerts -alias mylocalcert -file mylocalcert.cer -keystore cacerts -storepass changeit
