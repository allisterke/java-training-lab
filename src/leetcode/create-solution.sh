#!/bin/bash

if [ $# -lt 1 ]; then
    echo 'please input package name'
    exit 1
fi

for DIR in $*; do
    if [[ ! $DIR =~ a.* ]]; then
        DIR=$(printf "a%03d" $DIR)
    fi
    
    if [ -e $DIR ]; then
        echo $DIR exists
        continue
    fi

    mkdir $DIR 
    cp Solution.java $DIR/
    sed -i -r "1 s/^(.*)$/package leetcode.$DIR;/" $DIR/Solution.java
    
    echo $DIR/Solution.java created
done
