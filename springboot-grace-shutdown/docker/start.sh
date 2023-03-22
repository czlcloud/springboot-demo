#!/bin/bash

function graceful_shut_down {
  echo "[shell]begin graceful shut down!"
  kill -TERM $PID
  echo "[shell]kill pid $PID"
  # do something..
  sleep 30;
  echo "[shell]graceful shut down end"
}
trap graceful_shut_down TERM INT

exec java "$JAVA_OPTS" "$JAVA_AGENTS" -jar springboot-grace-shutdown-0.0.1.jar &
#tail --pid=${!} -f /dev/null &
#wait "${!}"
PID=$!
wait $PID
trap - TERM INT
wait $PID
EXIT_STATUS=$?