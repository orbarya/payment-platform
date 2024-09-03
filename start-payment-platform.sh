#!/bin/bash

# Define directories
COMMON_DIR="payment-common"
SERVICE_DIR="payment-service"
RISK_ENGINE_DIR="risk-engine-service"

# Remove previous target directories
echo "Removing previous target directories..."
rm -rf "$COMMON_DIR/target"
rm -rf "$SERVICE_DIR/target"
rm -rf "$RISK_ENGINE_DIR/target"

# Rebuild and start Docker containers
echo "Rebuilding and starting Docker containers..."
docker-compose up --build

# Check for errors
if [ $? -eq 0 ]; then
    echo "Build and deployment successful."
else
    echo "An error occurred during the build or deployment."
fi
