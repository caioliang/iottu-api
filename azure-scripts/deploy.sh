#!/bin/bash

# Instalar dependências e Docker na VM
ssh ${ADMIN_USER}@<IP_PUBLICO> << EOF
  sudo apt update
  sudo apt upgrade -y
  sudo apt install apt-transport-https ca-certificates curl software-properties-common git -y

  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
  echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

  sudo apt update
  sudo apt install docker-ce docker-ce-cli containerd.io -y

  # Clonar projeto
  git clone https://github.com/caioliang/iottu-api.git
  cd iottu-api

  # Criar Dockerfile (caso não exista)
  cat << DOCKERFILE > Dockerfile
    FROM maven:3.9.2-eclipse-temurin-17 AS builder
    WORKDIR /app
    COPY pom.xml .
    COPY src ./src
    RUN mvn clean package -DskipTests

    FROM eclipse-temurin:17-jre
    WORKDIR /app
    COPY --from=builder /app/target/iottu-api-*.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]
    DOCKERFILE

  # Build da imagem
  sudo docker build -t iottu-api .

  # Criar usuário sem privilégios
  sudo adduser iottuuser --gecos "" --disabled-password
  echo "iottuuser ALL=(ALL) NOPASSWD:ALL" | sudo tee /etc/sudoers.d/iottuuser
  sudo deluser iottuuser sudo
  sudo usermod -aG docker iottuuser

  # Rodar container com usuário
  sudo docker run -d -p 8080:8080 --name iottu-api iottu-api
EOF
