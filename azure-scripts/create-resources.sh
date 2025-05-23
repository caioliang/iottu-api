#!/bin/bash

# Variáveis
RESOURCE_GROUP="rg-fiap-iottu"
VM_NAME="iottu-vm"
LOCATION="brazilsouth"
ADMIN_USER="adminuser"
ADMIN_PASSWORD="StrongPassword123!"
VM_SIZE="Standard_B1s"

# Criar grupo de recursos
az group create --name $RESOURCE_GROUP --location $LOCATION

# Criar VM
az vm create \
  --name $VM_NAME \
  --resource-group $RESOURCE_GROUP \
  --image Canonical:0001-com-ubuntu-server-focal:20_04-lts:latest \
  --admin-username $ADMIN_USER \
  --admin-password $ADMIN_PASSWORD \
  --size $VM_SIZE

# Abrir porta 8080
az vm open-port \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --port 8080
