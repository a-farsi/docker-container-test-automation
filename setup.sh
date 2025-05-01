#!/bin/bash
configurations=(
  "API_PATH=v1/sentiment USERNAME=alice PASSWORD=wonderland SENTENCE='I am not happy'"
  "API_PATH=v2/sentiment USERNAME=alice PASSWORD=wonderland SENTENCE='I am not happy'"
)

for config in "${configurations[@]}"; do
  export $config
  docker-compose up --build --force-recreate
  sleep 10  # Attendre que les tests s'exécutent
  docker-compose down
done
