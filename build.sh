
#!/bin/bash
echo "######################################################################################################################################################"
echo "########################################################## Gradle Clean ##############################################################################"
echo "######################################################################################################################################################"
sh ./gradlew clean --stacktrace
rm -r scmrzscanner/build
echo "######################################################################################################################################################"
echo "########################################################## Building aar ##############################################################################"
echo "######################################################################################################################################################"
sh ./gradlew :library:assembleRelease --stacktrace
echo "########################################################## Copying aar ###############################################################################"
cp  library/build/outputs/aar/*.aar aar/

echo "You can find the aar file under 'aar'."
echo "########################################################## Uploading aar ###############################################################################"
echo "Uploading Library to ShoCard Nexus Repository..."
sh ./gradlew :library:uploadArchives --stacktrace