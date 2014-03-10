#!/bin/sh

cwd="$( pwd )"; cd "$( dirname "$0" )"; dir="$( pwd )"; cd "$cwd"
generated="$dir/src/generated"

exec java \
  -jar "$dir/dsl-clc.jar" latest \
  --project-ini-path="$generated/resources/dsl-project.ini" \
  --dsl-path="$dir/dsl" \
  --with-active-record \
  --output-path="$generated" \
  "$@"
