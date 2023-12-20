#!/bin/bash
current_path=`pwd`
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install wget curl vim git zip -y
sudo apt-get install build-essential cmake tofrodos libtool-bin autoconf pkg-config -y
ubuntu_version=`sudo lsb_release -r|awk '{print $2}'`
 [ $ubuntu_version == 18.04 ] && sudo apt-get install python
sudo mkdir -p /opt/android
sudo chown `whoami` /opt/android
cd /opt/android
wget https://dl.google.com/android/repository/android-ndk-r17c-linux-x86_64.zip -O android-ndk-r17c-linux-x86_64.zip && echo "3f541adbd0330a9205ba12697f6d04ec90752c53d6b622101a2a8a856e816589  android-ndk-r17c-linux-x86_64.zip"|sha256sum -c
unzip -q android-ndk-r17c-linux-x86_64.zip
mv android-ndk-r17c ndk-r17c
git clone https://github.com/MoreloNetwork/morelo-android-lib.git /tmp/morelo-android-lib
cd /tmp/morelo-android-lib/external-libs/
find . -name "*.sh" -exec chmod +x {} \;
mkdir -p build/src
make all
if [ $? -eq 0 ]; then
cd $current_path
mkdir -p morelo-libs/{morelo,boost,openssl,libsodium}/lib/{arm64-v8a,armeabi-v7a}
cp -a /tmp/morelo-android-lib/external-libs/build/build/morelo/arm/lib/* morelo-libs/morelo/lib/armeabi-v7a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/morelo/arm64/lib/* morelo-libs/morelo/lib/arm64-v8a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/boost/arm/lib/* morelo-libs/boost/lib/armeabi-v7a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/boost/arm64/lib/* morelo-libs/boost/lib/arm64-v8a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/openssl/arm/lib/* morelo-libs/openssl/lib/armeabi-v7a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/openssl/arm64/lib/* morelo-libs/openssl/lib/arm64-v8a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/libsodium/arm/lib/* morelo-libs/libsodium/lib/armeabi-v7a/
cp -a /tmp/morelo-android-lib/external-libs/build/build/libsodium/arm64/lib/* morelo-libs/libsodium/lib/arm64-v8a/
fi
